package uct.cs.networks.proto;

import java.io.IOException;
import java.io.Serializable;
import uct.cs.networks.enums.MessageType;
import uct.cs.networks.interfaces.IMessage;
import uct.cs.networks.utils.EncryptionHelper;
import uct.cs.networks.utils.HelperUtils;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class MessageProtocol implements Serializable {        
    private final String _id;
    private final String _timestamp;
    private final MessageType _type;    
    private final String _sender;
    private final String _receiver;
    
    private Object _cipherBody;
    
    public MessageProtocol(IMessage message, boolean createHash) throws IOException  {
        _id = message.getId();      
        _timestamp = HelperUtils.GetCuttentUtcTimestamp();
        _type = message.getType();  
        _sender = message.getSender();  
        _receiver = message.getReceiver();  
        
        ProtocolBody body;     
        
        if(createHash)
            body =  new ProtocolBody(message, EncryptionHelper.createMessageDigest(message));
        else 
            body =  new ProtocolBody(message, null);
      
        String bodyString = HelperUtils.convertProtocolBodyToBase64String(body);
        
        //_cipherBody = encrypted bodyString
        _cipherBody = bodyString;
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
