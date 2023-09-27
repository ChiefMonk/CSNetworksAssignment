package uct.cs.networks.messages;

import uct.cs.networks.enums.Enums;
import uct.cs.networks.interfaces.IMessage;

/**
 *
 * @author Shaylin Chetty (CHTSHA042@myuct.ac.za)
 * @author Chipo Hamayobe (HMYCHI001@myuct.ac.za)
 * @author Orefile Morule (MRLORE001@myuct.ac.za)
 * @author Enock Shezi (SHZENO001@myuct.ac.za)
 */
public class SessionEndMessage extends MessageBase implements IMessage {
    
    public SessionEndMessage(String senderId, String receiverId) {
        super(Enums.MessageType.SessionEnd, senderId, receiverId);
    }
    
}