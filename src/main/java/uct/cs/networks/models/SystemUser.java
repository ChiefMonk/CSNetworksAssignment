package uct.cs.networks.models;

import java.io.FileInputStream;
import java.io.Serializable;
import org.apache.commons.io.IOUtils;

import uct.cs.networks.utils.*;

/**
 *
 * @author Shaylin Chetty (CHTSHA042@myuct.ac.za)
 * @author Chipo Hamayobe (HMYCHI001@myuct.ac.za)
 * @author Orefile Morule (MRLORE001@myuct.ac.za)
 * @author Enock Shezi (SHZENO001@myuct.ac.za)
 */

public class SystemUser implements Serializable {
    private static final String keysFolder = "keys";
    private final String _id;
    private final String _name;
    private final String _emailAddress;
    private String _password;
    private String _secretKey;
    private String _publicKey; // file path to key
    private String _privateKey;
    private byte[] _publicKeyStream = null;
    private byte[] _privateKeyStream = null;

    public SystemUser(SystemUserAuthentication authUser) {
        this(authUser.getId(), authUser.getName(), authUser.getEmailAddress(), authUser.getKeyPassphrase());
    }

    public SystemUser(String id, String name, String emailAddress, String password) {
        _id = id;
        _name = name;
        _emailAddress = emailAddress;
        _password = password;
        _privateKey = String.format("%s\\%s_prv.asc", keysFolder, id);
        _publicKey = String.format("%s\\%s_pub.asc", keysFolder, id);
        
        // Create new SystemUser with private and public key
        RSAKeyGenerator keyGenerator = new RSAKeyGenerator();
        keyGenerator.createKeys(_privateKey, _publicKey, name, password); // will create keys in path below
        //_publicKey = "keys\\UserPublicKey.asc";
       // _privateKey = "keys\\UserPrivateKey.asc";
     
        
        setPublicKey();
        setPrivateKey();
    }

    public SystemUser(String id, String name, String emailAddress, byte[] publicKeyStream) {
        _id = id;
        _name = name;
        _emailAddress = emailAddress;
        _publicKeyStream = publicKeyStream;
    }

    public String getId() {
        return _id;
    }

    public String getName() {
        
        if(_name == null)
            return "Name";
        return _name;
    }

    public String getEmailAddress() {
        return _emailAddress;
    }

    public String getSecretKey() {
        return _secretKey;
    }

    public void setSecretKey(String key) {
        this._secretKey = key;
    }

    public byte[] getPublicKey() {
        return _publicKeyStream;
    }

    public Boolean hasSharedKey() {
        System.out.println("This is the shared key:");
        System.out.println(this._secretKey);
        return (this._secretKey != null);
    }

    public void removeSecretKey() {
        this._secretKey = null;
    }

    public byte[] getPrivateKey() {
        return _privateKeyStream;
    }

    private void setPublicKey() {
        try {
            _publicKeyStream = IOUtils.toByteArray(new FileInputStream(_publicKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setPrivateKey() {
        try {
            _privateKeyStream = IOUtils.toByteArray(new FileInputStream(_privateKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SystemUser cloneForSharing() {
        return new SystemUser(getId(), getName(), getEmailAddress(), getPublicKey());
    }

    public static SystemUser createClientUser(SystemUserAuthentication authUser) {
        return new SystemUser(authUser);
    }

    public static SystemUser createServerUser() {
        String id = HelperUtils.SERVER_ID;
        String name = "The Server";
        String email = "chatserver@cs.uct.ac.za";
        String password = "chatserver001";

        return new SystemUser(
                id,
                name,
                email,
                password);
    }
}
