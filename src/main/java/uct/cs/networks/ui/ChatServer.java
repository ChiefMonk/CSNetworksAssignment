package uct.cs.networks.ui;

import com.formdev.flatlaf.FlatLightLaf;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.swing.JOptionPane;
import uct.cs.networks.enums.MessageType;
import uct.cs.networks.interfaces.IMessage;
import uct.cs.networks.messages.*;
import uct.cs.networks.models.SystemUser;
import uct.cs.networks.proto.MessageProtocol;
import uct.cs.networks.proto.ProtocolBody;
import uct.cs.networks.utils.CompressionHelper;
import uct.cs.networks.utils.HelperUtils;
import uct.cs.networks.utils.MessageFactory;
import uct.cs.networks.utils.EncryptionHelper;

/**
 * The ChatClientGUI is the main class for the Desktop Application.
 * 
 * @author Shaylin Chetty (CHTSHA042@myuct.ac.za)
 * @author Chipo Hamayobe (HMYCHI001@myuct.ac.za)
 * @author Orefile Morule (MRLORE001@myuct.ac.za)
 * @author Enock Shezi (SHZENO001@myuct.ac.za)
 * 
 * @version 1.0.1
 * @since 2023-09-21
 */
public class ChatServer extends javax.swing.JFrame {

    private static final String TITLE = "- SecureChatSystem SERVER : Demonstrating Secure Network Communication with Cryptographic Functions -";
    private static final String ERROR_DEFAULT_TITLE = "Application Error Occurred";
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(20);
    private static final int PORT_NUMBER = 4026;

    private SystemUser _serverUser;
    private ServerSocket _serverSocket = null;
    private Set<ChatClientHandler> _chatClientList = null;

    /**
     * Creates new form ToolGUI
     */
    public ChatServer() throws IOException {
        super(TITLE);
        initComponents();
        this.setTitle("<html><body><center>" + TITLE + "</center></body></html>");
        this.setResizable(true);
        this.setVisible(true);

        this.setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                ButtonExitApplicationActionPerformed(null);
            }
        });

        loadServerUser();
        _chatClientList = new HashSet<>();
        _serverSocket = new ServerSocket(PORT_NUMBER);
    }

    private void setCurrentTitle(String ipAddress) {
        String t = TITLE;
        if (ipAddress != null && !ipAddress.isBlank())
            t = String.format("%s : Listening on %s:%s", TITLE, ipAddress, PORT_NUMBER);

        this.setTitle("<html><body><center>" + t + "</center></body></html>");
    }

    private void loadServerUser() {
        _serverUser = new SystemUser(HelperUtils.SERVER_ID, "The Server", "chatserver@cs.uct.ac.za", "chatserver001");
    }

    private String getIpAddress() {
        String ipAddress = null;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                // System.out.println(networkInterface.getName());
                // Filter for Wi-Fi adapters (you can change this to suit your needs)
                if (networkInterface.getName().startsWith("wlan") || networkInterface.getName().startsWith("eth")) {
                    Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();

                    while (inetAddresses.hasMoreElements()) {
                        InetAddress inetAddress = inetAddresses.nextElement();
                        // System.out.println(inetAddress.getHostAddress());
                        // Check for IPv4 addresses and exclude loopback address
                        if (!inetAddress.isLoopbackAddress() && inetAddress.getAddress().length == 4
                                && inetAddress.getHostAddress().length() < 15) {
                            ipAddress = inetAddress.getHostAddress();
                            break;
                        }
                    }
                }

                if (ipAddress != null)
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "127.0.0.1";
        }

        return ipAddress;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        PanelMain = new javax.swing.JPanel();
        PanelOutputExplanations = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        TextAreaMain = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelMain.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        PanelOutputExplanations
                .setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 204, 255)));

        TextAreaMain.setEditable(false);
        TextAreaMain.setColumns(20);
        TextAreaMain.setRows(5);
        TextAreaMain.setFocusable(false);
        jScrollPane7.setViewportView(TextAreaMain);

        javax.swing.GroupLayout PanelOutputExplanationsLayout = new javax.swing.GroupLayout(PanelOutputExplanations);
        PanelOutputExplanations.setLayout(PanelOutputExplanationsLayout);
        PanelOutputExplanationsLayout.setHorizontalGroup(
                PanelOutputExplanationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelOutputExplanationsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1112, Short.MAX_VALUE)
                                .addContainerGap()));
        PanelOutputExplanationsLayout.setVerticalGroup(
                PanelOutputExplanationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelOutputExplanationsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
                                .addContainerGap()));

        javax.swing.GroupLayout PanelMainLayout = new javax.swing.GroupLayout(PanelMain);
        PanelMain.setLayout(PanelMainLayout);
        PanelMainLayout.setHorizontalGroup(
                PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelMainLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(PanelOutputExplanations, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap()));
        PanelMainLayout.setVerticalGroup(
                PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelMainLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(PanelOutputExplanations, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap()));

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(PanelMain, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(PanelMain, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(0, 0, 0)));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void start() throws IOException {
        setCurrentTitle(getIpAddress());

        while (true) {
            Socket socket = _serverSocket.accept();
            ChatClientHandler client = new ChatClientHandler(socket);
            _chatClientList.add(client);

            threadPool.execute(client);
        }
    }

    private void broadcastMessage(MessageProtocol message) {
        for (ChatClientHandler client : _chatClientList) {
            client.sendMessage(message);
        }
    }

    private void processReceivedMessage(ChatClientHandler client, Object messageObject) {
        if (messageObject == null)
            return;

        try {
            MessageProtocol message = MessageFactory.getMessage(messageObject);
            appendInMessage(" =>:" + message.toServerString());

            // Message if for the server
            if (message.getType() == MessageType.SystemUserAuth) {
                var cipherBody = message.getCipherBody().toString();
                cipherBody = EncryptionHelper.decryptwithPrivateKey(cipherBody, "server", "networks"); // decrypt
                var plainBody = cipherBody;

                ProtocolBody messageBody = (ProtocolBody) HelperUtils.convertBase64StringToProtocolBody(plainBody);

                var actualMessage = (SystemUserAuthenticationMessage) messageBody.getMessage();
                client.setSystemUser(actualMessage.getUser());

                MessageProtocol outMessage = MessageFactory.CreateBroadcastSystemUsersMessage(_serverUser,
                        client.getSystemUser(), getAllSystemUsers());
                broadcastMessage(outMessage);
            } else {
                broadcastMessage(message);
            }
        } catch (IOException | ClassNotFoundException ex) {
            logException(ex);
        }
    }

    private List<SystemUser> getAllSystemUsers() {
        List<SystemUser> users = new ArrayList<>();

        users.add(_serverUser);

        for (ChatClientHandler client : _chatClientList) {
            users.add(client.getSystemUser());
        }

        return users;
    }

    private void closeServerSocket() {
        try {
            if (_serverSocket != null)
                _serverSocket.close();
        } catch (IOException ex) {
            logException(ex);
        }
    }

    private void closeAllClients() {
        try {
            if (_serverSocket != null)
                _serverSocket.close();
        } catch (IOException ex) {
            logException(ex);
        }
    }

    private class ChatClientHandler implements Runnable {
        private Socket _secureSocket;
        private ObjectOutputStream _outputStream;
        private ObjectInputStream _inputStream;
        private SystemUser _systemUser;

        public ChatClientHandler(Socket secureSocket) {
            _secureSocket = secureSocket;

            try {
                _inputStream = new ObjectInputStream(new GZIPInputStream(_secureSocket.getInputStream()));
                _outputStream = new ObjectOutputStream(new GZIPOutputStream(_secureSocket.getOutputStream()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                Object messageObject;
                while ((messageObject = _inputStream.readObject()) != null) {
                    processReceivedMessage(this, messageObject);
                }
            } catch (IOException | ClassNotFoundException ex) {
                logException(ex);
            } finally {
                // removeClient();
                // closeSocket();
            }
        }

        public void sendMessage(MessageProtocol message) {
            try {
                _outputStream.writeObject(message);
                _outputStream.flush();
            } catch (IOException ex) {
                logException(ex);
            }
        }

        public void setSystemUser(SystemUser systemUser) {
            _systemUser = systemUser;
        }

        public SystemUser getSystemUser() {
            return _systemUser;
        }

        private void removeClient() {
            closeSocket();
            _chatClientList.remove(this);
        }

        private void closeSocket() {
            try {
                _inputStream.close();

                _outputStream.flush();
                _outputStream.close();

                _secureSocket.close();
            } catch (IOException ex) {
                logException(ex);
            }
        }
    }

    private void ButtonExitApplicationActionPerformed() {
        int reply = JOptionPane.showConfirmDialog(this, "Are you sure you would like to Exit the Application",
                "Exit the Application", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            closeAllClients();
            closeServerSocket();
            System.exit(0);
        }
    }

    private void appendInMessage(String message) {
        if (message == null || message.isBlank())
            return;

        String m = String.format(" IN: %s\n", message);
        TextAreaMain.append(m);
        System.out.println(m);
        logInfor(m);
    }

    private void appendInMessage(IMessage message) {
        if (message == null)
            return;

        appendInMessage(message.toServerString());
        logInfor(message.toServerString());
    }

    private void appendOutMessage(String message) {
        if (message == null || message.isBlank())
            return;

        String m = String.format(" OUT: %s\n", message);
        TextAreaMain.append(m);
        System.out.println(m);
        logInfor(m);
    }

    private void appendOutMessage(IMessage message) {
        if (message == null)
            return;

        appendOutMessage(message.toServerString());
        logInfor(message.toServerString());
    }

    private void logException(Exception ex) {
        ex.printStackTrace();
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
    }

    private void logInfor(String info) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, info);
    }

    private void ButtonExitApplicationActionPerformed(java.awt.event.ActionEvent evt) {
        int reply = JOptionPane.showConfirmDialog(this, "Are you sure you would like to Exit the Application",
                "Exit the Application", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void processReceivedMessage(IMessage message) {
        if (message == null)
            return;

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="ERROR MESSAGES">
    /**
     * shows a message popup
     * 
     * @param title
     * @param errorMessage
     */
    private void showErrorPopupMessage(String title, String errorMessage) {
        if (errorMessage == null || errorMessage.isEmpty())
            return;

        if (title == null || title.isEmpty())
            title = ERROR_DEFAULT_TITLE;

        JOptionPane.showMessageDialog(this, errorMessage, title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * shows a message popup
     * 
     * @param title
     * @param errorMessage
     * @param exception
     */
    private void showErrorPopupMessage(String title, String errorMessage, Exception exception) {
        if (errorMessage == null || errorMessage.isEmpty())
            return;

        if (title == null || title.isEmpty())
            title = ERROR_DEFAULT_TITLE;

        Logger.getLogger(this.getName()).log(Level.SEVERE, title, exception);
        JOptionPane.showMessageDialog(this, String.format("%s\n%s", errorMessage, exception.getMessage()) + "", title,
                JOptionPane.ERROR_MESSAGE);
    }

    // </editor-fold>

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        boolean isLookSet = false;

        try {
            FlatLightLaf.setup();
            javax.swing.UIManager.setLookAndFeel(new FlatLightLaf());
            isLookSet = true;

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        if (!isLookSet) {
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                    javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                    | javax.swing.UnsupportedLookAndFeelException ex) {
                Logger.getLogger(ChatServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
        // </editor-fold>

        /* Create and display the form */
        try {
            new ChatServer().start();
        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelMain;
    private javax.swing.JPanel PanelOutputExplanations;
    private javax.swing.JTextArea TextAreaMain;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane7;
    // End of variables declaration//GEN-END:variables
}
