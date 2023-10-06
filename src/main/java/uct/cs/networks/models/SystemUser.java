package uct.cs.networks.models;

import java.io.File;
import java.io.FileInputStream;

import uct.cs.networks.utils.*;

/**
 *
 * @author Shaylin Chetty (CHTSHA042@myuct.ac.za)
 * @author Chipo Hamayobe (HMYCHI001@myuct.ac.za)
 * @author Orefile Morule (MRLORE001@myuct.ac.za)
 * @author Enock Shezi (SHZENO001@myuct.ac.za)
 */

public class SystemUser {
    private final String _id;
    private final String _name;
    private final String _emailAddress;
    private final String _password;
    private String _secretKey;
    private String _publicKey; // file path to key

    public SystemUser(String name, String emailAddress, String password, Boolean chatsWith) {
        _id = java.util.UUID.randomUUID().toString();
        _name = name;
        _emailAddress = emailAddress;
        _password = password;
        if (chatsWith) {
            // Existing user attempting to chat with SystemUser
        } else {
            // Create new SystemUser with private and public key
            RSAKeyGenerator keyGenerator = new RSAKeyGenerator();
            keyGenerator.createKeys(name, password); // will create keys in path below
            _publicKey = "keys\\UserPublicKey.asc";
        }

    }

    public String getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public String getEmailAddress() {
        return _emailAddress;
    }

    public String getSecretKey() {
        return _secretKey;
    }

    public FileInputStream getPublicKey() {
        FileInputStream pubKey = null;
        try {
            pubKey = new FileInputStream(_publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pubKey;
    }
}
