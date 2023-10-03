package uct.cs.networks.ui;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import uct.cs.networks.interfaces.IMessage;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.*;
import java.net.Socket;
import uct.cs.networks.enums.*;
import uct.cs.networks.models.SystemUser;
import uct.cs.networks.utils.MessageFactory;

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
public class ChatClientGUI extends javax.swing.JFrame {         
    
    // static and final parameters   
    private static final String ERROR_DEFAULT_TITLE = "Invalid Error Occurred";
    private static final int SCROLL_BUFFER_SIZE = 10;     
    private final JFileChooser _fileChooser;
    private String _ipAddress = "127.0.0.1";
    private int _portNumber = 4026;
    
    private SystemUser _currentUser;
    private List<SystemUser> _listOfUsers;
        
    private List<IMessage> _messageSentList;
    private List<IMessage> _messageReceivedList;
    
    private ObjectOutputStream _outputStream; 
    private ObjectInputStream _inputStream;
   
    private Socket _socket;
       
    
    /**
     * Creates new form ToolGUI
     */
    public ChatClientGUI() {      
        super("- SecureChatSystem : Demonstrating Secure Network Communication with Cryptographic Functions -");
        initComponents();
        this.setResizable(true);
          
        this.setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            ButtonExitApplicationActionPerformed(null);
        }}); 
        
        // Create a button group to group the radio buttons
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(RadioButtonUser1);
        buttonGroup.add(RadioButtonUser2);
        buttonGroup.add(RadioButtonUser3);
        buttonGroup.add(RadioButtonUser4);
        buttonGroup.add(RadioButtonUser5);
        buttonGroup.add(RadioButtonUser6);
        
       _messageSentList = new ArrayList<>();
       _messageReceivedList = new ArrayList<>();
       
       _currentUser = new SystemUser("Chipo", "hmychi001@myuct.ac.za");
       _listOfUsers = new ArrayList<>();
        
        _fileChooser = new JFileChooser();
        _fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif"));
        _fileChooser.setCurrentDirectory(new java.io.File("."));
        _fileChooser.setDialogTitle("Select an Image File");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        PanelMain = new javax.swing.JPanel();
        PanelInputs = new javax.swing.JPanel();
        Labelmage = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        RadioButtonUser1 = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        RadioButtonUser2 = new javax.swing.JRadioButton();
        RadioButtonUser3 = new javax.swing.JRadioButton();
        RadioButtonUser4 = new javax.swing.JRadioButton();
        RadioButtonUser5 = new javax.swing.JRadioButton();
        RadioButtonUser6 = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaOutput = new javax.swing.JTextArea();
        ButtonLoadImage = new javax.swing.JButton();
        PanelOutputEntailAndJustify = new javax.swing.JPanel();
        textFieldInputMessage = new javax.swing.JTextField();
        ButtonSendMessage = new javax.swing.JButton();
        ComboBoxMessageType = new javax.swing.JComboBox<>();
        PanelOutputExplanations = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        textAreaOutputExplanation = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenuItemServerSettings = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelMain.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        PanelInputs.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 204, 255), 1, true));
        PanelInputs.setAlignmentX(1.0F);
        PanelInputs.setAlignmentY(1.0F);

        RadioButtonUser1.setText("TheSever");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("List of Users");

        RadioButtonUser2.setText("User2");

        RadioButtonUser3.setText("User3");

        RadioButtonUser4.setText("User4");

        RadioButtonUser5.setText("User5");

        RadioButtonUser6.setText("User6");

        textAreaOutput.setEditable(false);
        textAreaOutput.setColumns(20);
        textAreaOutput.setRows(1000);
        textAreaOutput.setFocusable(false);
        jScrollPane1.setViewportView(textAreaOutput);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(RadioButtonUser1)
                    .addComponent(RadioButtonUser2)
                    .addComponent(RadioButtonUser3)
                    .addComponent(RadioButtonUser4)
                    .addComponent(RadioButtonUser5)
                    .addComponent(RadioButtonUser6))
                .addGap(43, 43, 43)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 892, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RadioButtonUser1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RadioButtonUser2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RadioButtonUser3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RadioButtonUser4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RadioButtonUser5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RadioButtonUser6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        ButtonLoadImage.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ButtonLoadImage.setText("Load Image");
        ButtonLoadImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonLoadImageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelInputsLayout = new javax.swing.GroupLayout(PanelInputs);
        PanelInputs.setLayout(PanelInputsLayout);
        PanelInputsLayout.setHorizontalGroup(
            PanelInputsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInputsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelInputsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Labelmage, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInputsLayout.createSequentialGroup()
                        .addComponent(ButtonLoadImage, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)))
                .addContainerGap())
        );
        PanelInputsLayout.setVerticalGroup(
            PanelInputsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInputsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelInputsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelInputsLayout.createSequentialGroup()
                        .addComponent(Labelmage, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ButtonLoadImage))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        PanelOutputEntailAndJustify.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 204, 255)));

        textFieldInputMessage.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        ButtonSendMessage.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ButtonSendMessage.setText("Send Message");
        ButtonSendMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSendMessageActionPerformed(evt);
            }
        });

        ComboBoxMessageType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1. Session Start Message", "2. Verify Public Certificate", "3. Send Text Message", "4. Send Image with Caption Message", "5. Session End Message" }));
        ComboBoxMessageType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxMessageTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelOutputEntailAndJustifyLayout = new javax.swing.GroupLayout(PanelOutputEntailAndJustify);
        PanelOutputEntailAndJustify.setLayout(PanelOutputEntailAndJustifyLayout);
        PanelOutputEntailAndJustifyLayout.setHorizontalGroup(
            PanelOutputEntailAndJustifyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelOutputEntailAndJustifyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ComboBoxMessageType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(textFieldInputMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ButtonSendMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PanelOutputEntailAndJustifyLayout.setVerticalGroup(
            PanelOutputEntailAndJustifyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelOutputEntailAndJustifyLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ComboBoxMessageType, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addGroup(PanelOutputEntailAndJustifyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelOutputEntailAndJustifyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ButtonSendMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldInputMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        PanelOutputExplanations.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 204, 255)));

        textAreaOutputExplanation.setEditable(false);
        textAreaOutputExplanation.setColumns(20);
        textAreaOutputExplanation.setRows(5);
        textAreaOutputExplanation.setFocusable(false);
        jScrollPane7.setViewportView(textAreaOutputExplanation);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Output and Logs");

        javax.swing.GroupLayout PanelOutputExplanationsLayout = new javax.swing.GroupLayout(PanelOutputExplanations);
        PanelOutputExplanations.setLayout(PanelOutputExplanationsLayout);
        PanelOutputExplanationsLayout.setHorizontalGroup(
            PanelOutputExplanationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelOutputExplanationsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelOutputExplanationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7)
                    .addGroup(PanelOutputExplanationsLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PanelOutputExplanationsLayout.setVerticalGroup(
            PanelOutputExplanationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelOutputExplanationsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout PanelMainLayout = new javax.swing.GroupLayout(PanelMain);
        PanelMain.setLayout(PanelMainLayout);
        PanelMainLayout.setHorizontalGroup(
            PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelOutputEntailAndJustify, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelOutputExplanations, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelInputs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelMainLayout.setVerticalGroup(
            PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelInputs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PanelOutputEntailAndJustify, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelOutputExplanations, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        MenuItemServerSettings.setText("File");

        jMenuItem1.setText("Server Settings");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        MenuItemServerSettings.add(jMenuItem1);

        jMenuBar1.add(MenuItemServerSettings);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelMain, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonSendMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSendMessageActionPerformed
                
        var messageType = getMessageType(String.valueOf(ComboBoxMessageType.getSelectedItem()));
        
        String info = textFieldInputMessage.getText();
        
        if(info == null || info.isBlank())
            return;
        
        if(_outputStream == null)
            startListener();
        
        try
        {   
            IMessage message = MessageFactory.CreateMessage(_currentUser, _currentUser, messageType, null, info);
            
            if(message == null)
                return;

            _outputStream.writeObject(message);                   
            _outputStream.flush();           
        }
         catch (IOException ex) {
            ex.printStackTrace();
         }
         textFieldInputMessage.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonSendMessageActionPerformed

    private void ButtonLoadImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonLoadImageActionPerformed
        
        if (_fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)                                               
          return;
                
         // Load an image using ImageIcon
        ImageIcon imageIcon = new ImageIcon(_fileChooser.getSelectedFile().getAbsolutePath()); // Replace with your image file path
        int imageWith = Labelmage.getHeight()-1;
                 
        Image scaledImage = imageIcon.getImage().getScaledInstance(imageWith, imageWith, Image.SCALE_SMOOTH); 
        
        Labelmage.setIcon(new ImageIcon(scaledImage)); // TODO add your handling code here:
    }//GEN-LAST:event_ButtonLoadImageActionPerformed

    private void ComboBoxMessageTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxMessageTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxMessageTypeActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        
        String ipAddress = JOptionPane.showInputDialog(this, "Enter Server IP Address: ", "Server Settings", JOptionPane.OK_CANCEL_OPTION);
              
        // Regular expression patterns for IPv4
        String ipv4Pattern = "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                         "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                         "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                         "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

        // Check if the given IP address matches the IPv4 or IPv6 pattern         
        Matcher matcherIPv4 = Pattern.compile(ipv4Pattern).matcher(ipAddress);

        // Check if the InetAddress is a valid IP address
        if(!matcherIPv4.matches())
        {
            showErrorPopupMessage("Invalid IP Address", "Please enter a valid Server IP Address");
            return;                              
        }
            
        _ipAddress = ipAddress;
               
        String portNumber = JOptionPane.showInputDialog(this, "Enter Server Port Number: ", "Server Settings", JOptionPane.OK_CANCEL_OPTION);
        
        try {
            // Parse the port number as an integer
            int port = Integer.parseInt(portNumber);

            // Check if the port number is within the valid range (1 - 65535)
            if(port >= 4000 && port <= 65535)
            {
                 _portNumber =  port;
            }
            else
            {
                showErrorPopupMessage("Invalid Port Number", "Please enter a valid Server Port Number between 4000 and 65535");
                return;
            }
        } 
        catch (NumberFormatException ex) {
            // If a NumberFormatException is thrown, the port number is not a valid integer
            showErrorPopupMessage("Invalid Port Number", "Please enter a valid Server Port Number between 4000 and 65535", ex);
            return;
        }

        // Handle user input (e.g., validate and use the IP and port)
        if (ipAddress != null && portNumber != null) 
        {       
            JOptionPane.showMessageDialog(null, "Server IP Address: " + ipAddress + "\nServer Port Number: " + portNumber);
             startListener();
        } // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void ButtonExitApplicationActionPerformed(java.awt.event.ActionEvent evt) {                                                      
        int reply = JOptionPane.showConfirmDialog(this, "Are you sure you would like to Exit the Application", "Exit the Application", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
             System.exit(0);
        }    
    }
    
    private void processReceivedMessage(IMessage message)
    {
        if(message == null)
            return;
        
        textAreaOutput.append(String.format("%s\n", message.toClientString()));
    }
     // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="ERROR MESSAGES">
   /**    
     * shows a message popup
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
        JOptionPane.showMessageDialog(this, String.format("%s\n%s", errorMessage, exception.getMessage()) + "", title, JOptionPane.ERROR_MESSAGE);
    }
     
    
    // </editor-fold>
     
     private static MessageType getMessageType(String selection)
     {
        if(selection.startsWith("1."))
            return MessageType.SessionStart;
         
        if(selection.startsWith("2."))
            return MessageType.ValidateCertRequest;
          
        if(selection.startsWith("3."))
            return MessageType.SendText;
           
        if(selection.startsWith("4."))
            return MessageType.SendImage;
            
        if(selection.startsWith("5."))
            return MessageType.SessionEnd;
             
        return MessageType.Unknown;
     }
       
     private void startListener() 
     {      
        try {
            // Connect to the server
             _socket = new Socket(_ipAddress, _portNumber);

            // Create reader and writer            
             _outputStream = new ObjectOutputStream(_socket.getOutputStream());
            _inputStream = new ObjectInputStream(_socket.getInputStream());
           
            // _printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            // Start a new thread for receiving messages
           Thread receiveThread = new Thread(() -> {
               try {
                   Object  object;
                   while ((object = _inputStream.readObject()) != null)                 
                   {
                      IMessage message = MessageFactory.getMessage(object);
                      processReceivedMessage(message);
                   }
               } 
               catch (IOException e) 
               {
                   e.printStackTrace();
               } 
               catch (ClassNotFoundException ex) {
                    Logger.getLogger(ChatClientGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            receiveThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        boolean isLookSet = false;
        
        try {            
            FlatLightLaf.setup();
            javax.swing.UIManager.setLookAndFeel( new FlatLightLaf() );
            isLookSet = true;
            
         } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ChatClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
           
       if(!isLookSet) {
            try {           
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                    javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
                Logger.getLogger(ChatClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
       }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChatClientGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonLoadImage;
    private javax.swing.JButton ButtonSendMessage;
    private javax.swing.JComboBox<String> ComboBoxMessageType;
    private javax.swing.JLabel Labelmage;
    private javax.swing.JMenu MenuItemServerSettings;
    private javax.swing.JPanel PanelInputs;
    private javax.swing.JPanel PanelMain;
    private javax.swing.JPanel PanelOutputEntailAndJustify;
    private javax.swing.JPanel PanelOutputExplanations;
    private javax.swing.JRadioButton RadioButtonUser1;
    private javax.swing.JRadioButton RadioButtonUser2;
    private javax.swing.JRadioButton RadioButtonUser3;
    private javax.swing.JRadioButton RadioButtonUser4;
    private javax.swing.JRadioButton RadioButtonUser5;
    private javax.swing.JRadioButton RadioButtonUser6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextArea textAreaOutput;
    private javax.swing.JTextArea textAreaOutputExplanation;
    private javax.swing.JTextField textFieldInputMessage;
    // End of variables declaration//GEN-END:variables
}
