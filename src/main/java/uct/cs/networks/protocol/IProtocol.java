package uct.cs.networks.protocol;
import uct.cs.networks.interfaces.*;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public interface IProtocol {  
    String getDestination();
    public IMessageHeader getHeader();
    public IMessageBody getBody();   
}
