package uct.cs.networks.interfaces;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public interface IMessageBody {
    
    public Object getData();
    
    public void setData(Object data);
    
    public String getInfo();
    
    public void setInfo(String info);
}
