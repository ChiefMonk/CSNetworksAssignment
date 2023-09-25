package uct.cs.networks.messages;

import uct.cs.networks.enums.Enums;
import uct.cs.networks.interfaces.*;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class SessionStartMessage extends MessageBase implements IMessage {

    public SessionStartMessage(Enums.MessageType type, Enums.MessageFormat format, String senderId, String receiverId)
    {
        super(type, format, senderId, receiverId);
    }
}
