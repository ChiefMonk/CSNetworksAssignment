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
public class ValidateCertificateMessage extends MessageBase implements IMessage {
    
    public ValidateCertificateMessage(String senderId, String receiverId) {
        super(Enums.MessageType.ValidateCertificate, senderId, receiverId);
    }
    
}
