package uct.cs.networks.utils;

/*          USAGE

    To generate a private and public key use the "createKeys(String name, String passPhrase)"" method
        This method will generate public and private keys and save them to the "localKeys" folder.
        This shoud stay local. Public keys will be shared as part of Client object. 

*/

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.bcpg.CompressionAlgorithmTags;
import org.bouncycastle.bcpg.HashAlgorithmTags;
import org.bouncycastle.bcpg.PublicKeyAlgorithmTags;
import org.bouncycastle.bcpg.SymmetricKeyAlgorithmTags;
import org.bouncycastle.bcpg.sig.Features;
import org.bouncycastle.bcpg.sig.KeyFlags;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPKeyPair;
import org.bouncycastle.openpgp.PGPKeyRingGenerator;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPPublicKeyRing;
import org.bouncycastle.openpgp.PGPSecretKeyRing;
import org.bouncycastle.openpgp.PGPSignature;
import org.bouncycastle.openpgp.PGPSignatureSubpacketGenerator;
import org.bouncycastle.openpgp.operator.PBESecretKeyEncryptor;
import org.bouncycastle.openpgp.operator.PGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.PGPDigestCalculator;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPDigestCalculatorProviderBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPKeyPair;
import org.bouncycastle.openpgp.operator.jcajce.JcePBESecretKeyEncryptorBuilder;

public class RSAKeyGenerator {

    private static final int SIG_HASH = HashAlgorithmTags.SHA512;
    private static final int[] HASH_PREFERENCES = new int[] {
            HashAlgorithmTags.SHA512, HashAlgorithmTags.SHA384, HashAlgorithmTags.SHA256, HashAlgorithmTags.SHA224
    };
    private static final int[] SYM_PREFERENCES = new int[] {
            SymmetricKeyAlgorithmTags.AES_256, SymmetricKeyAlgorithmTags.AES_192, SymmetricKeyAlgorithmTags.AES_128
    };
    private static final int[] COMP_PREFERENCES = new int[] {
            CompressionAlgorithmTags.ZLIB, CompressionAlgorithmTags.BZIP2, CompressionAlgorithmTags.ZLIB,
            CompressionAlgorithmTags.UNCOMPRESSED
    };

    private static void generateAndExportKeyRing(
            OutputStream secretOut,
            OutputStream publicOut,
            String identity,
            char[] passPhrase,
            boolean armor)
            throws IOException, NoSuchProviderException, PGPException, NoSuchAlgorithmException {
        if (armor) {
            secretOut = new ArmoredOutputStream(secretOut);
        }

        PGPDigestCalculator sha1Calc = new JcaPGPDigestCalculatorProviderBuilder().build().get(HashAlgorithmTags.SHA1);
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA", "BC");

        PGPContentSignerBuilder contentSignerBuilder = new JcaPGPContentSignerBuilder(
                PublicKeyAlgorithmTags.RSA_GENERAL, SIG_HASH).setProvider("BC");
        PBESecretKeyEncryptor secretKeyEncryptor = new JcePBESecretKeyEncryptorBuilder(
                SymmetricKeyAlgorithmTags.AES_256, sha1Calc).setProvider("BC")
                .build(passPhrase);

        Date now = new Date();

        kpg.initialize(3072);
        KeyPair primaryKP = kpg.generateKeyPair();
        PGPKeyPair primaryKey = new JcaPGPKeyPair(PGPPublicKey.RSA_GENERAL, primaryKP, now);
        PGPSignatureSubpacketGenerator primarySubpackets = new PGPSignatureSubpacketGenerator();
        primarySubpackets.setKeyFlags(true, KeyFlags.CERTIFY_OTHER);
        primarySubpackets.setPreferredHashAlgorithms(false, HASH_PREFERENCES);
        primarySubpackets.setPreferredSymmetricAlgorithms(false, SYM_PREFERENCES);
        primarySubpackets.setPreferredCompressionAlgorithms(false, COMP_PREFERENCES);
        primarySubpackets.setFeature(false, Features.FEATURE_MODIFICATION_DETECTION);
        primarySubpackets.setIssuerFingerprint(false, primaryKey.getPublicKey());

        kpg.initialize(3072);
        KeyPair signingKP = kpg.generateKeyPair();
        PGPKeyPair signingKey = new JcaPGPKeyPair(PGPPublicKey.RSA_GENERAL, signingKP, now);
        PGPSignatureSubpacketGenerator signingKeySubpacket = new PGPSignatureSubpacketGenerator();
        signingKeySubpacket.setKeyFlags(true, KeyFlags.SIGN_DATA);
        signingKeySubpacket.setIssuerFingerprint(false, primaryKey.getPublicKey());

        kpg.initialize(3072);
        KeyPair encryptionKP = kpg.generateKeyPair();
        PGPKeyPair encryptionKey = new JcaPGPKeyPair(PGPPublicKey.RSA_GENERAL, encryptionKP, now);
        PGPSignatureSubpacketGenerator encryptionKeySubpackets = new PGPSignatureSubpacketGenerator();
        encryptionKeySubpackets.setKeyFlags(true, KeyFlags.ENCRYPT_COMMS | KeyFlags.ENCRYPT_STORAGE);
        encryptionKeySubpackets.setIssuerFingerprint(false, primaryKey.getPublicKey());

        PGPKeyRingGenerator gen = new PGPKeyRingGenerator(PGPSignature.POSITIVE_CERTIFICATION, primaryKey, identity,
                sha1Calc, primarySubpackets.generate(), null, contentSignerBuilder, secretKeyEncryptor);
        gen.addSubKey(signingKey, signingKeySubpacket.generate(), null, contentSignerBuilder);
        gen.addSubKey(encryptionKey, encryptionKeySubpackets.generate(), null);

        PGPSecretKeyRing secretKeys = gen.generateSecretKeyRing();
        secretKeys.encode(secretOut);

        secretOut.close();

        if (armor) {
            publicOut = new ArmoredOutputStream(publicOut);
        }

        List<PGPPublicKey> publicKeyList = new ArrayList<PGPPublicKey>();
        Iterator<PGPPublicKey> it = secretKeys.getPublicKeys();
        while (it.hasNext()) {
            publicKeyList.add(it.next());
        }

        PGPPublicKeyRing publicKeys = new PGPPublicKeyRing(publicKeyList);

        publicKeys.encode(publicOut);

        publicOut.close();
    }

    private static final String keysFolder = "keys";
   // private static final String privateKeyFile = "keys\\UserPrivateKey.asc";
   // private static final String publicKeyFile = "keys\\UserPublicKey.asc";

    public static void createKeys(String privateKeyFile, String publicKeyFile, String identity, String passPhrase) {
        try {
            createKeysFolder();

            Security.addProvider(new BouncyCastleProvider());
            if ((identity == null) || (passPhrase == null)) {
                System.out.println("Please specify both the identity and passPhrase of the key");
            } else {

                // if(checkIfBothKeysExist()) //changed incase passwords change
                // return;

                FileOutputStream out1 = new FileOutputStream(privateKeyFile);
                FileOutputStream out2 = new FileOutputStream(publicKeyFile);
                generateAndExportKeyRing(out1, out2, identity, passPhrase.toCharArray(), true);
                
                out1.close();
                out2.close();
            }
        } catch (IOException | NoSuchAlgorithmException | NoSuchProviderException | PGPException ex) {
            Logger.getLogger(RSAKeyGenerator.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    private static void createKeysFolder() {
        File file = new File(keysFolder);
        if (file.exists() && file.isDirectory())
            return;

        file.mkdir();
    }

   private static boolean checkIfBothKeysExist(String privateKeyFile, String publicKeyFile) {
        File priFile = new File(privateKeyFile);
        File pubFile = new File(publicKeyFile);

        return (priFile.exists() && priFile.isFile() && pubFile.exists() && pubFile.isFile());
    }

    public static void main(String[] args) throws Exception {
        String identity = "server";
        String passPhrase = "networks";
       // createKeys(identity, passPhrase);

    }
}
