package uct.cs.networks.utils;

import java.io.IOException;
import java.nio.charset.Charset;

import org.bouncycastle.bcpg.CompressionAlgorithmTags;
import org.bouncycastle.bcpg.SymmetricKeyAlgorithmTags;
import org.bouncycastle.openpgp.PGPException;

import uct.cs.networks.models.SystemUser;

public class EncryptionHelper {

    public static String ByteArray2String(byte[] a) {
        String text = "";
        for (int i = 0; i < a.length; i++) {
            text = text + a[i] + " ";
        }
        return text;
    }

    // Takes in a message as a string and returns a string which has been encryped
    public static Object encryptwithPublicKey(String message, SystemUser receiver) {
        PgpEncryptionUtil pgpEncryptionUtil = null;
        pgpEncryptionUtil = PgpEncryptionUtil.builder()
                .armor(true)
                .compressionAlgorithm(CompressionAlgorithmTags.ZIP)
                .symmetricKeyAlgorithm(SymmetricKeyAlgorithmTags.AES_128)
                .withIntegrityCheck(true)
                .build();
        try {
            byte[] encryptedBytes = pgpEncryptionUtil.encrypt(message.getBytes(Charset.defaultCharset()),
                    receiver.getPublicKey()); // Needs to be as an input stream
            return ByteArray2String(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Takes in an encoded message as a string and retunrs the decoded message as a
    // String.
    public static Object decryptwithPrivateKey(String encryptedMessage, SystemUser reciever, String password) {
        PgpDecryptionUtil pgpDecryptionUtil = null;
        byte[] encryptedMessageBytes = encryptedMessage.getBytes(Charset.defaultCharset());
        String decrypted;
        try {
            pgpDecryptionUtil = new PgpDecryptionUtil(reciever.getSecretKey(), password);
            byte[] decryptedBytes = pgpDecryptionUtil.decrypt(encryptedMessageBytes);
            decrypted = new String(decryptedBytes, Charset.defaultCharset());
            return decrypted;
        } catch (IOException | PGPException e) {
            e.printStackTrace();
            return null;
        }
    }

    // UserModel has shared secret key as a string
    public static Object encryptWithSharedKey(String message, SystemUser reciever) {
        AESEncryption aesEncryption = new AESEncryption();
        try {
            String encrypted = aesEncryption.encrypt(message,
                    aesEncryption.deserializeKeyFromString(reciever.getSecretKey()));
            return encrypted;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object decryptWithSharedKey(String encryptedMessage, SystemUser reciever) {
        try {
            AESEncryption aesEncryption = new AESEncryption();
            String decryptedString = aesEncryption.decrypt(encryptedMessage,
                    aesEncryption.deserializeKeyFromString(reciever.getSecretKey()));
            return decryptedString;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
