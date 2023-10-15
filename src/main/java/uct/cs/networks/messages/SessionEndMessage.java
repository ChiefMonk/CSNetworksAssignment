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
public class SessionEndMessage extends MessageBase implements IMessage {
    
    public SessionEndMessage(SystemUser sender, SystemUser receiver) {
        super(MessageType.SessionEnd, sender, receiver);
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toClientString() {        
        StringBuilder sb = new StringBuilder();
        sb.append(super.toClientString());
        sb.append("Ended the chat |");       

        return sb.toString();
    }

    @Override
    public String toServerString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ended the chat |");              

        return sb.toString();
    }
}
