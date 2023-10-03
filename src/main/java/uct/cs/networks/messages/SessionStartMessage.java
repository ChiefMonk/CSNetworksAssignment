package uct.cs.networks.messages;

import uct.cs.networks.enums.*;
import uct.cs.networks.interfaces.*;
import uct.cs.networks.models.SystemUser;

/**
 *
 * @author Shaylin Chetty (CHTSHA042@myuct.ac.za)
 * @author Chipo Hamayobe (HMYCHI001@myuct.ac.za)
 * @author Orefile Morule (MRLORE001@myuct.ac.za)
 * @author Enock Shezi (SHZENO001@myuct.ac.za)
 */
public class SessionStartMessage extends MessageBase implements IMessage {

    public SessionStartMessage(SystemUser sender, SystemUser receiver)
    {
        super(MessageType.SessionStart, sender, receiver);
    }
}
