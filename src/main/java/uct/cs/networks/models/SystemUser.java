package uct.cs.networks.models;

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
    private String _secretKey;
    private String _publicKey; // file path to key
    private FileInputStream _publicKeyStream = null;
   
    public SystemUser(SystemUserAuthentication authUser) {
         this(authUser.getId(), authUser.getName(), authUser.getEmailAddress(), authUser.getKeyPassphrase());        
    }
     
    public SystemUser (String id, String name, String emailAddress, String password)
    {
        _id = id;
        _name = name;
        _emailAddress = emailAddress;             
            // Create new SystemUser with private and public key
        RSAKeyGenerator keyGenerator = new RSAKeyGenerator();
        keyGenerator.createKeys(name, password); // will create keys in path below
        _publicKey = "keys\\UserPublicKey.asc";     
        setPublicKey();
    }
    
    public SystemUser (String id, String name, String emailAddress, FileInputStream publicKeyStream)
    {
        _id = id;
        _name = name;
        _emailAddress = emailAddress;                      
        _publicKeyStream = publicKeyStream;        
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
        return _publicKeyStream;
    }

    private void setPublicKey() 
    {      
        try {
            _publicKeyStream = new FileInputStream(_publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }      
    }
}
