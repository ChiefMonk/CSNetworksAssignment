package uct.cs.networks.server.models;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class SystemUser {    
    private final String _id;
    private final String _name;
    private String _ipAddress;
    private int _portNumber;
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
    
    public String getIpAddress() {
        return _ipAddress;
    }
     
    public int getPortNumber() {
        return _portNumber;
    }
    
    public String getPublicKey() {
        return _publicKey;
    }
}
