package uct.cs.networks.proto;

import java.io.Serializable;
import uct.cs.networks.interfaces.IMessage;


/**
 *
 * @author Shaylin Chetty (CHTSHA042@myuct.ac.za)
 * @author Chipo Hamayobe (HMYCHI001@myuct.ac.za)
 * @author Orefile Morule (MRLORE001@myuct.ac.za)
 * @author Enock Shezi (SHZENO001@myuct.ac.za)
 */

public class ProtocolBody implements Serializable {

    private IMessage _message;
    private String _messageDigest;   

    public ProtocolBody(IMessage message, String messageDigest) {
        _message = message;
        _messageDigest = messageDigest;
    }
      
    public IMessage getMessage() {
        return _message;
    }
      
    public String getMessageDigest() {
        return _messageDigest;
    }   
}
