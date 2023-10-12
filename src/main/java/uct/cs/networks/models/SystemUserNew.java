package uct.cs.networks.models;

import java.io.Serializable;
import uct.cs.networks.utils.*;

/**
 *
 * @author Shaylin Chetty (CHTSHA042@myuct.ac.za)
 * @author Chipo Hamayobe (HMYCHI001@myuct.ac.za)
 * @author Orefile Morule (MRLORE001@myuct.ac.za)
 * @author Enock Shezi (SHZENO001@myuct.ac.za)
 */

public class SystemUserNew implements Serializable {
    private final String _id;
    private final String _name;
    private final String _emailAddress;
           
    private String _privatePassphrase = null;
    private byte[] _publicKey = null;
    private byte[] _privateKey = null;    
    private String _sessionSecretKey = null;   

    public SystemUserNew(SystemUserAuthentication authUser) {
        this(authUser.getId(), authUser.getName(), authUser.getEmailAddress(), authUser.getKeyPassphrase());
    }

    public SystemUserNew(String id, String name, String emailAddress, String privatePassphrase) {
        _id = id;
        _name = name;
        _emailAddress = emailAddress;
        _privatePassphrase = privatePassphrase;       
    }

    public SystemUserNew(String id, String name, String emailAddress, String privatePassphrase, byte[] privateKey, byte[] publicKey) {
        _id = id;
        _name = name;
        _emailAddress = emailAddress;
        _privatePassphrase = privatePassphrase;
        _privateKey = privateKey;
        _publicKey = publicKey;
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

    public String getPrivatePassphrase() {
        return _privatePassphrase;
    }   

    public byte[] getPublicKey() {
        return _publicKey;
    }
  
    public byte[] getPrivateKey() {
        return _privateKey;
    }
    
    public String getSessionSecretKey() {
        return _sessionSecretKey;
    }
    
    public void setSessionSecretKey(String sessionSecretKey) {
        _sessionSecretKey = sessionSecretKey;
    }
    
    public void clearSessionSecretKey() {
        _sessionSecretKey = null;
    }
    
    public boolean hasSessionWithUser() {
      return (_sessionSecretKey != null && !_sessionSecretKey.isBlank());
    }
    
    public SystemUserNew cloneForSharing()
    {
        return new SystemUserNew(getId(), getName(), getEmailAddress(), null, null, getPublicKey());
    }
    
    private static final String keysFolder = "keys";
    public static SystemUserNew createClientUser(SystemUserAuthentication authUser)
    {
        String privateKeyFile = String.format("%s\\%s_prv.asc", keysFolder, authUser.getId());
        String publicKeyFile = String.format("%s\\%s_pub.asc", keysFolder, authUser.getId());
            
         String identity = String.format("%s%s%s", authUser.getId(), authUser.getName(), authUser.getEmailAddress());   
         
        // RSAKeyGenerator generator = new RSAKeyGenerator();
       //  var isOk = generator.createKeys(privateKeyFile, publicKeyFile, identity, authUser.getKeyPassphrase(), false);
         
        // if(!isOk)
        //    return null;
         
       //  var privateBytes = RSAKeyGenerator.getKeyByteArray(privateKeyFile);
       //  var publicBytes = RSAKeyGenerator.getKeyByteArray(publicKeyFile);
         
        //  if(privateBytes == null || publicBytes == null)
         //   return null;
        
         return new SystemUserNew(
                 authUser.getId(), 
                 authUser.getName(), authUser.getEmailAddress(), 
                 authUser.getKeyPassphrase(), 
                 null, 
                 null);                  
    }
    
    public static SystemUserNew createServerUser()
    {
        String id = HelperUtils.SERVER_ID;
        String name = "The Server";
        String email = "chatserver@cs.uct.ac.za";
        String password = "chatserver001";
        
        String privateKeyFile = String.format("%s\\%s_prv.asc", keysFolder, id);
        String publicKeyFile = String.format("%s\\%s_pub.asc", keysFolder, id);
        
        String identity = String.format("%s%s%s", id, name, email, password);        
         
        //   RSAKeyGenerator generator = new RSAKeyGenerator();
       //  var isOk = generator.createKeys(privateKeyFile, publicKeyFile, identity, password, true);
         
       // if(!isOk)
        //    return null;
        
        // var privateBytes = RSAKeyGenerator.getKeyByteArray(privateKeyFile);
        // var publicBytes = RSAKeyGenerator.getKeyByteArray(publicKeyFile);
         
        //  if(privateBytes == null || publicBytes == null)
         //   return null;
        
         return new SystemUserNew(
                 id, 
                 name, 
                 email, 
                 password, 
                   null, 
                 null);                         
    }
}
