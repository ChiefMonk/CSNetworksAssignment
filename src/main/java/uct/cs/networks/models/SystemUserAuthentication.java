package uct.cs.networks.models;

import java.io.Serializable;


/**
 *
 * @author Shaylin Chetty (CHTSHA042@myuct.ac.za)
 * @author Chipo Hamayobe (HMYCHI001@myuct.ac.za)
 * @author Orefile Morule (MRLORE001@myuct.ac.za)
 * @author Enock Shezi (SHZENO001@myuct.ac.za)
 */

public class SystemUserAuthentication implements Serializable {
    private final String _serverIpAddress;
    private final int _portNumber;
    private final String _id;
    private final String _name;
    private final String _emailAddress;
    private final String _keyPassphrase;     

    public SystemUserAuthentication(String serverIpAddress, int portNumber, String name, String emailAddress, String keyPassphrase) {
         _serverIpAddress = serverIpAddress;
         _portNumber = portNumber;
        _id = java.util.UUID.randomUUID().toString();
        _name = name;
        _emailAddress = emailAddress;
        _keyPassphrase = keyPassphrase;       
    }
    
    public String getServerIpAddress() {
        return _serverIpAddress;
    }
     
    public int getPortNumber() {
        return _portNumber;
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

    public String getKeyPassphrase() {
        return _keyPassphrase;
    }
  
}
