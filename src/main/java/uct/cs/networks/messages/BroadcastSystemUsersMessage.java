package uct.cs.networks.messages;
/*
            Usage: Use this to send a user object to each client currently connected to the server
            Should take in user object and create message out of it
            Should add user object to userList of each client and update GUI to show user
 */

import java.util.List;
import uct.cs.networks.enums.MessageType;
import uct.cs.networks.interfaces.IMessage;
import uct.cs.networks.models.SystemUser;

/**
 *
 * @author Shaylin Chetty (CHTSHA042@myuct.ac.za)
 * @author Chipo Hamayobe (HMYCHI001@myuct.ac.za)
 * @author Orefile Morule (MRLORE001@myuct.ac.za)
 * @author Enock Shezi (SHZENO001@myuct.ac.za)
 */
public class BroadcastSystemUsersMessage extends MessageBase implements IMessage {

    private final List<SystemUser> _userList; // the new user to be broadcasted

    public BroadcastSystemUsersMessage(SystemUser sender, SystemUser receiver, List<SystemUser> users) {
        super(MessageType.ServerBroadcastUserList, sender, receiver);
        _userList = users;
    }

    public List<SystemUser> getUserList() {
        return _userList;
    }
}
