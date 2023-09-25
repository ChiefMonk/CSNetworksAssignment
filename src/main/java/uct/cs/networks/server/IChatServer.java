package uct.cs.networks.server;

import java.io.IOException;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public interface IChatServer {
 
    public void Start(int portNumber) throws IOException;
    
    public boolean Stop();
    
}
