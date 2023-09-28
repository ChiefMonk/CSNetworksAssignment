package uct.cs.networks.ui;

    import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 4026;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public ChatClient() {
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            // Create a thread for receiving messages from the server
            Thread serverListener = new Thread(new ServerListener());
            serverListener.start();

            // Read input from the console and send it to the server
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
            String userInput;
            while ((userInput = consoleInput.readLine()) != null) {
                output.println(userInput);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class ServerListener implements Runnable {
        @Override
        public void run() {
            try {
                String serverMessage;
                while ((serverMessage = input.readLine()) != null) {
                    System.out.println("Server: " + serverMessage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
       
       
        ChatClient client = new ChatClient();
    }
}
