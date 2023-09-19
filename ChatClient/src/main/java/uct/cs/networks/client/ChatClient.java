package uct.cs.networks.client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to Chat Server.");
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();
            out.println(username);

            Thread receiverThread = new Thread(() -> {
                String message;
                try {
                    while ((message = in.readLine()) != null) {
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            receiverThread.start();

            String userMessage;
            while (true) {
                userMessage = scanner.nextLine();
                if (userMessage.equalsIgnoreCase("/exit")) {
                    break;
                }
                out.println(username + ": " + userMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
