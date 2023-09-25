package uct.cs.networks.messages;

import uct.cs.networks.enums.Enums;
import uct.cs.networks.interfaces.IMessageBody;
import uct.cs.networks.interfaces.IMessageHeader;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public abstract class MessageBase {
      
    private IMessageHeader _header;
    private IMessageBody _body;
    
    public MessageBase(Enums.MessageType type, Enums.MessageFormat format, String senderId, String receiverId)
    {
        _header = new MessageHeader(type, format, senderId, receiverId);
    }
    
    public IMessageHeader getHeader() {
       return _header;
    }
   
    public void setHeader(IMessageHeader header) {
       _header = header;
    }
   
    public IMessageBody getBody() {
        return _body;
    }
   
    public void setBody(IMessageBody body) {
       _body = body;
    }
}
