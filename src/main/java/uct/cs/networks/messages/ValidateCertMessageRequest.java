package uct.cs.networks.messages;

import uct.cs.networks.enums.*;
import uct.cs.networks.interfaces.IMessage;
import uct.cs.networks.models.SystemUser;

/**
 *
 * @author Shaylin Chetty (CHTSHA042@myuct.ac.za)
 * @author Chipo Hamayobe (HMYCHI001@myuct.ac.za)
 * @author Orefile Morule (MRLORE001@myuct.ac.za)
 * @author Enock Shezi (SHZENO001@myuct.ac.za)
 */
public class ValidateCertMessageRequest extends MessageBase implements IMessage {
    
    public ValidateCertMessageRequest(SystemUser sender, SystemUser receiver) {
        super(MessageType.ValidateCertRequest, sender, receiver);
    }    
}