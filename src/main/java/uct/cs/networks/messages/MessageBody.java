package uct.cs.networks.messages;

import uct.cs.networks.interfaces.IMessageBody;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class MessageBody implements IMessageBody  {

    private String _info;
    private Object _data;  
    
    public MessageBody()
    {       
    }
    
    public MessageBody(String info)
    {
        _info = info;       
    }
    
    public MessageBody(Object data, String info)
    {
        _info = info;  
        _data = data;       
    }
    
    @Override
    public Object getData() {
        return _data;
    }

    @Override
    public void setData(Object data) {
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
    
}
