package uct.cs.networks.models;

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
    private String _publicKey;
    
    public SystemUser(String name, String emailAddress) {
        _id = java.util.UUID.randomUUID().toString();
        _name = name;
        _emailAddress = emailAddress;
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
    
    public String getPublicKey() {
        return _publicKey;
    }
}
