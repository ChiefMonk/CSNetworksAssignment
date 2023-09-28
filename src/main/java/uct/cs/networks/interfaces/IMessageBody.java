package uct.cs.networks.interfaces;

import java.io.Serializable;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public interface IMessageBody extends Serializable {
    
    public Object getData();
    
    public void setData(Object data);
    
    public String getInfo();
    
    public void setInfo(String info);
}
