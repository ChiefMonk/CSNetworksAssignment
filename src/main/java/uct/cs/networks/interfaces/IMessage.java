package uct.cs.networks.interfaces;

import java.io.Serializable;


/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public interface IMessage extends Serializable {    
    
     public IMessageHeader getHeader();     
     public void setHeader(IMessageHeader header);
     
     public IMessageBody getBody();     
     public void setBody(IMessageBody body);          
}
