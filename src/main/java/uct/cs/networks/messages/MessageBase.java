package uct.cs.networks.messages;

import java.io.Serializable;
import uct.cs.networks.enums.*;
import uct.cs.networks.interfaces.IMessageBody;
import uct.cs.networks.interfaces.IMessageHeader;
import uct.cs.networks.models.SystemUser;

/**
 *
 * @author Shaylin Chetty (CHTSHA042@myuct.ac.za)
 * @author Chipo Hamayobe (HMYCHI001@myuct.ac.za)
 * @author Orefile Morule (MRLORE001@myuct.ac.za)
 * @author Enock Shezi (SHZENO001@myuct.ac.za)
 */
public abstract class MessageBase implements Serializable {

    private IMessageHeader _header;
    private IMessageBody _body;

    public MessageBase(MessageType type, SystemUser sender, SystemUser receiver) {
        _header = new MessageHeader(type, sender, receiver);
        _body = new MessageBody();
    }

    public MessageBase(MessageType type, String message, SystemUser sender, SystemUser receiver) {
        this(type, sender, receiver);
        _body = new MessageBody(message);
    }

    public MessageBase(MessageType type, String data, String message, SystemUser sender, SystemUser receiver) {
        this(type, sender, receiver);
        _body = new MessageBody(data, message);
    }

    public IMessageHeader getHeader() {
        return _header;
    }

    public void setHeader(IMessageHeader header) {
        _header = header;
    }

    public IMessageBody getBody() {
        return _body;
    }

    public void setBody(IMessageBody body) {
        _body = body;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Type: %s\n", getHeader().getType()));
        sb.append(String.format("Class: %s\n", getClass().getName()));
        sb.append(String.format("Id: %s\n", getHeader().getId()));
        sb.append(String.format("Sender: %s\n", getHeader().getSenderId()));
        sb.append(String.format("Receiver: %s\n", getHeader().getReceiverId()));
        sb.append(String.format("Message: %s\n", getBody().getInfo()));

        return sb.toString();
    }

    /**
     *
     * @return
     */
    public String toClientString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(" %s |", getHeader().getTimestamp()));
        sb.append(String.format(" %s |", getHeader().getType()));
        sb.append(String.format(" %s |", getHeader().getId()));
        sb.append(String.format(" %s |", getHeader().getSenderId()));
        sb.append(String.format(" %s", getBody().getInfo()));

        return sb.toString();
    }

    public String toServerString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(" %s |", getHeader().getTimestamp()));
        sb.append(String.format(" %s |", getHeader().getType()));
        sb.append(String.format(" %s |", getHeader().getId()));
        sb.append(String.format(" %s |", getHeader().getSenderId()));
        sb.append(String.format(" %s |", getHeader().getReceiverId()));
        sb.append(String.format(" %s", getBody().getInfo()));

        return sb.toString();
    }
}
