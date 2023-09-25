package uct.cs.networks.ui;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class ChatServer 
{
    private static final int PORT = 4026;
    private static Set<PrintWriter> clientWriters = new HashSet<>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) 
    {
        System.out.println(String.format("The Chat Server is running on port %s ...", PORT));
        try (ServerSocket serverSocket = new ServerSocket(PORT)) 
        {
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
        private Socket socket;
        private PrintWriter out;

        public ClientHandler(Socket socket) 
        {
            this.socket = socket;
        }

        public void run() 
        {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                synchronized (clientWriters) 
                {
                    clientWriters.add(out);
                }

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Received: " + message);
                    broadcastMessage(message);
                }
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            } 
            finally 
            {
                try 
                {
                    socket.close();
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
                synchronized (clientWriters) 
                {
                    clientWriters.remove(out);
                }
            }
        }

        private void broadcastMessage(String message) 
        {
            synchronized (clientWriters) 
            {
                for (PrintWriter writer : clientWriters) 
                {
                    writer.println(message);
                }
            }
        }
    }
}

