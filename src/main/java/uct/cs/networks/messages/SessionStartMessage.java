package uct.cs.networks.messages;

import uct.cs.networks.utils.*;
import java.security.Key;

/*
            Start interaction with new user
            This should do (1) send user object to recieptient to create a SystemUser on their side with public key included 
                           (2) Create and share symmetric key
 */

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

    private Key sessionKey;

    public SessionStartMessage(SystemUser sender, SystemUser receiver, Key key) {
        super(MessageType.SessionStart, sender, receiver);
        this.sessionKey = key;

        AESEncryption aesEncryption = new AESEncryption();
        // Key key;
        // try {
        // key = aesEncryption.getKeyFromKeyGenerator();
        // this.sessionKey = key;
        // System.out.println("Secret key:");
        // System.out.println(HelperUtils.byteArray2String(key.getEncoded()));
        // System.out.println(aesEncryption.serializeKeyToString(key) + "(shared key)");

        // Create new messageBody and update original
        // MessageBody newBody = new MessageBody("Incoming chat with " +
        // setBody(newBody);
        // } catch (Exception e) {
        // e.printStackTrace();
        // }

    }

    public Key getSessionKey() {
        return sessionKey;
    }

    public String getRecieverID() {
        return super.getReceiver();
    }

    /**
     *
     * @return
     */
    @Override
    public String toClientString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toClientString());
        sb.append("Wants to start a chat |");

        return sb.toString();
    }

    @Override
    public String toServerString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Wants to start a chat |");

        return sb.toString();
    }
}
