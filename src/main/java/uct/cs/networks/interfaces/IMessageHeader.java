package uct.cs.networks.interfaces;

import java.io.Serializable;
import uct.cs.networks.enums.*;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public interface IMessageHeader extends Serializable {    
    public String getId();
    public String getTimestamp();
    public MessageType getType();   
    public String getSenderId();
    public String getReceiverId();
}
