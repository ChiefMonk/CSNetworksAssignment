package uct.cs.networks.interfaces;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public interface IMessage {    
    
     public IMessageHeader getHeader();     
     public void setHeader(IMessageHeader header);
     
     public IMessageBody getBody();     
     public void setBody(IMessageBody body);
}
