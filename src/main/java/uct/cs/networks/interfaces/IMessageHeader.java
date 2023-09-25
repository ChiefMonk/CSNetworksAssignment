package uct.cs.networks.interfaces;

import uct.cs.networks.enums.Enums;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public interface IMessageHeader {    
    public String getId();
    public String getTimestamp();
    public Enums.MessageType getType();     
    public Enums.MessageFormat getFormat();    
    public String getSenderId();
    public String getReceiverId();
}
