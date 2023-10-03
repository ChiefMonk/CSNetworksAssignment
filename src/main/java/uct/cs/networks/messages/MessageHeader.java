package uct.cs.networks.messages;

import uct.cs.networks.enums.*;
import uct.cs.networks.interfaces.IMessageHeader;
import uct.cs.networks.models.SystemUser;
import uct.cs.networks.utils.HelperUtils;

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
    private final MessageType _type;  
    private final String _senderId;
    private final String _receiverId;
    
    public MessageHeader(MessageType type, SystemUser sender, SystemUser receiver)
    {
        _id = java.util.UUID.randomUUID().toString();
        _timestamp = HelperUtils.GetCuttentUtcTimestamp();
        _type = type;       
        _senderId = sender.getId();
        _receiverId = receiver.getId();
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
    public MessageType getType() {
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
