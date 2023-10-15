package uct.cs.networks.messages;

import java.util.List;
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
public class ValidateCertMessageRequest extends MessageBase implements IMessage {

    private final SystemUser _verifyUser;

    public ValidateCertMessageRequest(SystemUser sender, SystemUser receiver, SystemUser verifyUser) {
        super(MessageType.ValidateCertRequest, sender, receiver);
        _verifyUser = verifyUser;
    }

    public SystemUser getVerifyUser() {
        return _verifyUser;
    }

    /**
     *
     * @return
     */
    @Override
    public String toClientString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toClientString());
        sb.append(String.format("Verify Public Certificate of %s |", _verifyUser.getName()));

        return sb.toString();
    }

    @Override
    public String toServerString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Verify Public Certificate of %s |", _verifyUser.getName()));

        return sb.toString();
    }
}
