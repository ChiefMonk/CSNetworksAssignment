package uct.cs.networks.utils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.crypto.KeyGenerator;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import static javax.crypto.Cipher.ENCRYPT_MODE;
import static javax.crypto.Cipher.DECRYPT_MODE;

public class AESEncryption {
    private static final String CIPHER = "AES";
    private static final String MODE = "AES/CBC/PKCS5Padding";
    private static final String IV = "abcdefghabcdefgh";

    private Key getSecureRandomKey(String cipher, int keySize) {
        byte[] secureRandomKeyBytes = new byte[keySize / 8];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(secureRandomKeyBytes);
        return new SecretKeySpec(secureRandomKeyBytes, cipher);
    }

    public Key getKeyFromKeyGenerator() throws NoSuchAlgorithmException {
        int keySize = 128;
        KeyGenerator keyGenerator = KeyGenerator.getInstance(CIPHER);
        keyGenerator.init(keySize);
        return keyGenerator.generateKey();
    }

    public String serializeKeyToString(Key key) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(key);
        oos.close();
        byte[] bytes = bos.toByteArray();
        return Base64.getEncoder().encodeToString(bytes);
    }

    public Key deserializeKeyFromString(String keyString) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(keyString);
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object obj = ois.readObject();
        ois.close();
        return (Key) obj;
    }

    private void readBtyeArray(byte[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println("");
    }

    public String encrypt(String plainText, Key key) {
        try {
            Cipher cipher = Cipher.getInstance(MODE);
            // System.out.println("a");
            cipher.init(ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes()));
            // System.out.println("b");
            byte[] cipherTextBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            // System.out.println("c");
            // readBtyeArray(cipherTextBytes);
            return Base64.getEncoder().encodeToString(cipherTextBytes);
            // logger.info();
        } catch (Exception e) {
            System.out.println("Encrypting error");
            e.printStackTrace();
            return "";
        }
    }

    public String decrypt(String encrypted, Key key) {
        try {
            Cipher cipher = Cipher.getInstance(MODE);
            byte[] values = Base64.getDecoder().decode(encrypted.getBytes());
            cipher.init(DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes()));
            String decrypted = new String(cipher.doFinal(values));
            return decrypted;
        } catch (Exception e) {
            System.out.println("Decrypting error");
            e.printStackTrace();
            return null;

        }
    }

    public static void main(String[] args) {
        AESEncryption test = new AESEncryption();
        try {
            Key key = test.getKeyFromKeyGenerator();
            String encrypted = test.encrypt("So, what are we going to do about this?", key);
            System.out.println("____ Encrypted:____");
            System.out.println(encrypted);
            System.out.println("____ Decrypted:____");
            test.decrypt(encrypted, key);
        } catch (Exception e) {

        }
    }
}
