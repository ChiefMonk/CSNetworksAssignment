package uct.cs.networks.messages;

import java.io.FileInputStream;
import java.nio.charset.Charset;

import org.bouncycastle.bcpg.CompressionAlgorithmTags;
import org.bouncycastle.bcpg.SymmetricKeyAlgorithmTags;

import uct.cs.networks.enums.*;
import uct.cs.networks.interfaces.IMessage;
import uct.cs.networks.models.SystemUser;
import uct.cs.networks.utils.PgpDecryptionUtil;
import uct.cs.networks.utils.PgpEncryptionUtil;
import uct.cs.networks.utils.EncryptionHelper;

/**
 *
 * @author Shaylin Chetty (CHTSHA042@myuct.ac.za)
 * @author Chipo Hamayobe (HMYCHI001@myuct.ac.za)
 * @author Orefile Morule (MRLORE001@myuct.ac.za)
 * @author Enock Shezi (SHZENO001@myuct.ac.za)
 */
public class SendTextMessage extends MessageBase implements IMessage {

    private Object _cipherText;

    public SendTextMessage(String message, SystemUser sender, SystemUser receiver) {
        super(MessageType.SendText, message, sender, receiver);
    }

    public Object getCipherText() {
        return _cipherText;
    }

}