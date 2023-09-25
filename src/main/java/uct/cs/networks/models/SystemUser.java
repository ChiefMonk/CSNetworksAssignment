package uct.cs.networks.models;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class SystemUser {    
    private final String _id;
    private final String _name;  
    private String _secretKey;
    private String _publicKey;
    
    public SystemUser(String name) {
        _id = java.util.UUID.randomUUID().toString();
        _name = name;
    }
    
    public String getId() {
        return _id;
    }
    
    public String getName() {
        return _name;
    }
       
    public String getSecretKey() {
        return _secretKey;
    }
    
    public String getPublicKey() {
        return _publicKey;
    }
}
