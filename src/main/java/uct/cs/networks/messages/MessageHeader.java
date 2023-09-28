package uct.cs.networks.messages;

import uct.cs.networks.enums.Enums;
import uct.cs.networks.interfaces.IMessageHeader;

/**
 *
 * @author Shaylin Chetty (CHTSHA042@myuct.ac.za)
 * @author Chipo Hamayobe (HMYCHI001@myuct.ac.za)
 * @author Orefile Morule (MRLORE001@myuct.ac.za)
 * @author Enock Shezi (SHZENO001@myuct.ac.za)
 */
public class MessageHeader implements IMessageHeader {

    private final String _id;
    private final String _timestamp;
    private final Enums.MessageType _type;  
    private final String _senderId;
    private final String _receiverId;
    
    public MessageHeader(Enums.MessageType type, String senderId, String receiverId)
    {
        _id = java.util.UUID.randomUUID().toString();
        _timestamp = java.util.UUID.randomUUID().toString();
        _type = type;       
        _senderId = "Chipo";
        _receiverId = "God";
    }
    
    @Override
    public String getId() {
        return _id;
    }

    @Override
    public String getTimestamp() {
        return _timestamp;
    }

    @Override
    public Enums.MessageType getType() {
       return _type;
    }   

    @Override
    public String getSenderId() {
        return _senderId;
    }
    
    @Override
    public String getReceiverId() {
       return _receiverId;
    }    
}
