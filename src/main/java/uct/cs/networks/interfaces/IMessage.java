package uct.cs.networks.interfaces;

import java.io.Serializable;
import uct.cs.networks.enums.MessageType;


/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public interface IMessage extends Serializable {    
    
    public String getId();   
    public String getTimestamp();     
    public MessageType getType();   
    public String getSender();
    public String getReceiver();
    
    public String getMessageData();
    public String toClientString();
    public String toServerString();
}
