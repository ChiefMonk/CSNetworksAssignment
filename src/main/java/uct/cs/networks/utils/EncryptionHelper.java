package uct.cs.networks.utils;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.apache.commons.io.IOUtils;
import org.bouncycastle.bcpg.CompressionAlgorithmTags;
import org.bouncycastle.bcpg.SymmetricKeyAlgorithmTags;
import org.bouncycastle.openpgp.PGPException;
import uct.cs.networks.interfaces.IMessage;

import uct.cs.networks.models.SystemUser;

public class EncryptionHelper {

    public static String ByteArray2String(byte[] a) {
        return Base64.getEncoder().encodeToString(a);
        // String x = new String(a, StandardCharsets.UTF_8);
        // String text = "";
        // for (int i = 0; i < a.length; i++) {
        // text = text + a[i] + " ";
        // }
        // System.out.println("GPT:\n" + x);
        // System.out.println("END of GPT");
        // System.out.println("Mine:\n" + text);
        // return text;
    }

    public static byte[] String2ByteArray(String a) {
        return Base64.getDecoder().decode(a);
    }

    // Takes in a message as a string and returns a string which has been encryped
    public static String encryptwithPublicKey(String message, SystemUser receiver) {
        PgpEncryptionUtil pgpEncryptionUtil = null;
        pgpEncryptionUtil = PgpEncryptionUtil.builder()
                .armor(true)
                .compressionAlgorithm(CompressionAlgorithmTags.ZIP)
                .symmetricKeyAlgorithm(SymmetricKeyAlgorithmTags.AES_128)
                .withIntegrityCheck(true)
                .build();
        try {
            byte[] publicKeyasByte = receiver.getPublicKey();
            InputStream publicKeyInputStream = new ByteArrayInputStream(publicKeyasByte);
            byte[] encryptedBytes = pgpEncryptionUtil.encrypt(message.getBytes(Charset.defaultCharset()),
                    publicKeyInputStream); // Needs to be as an input stream
            return ByteArray2String(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Takes in a message as a string and returns a string which has been encryped
    public static String encryptwithPublicKey(String message, String server) {
        PgpEncryptionUtil pgpEncryptionUtil = null;
        pgpEncryptionUtil = PgpEncryptionUtil.builder()
                .armor(true)
                .compressionAlgorithm(CompressionAlgorithmTags.ZIP)
                .symmetricKeyAlgorithm(SymmetricKeyAlgorithmTags.AES_128)
                .withIntegrityCheck(true)
                .build();
        byte[] _publicKeyStream = null;
        try {
            _publicKeyStream = IOUtils.toByteArray(new FileInputStream("keys\\ServerPublicKey.asc"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            byte[] encryptedBytes = pgpEncryptionUtil.encrypt(message.getBytes(Charset.defaultCharset()),
                    _publicKeyStream); // Needs to be as an input stream
            return ByteArray2String(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Takes in an encoded message as a string and retunrs the decoded message as a
    // String.
    public static String decryptwithPrivateKey(String encryptedMessage, SystemUser reciever, String password) {
        System.out.println("Input to decrypt: " + encryptedMessage);
        System.out.println("Checking right reciever:");
        System.out.println(
                reciever.getName() + " - " + HelperUtils.byteArray2String(reciever.getPrivateKey()) + password);
        PgpDecryptionUtil pgpDecryptionUtil = null;
        System.out.println("A");
        byte[] encryptedMessageBytes = EncryptionHelper.String2ByteArray(encryptedMessage);// encryptedMessage.getBytes(Charset.defaultCharset());
        System.out.println(encryptedMessageBytes.length);
        System.out.println("B");
        String decrypted;
        System.out.println("C");
        byte[] privateKeyasByte = reciever.getPrivateKey();
        InputStream privateKeyInputStream = new ByteArrayInputStream(privateKeyasByte);
        try {
            pgpDecryptionUtil = new PgpDecryptionUtil(privateKeyInputStream, password);
            System.out.println("D");
            byte[] decryptedBytes = pgpDecryptionUtil.decrypt(encryptedMessageBytes);
            System.out.println("E");
            decrypted = new String(decryptedBytes, Charset.defaultCharset());
            System.out.println("F");
            // privateKeyInputStream.close();
            return decrypted;
        } catch (IOException | PGPException e) {
            System.out.println("Entire try failed");
            e.printStackTrace();
            return null;
        }
    }

    public static String decryptwithPrivateKey(String encryptedMessage, String server, String password) {
        PgpDecryptionUtil pgpDecryptionUtil = null;
        byte[] encryptedMessageBytes = encryptedMessage.getBytes(Charset.defaultCharset());
        String decrypted;
        byte[] _privateKeyStream = null;
        try {
            _privateKeyStream = IOUtils.toByteArray(new FileInputStream("keys\\ServerPrivateKey.asc"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            pgpDecryptionUtil = new PgpDecryptionUtil(_privateKeyStream, password);
            byte[] decryptedBytes = pgpDecryptionUtil.decrypt(encryptedMessageBytes);
            decrypted = new String(decryptedBytes, Charset.defaultCharset());
            return decrypted;
        } catch (IOException | PGPException e) {
            e.printStackTrace();
            return null;
        }
    }

    // UserModel has shared secret key as a string
    public static String encryptWithSharedKey(String message, SystemUser reciever) {
        System.out.println("In encryptWithSharedKey: " + reciever.getSecretKey());

        AESEncryption aesEncryption = new AESEncryption();
        try {
            String encrypted = aesEncryption.encrypt(message, HelperUtils.convertBase64toKey(reciever.getSecretKey()));
            // aesEncryption.deserializeKeyFromString(reciever.getSecretKey()));
            return encrypted;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decryptWithSharedKey(String encryptedMessage, SystemUser reciever) {
        try {
            System.out.println("In DecryptWithSharedKey: " + reciever.getName() + " - " + reciever.getSecretKey());
            AESEncryption aesEncryption = new AESEncryption();
            String decryptedString = aesEncryption.decrypt(encryptedMessage,
                    HelperUtils.convertBase64toKey(reciever.getSecretKey()));
            // aesEncryption.deserializeKeyFromString(reciever.getSecretKey()));
            return decryptedString;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String createMessageDigest(IMessage message) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String data = message.getMessageData();
            byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
