package uct.cs.networks.messages;

import java.io.FileInputStream;
import java.nio.charset.Charset;

import org.bouncycastle.bcpg.CompressionAlgorithmTags;
import org.bouncycastle.bcpg.SymmetricKeyAlgorithmTags;

import uct.cs.networks.enums.*;
import uct.cs.networks.interfaces.IMessage;
import uct.cs.networks.models.SystemUser;
import uct.cs.networks.utils.PgpEncryptionUtil;

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
        _cipherText = encryptMessage(message, receiver);
    }

    public Object getCipherText() {
        return _cipherText;
    }

    private String ByteArray2String(byte[] a) {
        String text = "";
        for (int i = 0; i < a.length; i++) {
            text = text + a[i] + " ";
        }
        return text;
    }

    private Object encryptMessage(String message, SystemUser receiver) {
        PgpEncryptionUtil pgpEncryptionUtil = null;
        pgpEncryptionUtil = PgpEncryptionUtil.builder()
                .armor(true)
                .compressionAlgorithm(CompressionAlgorithmTags.ZIP)
                .symmetricKeyAlgorithm(SymmetricKeyAlgorithmTags.AES_128)
                .withIntegrityCheck(true)
                .build();
        try {
            byte[] encryptedBytes = pgpEncryptionUtil.encrypt(message.getBytes(Charset.defaultCharset()),
                    receiver.getPublicKey());
            return ByteArray2String(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}