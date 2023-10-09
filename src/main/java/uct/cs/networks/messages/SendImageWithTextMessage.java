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
public class SendImageWithTextMessage extends MessageBase implements IMessage {
    
    private final String _imageText;
    private final byte[] _imageData;
    
    public SendImageWithTextMessage(byte[] imageData, String imageText, SystemUser sender, SystemUser receiver) {
        super(MessageType.SendImageWithText, sender, receiver);    
        
        _imageData = imageData;
        _imageText = imageText;
    }    
    
     public String getImageText() {
        return _imageText;
    }
     
    public byte[] getImageData() {
        return _imageData;
    }
}
