package uct.cs.networks.ui;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import uct.cs.networks.interfaces.IMessage;
import uct.cs.networks.utils.MessageFactory;

public class ChatServer 
{
    private static final int PORT = 4026;
    private static Set<ObjectOutputStream> clientWriters = new HashSet<>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);
 
    public static void main(String[] args) 
    {    
        try (ServerSocket serverSocket = new ServerSocket(PORT))
        {                                     
        
            System.out.println(String.format("The Secure Chat Server is running on port %s ...", PORT));
            
            while (true) 
            {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                threadPool.execute(clientHandler);
            }
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable 
    {       
        private final Socket _secureSocket;
        private ObjectOutputStream outputStream;

        public ClientHandler(Socket secureSocket) 
        {
            _secureSocket = secureSocket;                       
        }

        @Override
        public void run() 
        {
            try {
              
                ObjectInputStream inputStream = new ObjectInputStream(_secureSocket.getInputStream());       
                outputStream = new ObjectOutputStream (_secureSocket.getOutputStream());

                synchronized (clientWriters) 
                {
                    clientWriters.add(outputStream);
                }
                  
               Object object;
                while ((object = inputStream.readObject()) != null) {
                    IMessage message = MessageFactory.getMessage(object);
                    System.out.println("Received: " + message);
                    broadcastMessage(message);
                }
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            } 
            catch (ClassNotFoundException ex) {
                Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
            } 
            finally 
            {
                try 
                {
                    _secureSocket.close();
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
                synchronized (clientWriters) 
                {
                    clientWriters.remove(outputStream);
                }
            }
        }

        private void broadcastMessage( IMessage message) throws IOException 
        {
            synchronized (clientWriters) 
            {
                for (ObjectOutputStream writer : clientWriters) 
                {
                    writer.writeObject(message);
                }
            }
        }
    }
}

