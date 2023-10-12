package uct.cs.networks.proto;

import java.io.IOException;
import java.io.Serializable;
import uct.cs.networks.enums.MessageType;
import uct.cs.networks.interfaces.IMessage;
import uct.cs.networks.utils.EncryptionHelper;
import uct.cs.networks.utils.HelperUtils;
import uct.cs.networks.models.SystemUser;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class MessageProtocol implements Serializable {
    private final String _id;
    private final String _timestamp;
    private final MessageType _type;
    private final String _sender; // User ID
    private final String _receiver; // User ID
    private Object _cipherBody;

    public MessageProtocol(IMessage message, boolean createHash, SystemUser receiver) throws IOException {
        _id = message.getId();
        _timestamp = HelperUtils.GetCuttentUtcTimestamp();
        _type = message.getType();
        _sender = message.getSender();
        _receiver = message.getReceiver();

        ProtocolBody body;

        if (createHash)
            body = new ProtocolBody(message, EncryptionHelper.createMessageDigest(message));
        else
            body = new ProtocolBody(message, null);

        String bodyString = HelperUtils.convertProtocolBodyToBase64String(body);
        String encryptedBodyString = null;
        // Type of encryption depends on type of messaged (normal message uses shared
        // key, new connection = RSA key)

        switch (message.getType()) {
            case SessionStart -> {
                encryptedBodyString = bodyString; // EncryptionHelper.encryptwithPublicKey(bodyString, receiver);
            }
            case SendText -> {
                encryptedBodyString = bodyString;// EncryptionHelper.encryptWithSharedKey(bodyString, receiver);
            }
            case SendImageWithText -> {
                encryptedBodyString = EncryptionHelper.encryptWithSharedKey(bodyString, receiver);
            }
            case SessionEnd -> {
                encryptedBodyString = EncryptionHelper.encryptWithSharedKey(bodyString, receiver);
            }
            case SystemUserAuth -> { // This should be server locked but then we need broadcast type
                encryptedBodyString = bodyString; // EncryptionHelper.encryptwithPublicKey(bodyString, "server");
            }
            case ValidateCertRequest -> // Done with public key as there is no "session" with server
            {
                encryptedBodyString = EncryptionHelper.encryptwithPublicKey(bodyString, receiver);
            }
            case ValidateCertResponse -> // Need to encrypt with server info
            {
                encryptedBodyString = bodyString;
            }
            case BroadcastUserList -> {
                encryptedBodyString = bodyString; // No encryption for now
            }
            case Unknown -> {
                encryptedBodyString = bodyString;
            }

        }
        if (encryptedBodyString != null) {
            _cipherBody = encryptedBodyString;
        } else {
            _cipherBody = bodyString;
        }
    }

    public String getId() {
        return _id;
    }

    public String getTimestamp() {
        return _timestamp;
    }

    public MessageType getType() {
        return _type;
    }

    public String getSender() {
        return _sender;
    }

    public String getReceiver() {
        return _receiver;
    }

    public Object getCipherBody() {
        return _cipherBody;
    }

    /**
     *
     * @return
     */
    public String toClientString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(" %s |", getTimestamp()));
        sb.append(String.format(" %s |", getType()));
        sb.append(String.format(" %s |", getId()));
        sb.append(String.format(" %s -> %s |", getSender(), getReceiver()));

        return sb.toString();
    }

    public String toServerString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(" %s |", getTimestamp()));
        sb.append(String.format(" %s |", getType()));
        sb.append(String.format(" %s |", getId()));
        sb.append(String.format(" %s -> %s |", getSender(), getReceiver()));

        return sb.toString();
    }
}
