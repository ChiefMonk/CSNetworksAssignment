package uct.cs.networks.messages;

import uct.cs.networks.enums.Enums;
import uct.cs.networks.interfaces.IMessageBody;
import uct.cs.networks.interfaces.IMessageHeader;

/**
 *
 * @author Shaylin Chetty (CHTSHA042@myuct.ac.za)
 * @author Chipo Hamayobe (HMYCHI001@myuct.ac.za)
 * @author Orefile Morule (MRLORE001@myuct.ac.za)
 * @author Enock Shezi (SHZENO001@myuct.ac.za)
 */
public abstract class MessageBase {
      
    private IMessageHeader _header;
    private IMessageBody _body;
    
    public MessageBase(Enums.MessageType type, String senderId, String receiverId)
    {
        _header = new MessageHeader(type, senderId, receiverId);
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
