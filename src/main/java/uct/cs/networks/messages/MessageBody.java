package uct.cs.networks.messages;

import org.bouncycastle.bcpg.attr.ImageAttribute;
import java.security.Key;

import uct.cs.networks.interfaces.IMessageBody;
import uct.cs.networks.utils.ImageProcessor;

/**
 *
 * @author Shaylin Chetty (CHTSHA042@myuct.ac.za)
 * @author Chipo Hamayobe (HMYCHI001@myuct.ac.za)
 * @author Orefile Morule (MRLORE001@myuct.ac.za)
 * @author Enock Shezi (SHZENO001@myuct.ac.za)
 */

public class MessageBody implements IMessageBody {

    private String _info; // Caption
    private String _data; // Image -> hashed image
    private Key _key; // A symmetric key (if used)

    public MessageBody() {
    }

    public MessageBody(String info) {
        _info = info;
    }

    public MessageBody(String info, Key key) {
        _info = info;
        _key = key;
    }

    public MessageBody(String data, String info) {
        ImageProcessor imageProcessor = new ImageProcessor();
        _info = info;
        try {
            _data = imageProcessor.encodeImage(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getData() {
        return _data;
    }

    @Override
    public void setData(String data) {
        _data = data;
    }

    @Override
    public String getInfo() {
        return _info;
    }

    @Override
    public void setInfo(String info) {
        _info = info;
    }

    @Override
    public Key getKey() {
        return _key;
    }

    @Override
    public void setKey(Key key) {
        _key = key;
    }

}
