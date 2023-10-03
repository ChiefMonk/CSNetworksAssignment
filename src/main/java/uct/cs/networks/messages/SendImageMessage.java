package uct.cs.networks.messages;

import uct.cs.networks.enums.*;
import uct.cs.networks.interfaces.IMessage;
import uct.cs.networks.models.SystemUser;
import uct.cs.networks.utils.HelperUtils;

/**
 *
 * @author Shaylin Chetty (CHTSHA042@myuct.ac.za)
 * @author Chipo Hamayobe (HMYCHI001@myuct.ac.za)
 * @author Orefile Morule (MRLORE001@myuct.ac.za)
 * @author Enock Shezi (SHZENO001@myuct.ac.za)
 */
public class SendImageMessage extends MessageBase implements IMessage {
    
    private byte[] _imageData;
    
    public SendImageMessage( byte[] imageData, String message, SystemUser sender, SystemUser receiver) {
        super(MessageType.SendImage, sender, receiver);    
        _imageData = imageData;
    }    
}
