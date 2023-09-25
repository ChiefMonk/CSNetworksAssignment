package uct.cs.networks.messages;

import uct.cs.networks.enums.Enums;
import uct.cs.networks.interfaces.IMessageHeader;
import uct.cs.networks.utils.HelperUtils;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class MessageHeader implements IMessageHeader {

    private final String _id;
    private final String _timestamp;
    private final Enums.MessageType _type;
    private final Enums.MessageFormat _format; 
    private final String _senderId;
    private final String _receiverId;
    
    public MessageHeader(Enums.MessageType type, Enums.MessageFormat format, String senderId, String receiverId)
    {
        _id = java.util.UUID.randomUUID().toString();
        _timestamp = HelperUtils.GetCuttentUtcTimestamp();
        _type = type;
        _format = format;
        _senderId = senderId;
        _receiverId = receiverId;
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
    public Enums.MessageFormat getFormat() {
       return _format;
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
