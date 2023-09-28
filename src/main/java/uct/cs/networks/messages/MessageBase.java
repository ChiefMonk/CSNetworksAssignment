package uct.cs.networks.messages;

import java.io.Serializable;
import uct.cs.networks.enums.Enums;
import uct.cs.networks.interfaces.IMessage;
import uct.cs.networks.interfaces.IMessageBody;
import uct.cs.networks.interfaces.IMessageHeader;

/**
 *
 * @author Shaylin Chetty (CHTSHA042@myuct.ac.za)
 * @author Chipo Hamayobe (HMYCHI001@myuct.ac.za)
 * @author Orefile Morule (MRLORE001@myuct.ac.za)
 * @author Enock Shezi (SHZENO001@myuct.ac.za)
 */
public abstract class MessageBase implements Serializable {
      
    private IMessageHeader _header;
    private IMessageBody _body;
    
    public MessageBase(Enums.MessageType type, String senderId, String receiverId)
    {
        _header = new MessageHeader(type, senderId, receiverId);
    }
    
    public MessageBase(Enums.MessageType type, String message)
    {
        _header = new MessageHeader(type, java.util.UUID.randomUUID().toString(), java.util.UUID.randomUUID().toString());
        _body = new MessageBody(message);
    }
    
    public MessageBase(Enums.MessageType type, Object data, String message)
    {
        _header = new MessageHeader(type, java.util.UUID.randomUUID().toString(), java.util.UUID.randomUUID().toString());
        _body = new MessageBody(data, message);
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
      
    @Override
   public String toString()
   {
       StringBuilder sb = new  StringBuilder();
       sb.append(String.format("Type: %s\n", getHeader().getType()));
       sb.append(String.format("Class: %s\n", getClass().getName()));
       sb.append(String.format("Id: %s\n", getHeader().getId()));
       sb.append(String.format("Sender: %s\n", getHeader().getSenderId()));
       sb.append(String.format("Receiver: %s\n", getHeader().getReceiverId()));
       sb.append(String.format("Message: %s\n", getBody().getInfo()));
    
       return sb.toString();
   }
}
