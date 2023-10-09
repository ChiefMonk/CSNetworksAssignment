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
public class SystemUserAuthenticationMessage extends MessageBase implements IMessage {
    
    private final SystemUser _user;
    public SystemUserAuthenticationMessage(SystemUser user) {
        super(MessageType.SystemUserAuth, user);
             
        _user = new SystemUser(user.getId(), user.getName(), user.getEmailAddress(), user.getPublicKey());
    } 
    
     public SystemUser getUser() {
        return _user;
    }
}
