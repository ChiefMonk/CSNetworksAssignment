package uct.cs.networks.messages;

import java.io.Serializable;
import uct.cs.networks.enums.*;
import uct.cs.networks.models.SystemUser;
import uct.cs.networks.utils.HelperUtils;

/**
 *
 * @author Shaylin Chetty (CHTSHA042@myuct.ac.za)
 * @author Chipo Hamayobe (HMYCHI001@myuct.ac.za)
 * @author Orefile Morule (MRLORE001@myuct.ac.za)
 * @author Enock Shezi (SHZENO001@myuct.ac.za)
 */
public abstract class MessageBase implements Serializable {
    private final String _id;
    private final String _timestamp;
    private final MessageType _type;
    private String _sender;
    private String _receiver;

    public MessageBase(MessageType type, SystemUser sender, SystemUser receiver) {
        _id = java.util.UUID.randomUUID().toString();
        _timestamp = HelperUtils.GetCuttentUtcTimestamp();
        _type = type;
        _sender = sender.getId();
        _receiver = sender.getId();

        if (sender != null)
            _sender = sender.getName();

        if (receiver != null)
            _receiver = receiver.getName();
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

    public String getMessageData() {
        String seperator = "|";

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s%s", getType(), seperator));
        sb.append(String.format("%s%s", getTimestamp(), seperator));
        sb.append(String.format("%s%s", getId(), seperator));
        sb.append(String.format("%s%s", getSender(), seperator));
        sb.append(String.format("%s%s", getReceiver(), seperator));

        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Tm: %s\n", getTimestamp()));
        sb.append(String.format("Tp: %s\n", getType()));
        sb.append(String.format("Id: %s\n", getId()));
        sb.append(String.format("Se: %s\n", getSender()));
        sb.append(String.format("Re: %s\n", getReceiver()));

        return sb.toString();
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
