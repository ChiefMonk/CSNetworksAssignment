package uct.cs.networks.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class SecureChatServer implements IChatServer {                
    private static Set<PrintWriter> _clientWriters = new HashSet<>();
    private boolean _isRunning;

    public SecureChatServer(int portNumber) {
       _isRunning = false;
    }   

    /**
     *
     *
     * @param portNumber
     * @throws IOException
     */
    @Override
    public void Start(int portNumber) throws IOException
    {
       writeToConsole(String.format("Starting the Secure Peer2Peer Chat Server on port %s", portNumber));
       
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) 
        {
            _isRunning = true;
            while (_isRunning) 
            {
                new ClientHandler(serverSocket.accept()).start();
            }
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }                
    }

    @Override
    public boolean Stop()
    {
        _isRunning = false;        
        return true;
    }
    
    
    private static int _messageCounter = 1;
    private void writeToConsole(String message)
    {
         System.out.println(String.format("%s: %s", _messageCounter++, message ));
    }
    
     private static class ClientHandler extends Thread {
        private Socket _socket;
        private PrintWriter _outWriter;
        private BufferedReader _inReader;

        public ClientHandler(Socket socket) {
            this._socket = socket;
        }

        public void run() {
            try {
                _inReader = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
                _outWriter = new PrintWriter(_socket.getOutputStream(), true);

                synchronized (_clientWriters) {
                    _clientWriters.add(_outWriter);
                }

                String message;
                while ((message = _inReader.readLine()) != null) 
                {
                    System.out.println("Received: " + message);
                    broadcastMessage(message);
                }
            } catch (IOException e) 
            {
                e.printStackTrace();
            } 
            finally 
            {
                try 
                {
                    _socket.close();
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
                synchronized (_clientWriters) 
                {
                    _clientWriters.remove(_outWriter);
                }
            }
        }

        private void broadcastMessage(String message) {
            synchronized (_clientWriters) {
                for (PrintWriter writer : _clientWriters) {
                    writer.println(message);
                }
            }
        }
    }
}
