package uct.cs.networks.utils;

import java.io.FileInputStream;
import java.io.IOException;
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
        String text = "";
        for (int i = 0; i < a.length; i++) {
            text = text + a[i] + " ";
        }
        return text;
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
            byte[] encryptedBytes = pgpEncryptionUtil.encrypt(message.getBytes(Charset.defaultCharset()),
                    receiver.getPublicKey()); // Needs to be as an input stream
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
        PgpDecryptionUtil pgpDecryptionUtil = null;
        byte[] encryptedMessageBytes = encryptedMessage.getBytes(Charset.defaultCharset());
        String decrypted;
        try {
            pgpDecryptionUtil = new PgpDecryptionUtil(reciever.getPrivateKey(), password);
            byte[] decryptedBytes = pgpDecryptionUtil.decrypt(encryptedMessageBytes);
            decrypted = new String(decryptedBytes, Charset.defaultCharset());
            return decrypted;
        } catch (IOException | PGPException e) {
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

    public static String decryptWithSharedKey(String encryptedMessage, SystemUser reciever) {
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
