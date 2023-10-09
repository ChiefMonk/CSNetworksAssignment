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
public class SendTextMessage extends MessageBase implements IMessage {

    private final String _textData;

    public SendTextMessage(String textData, SystemUser sender, SystemUser receiver) {
        super(MessageType.SendText, sender, receiver);
        
        _textData = textData;
    }

    public String getTextData() {
        return _textData;
    }

}