package uct.cs.networks.ui;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class ChatClient extends javax.swing.JFrame {   
    private JList<String> userList;
    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;

    public ChatClient() {
        
       // initComponents();
        this.setResizable(true);
          
           this.setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            ButtonExitApplicationActionPerformed(null);
        }}); 
        
        // Create the main frame
       // frame = new JFrame("Chat Client");
        //ame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Create a panel for the user list
        userList = new JList<>();
        JScrollPane userListScrollPane = new JScrollPane(userList);
        userListScrollPane.setPreferredSize(new Dimension(100, 0));

        // Create a panel for the chat area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane chatScrollPane = new JScrollPane(chatArea);

        // Create a panel for the message input and send button
        JPanel inputPanel = new JPanel(new BorderLayout());
        messageField = new JTextField();
        sendButton = new JButton("Send");
        inputPanel.add(messageField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        // Add action listener to the send button
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageField.getText();
                if (!message.isEmpty()) {
                    // Replace this with your code to send the message
                    chatArea.append("You: " + message + "\n");
                    messageField.setText("");
                }
            }
        });

        // Create a panel to hold the user list and chat area
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(userListScrollPane, BorderLayout.WEST);
        mainPanel.add(chatScrollPane, BorderLayout.CENTER);

        // Add panels to the main frame
        add(mainPanel, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        // Display the frame
        setVisible(true);
    }
    
    private void ButtonExitApplicationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonExitApplicationActionPerformed
        int reply = JOptionPane.showConfirmDialog(this, "Are you sure you would like to Exit the Application", "Exit the Application", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
             System.exit(0);
        }    
    }

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
            Logger.getLogger(ChatClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                Logger.getLogger(ChatClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
       }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatClient().setVisible(true);
            }
        });
    }
}

