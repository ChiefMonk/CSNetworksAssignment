/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uct.cs.networks.utils;

import java.io.IOException;
import uct.cs.networks.enums.*;
import static uct.cs.networks.enums.MessageType.ServerBroadcastUserList;
import static uct.cs.networks.enums.MessageType.SystemUserAuth;
import static uct.cs.networks.enums.MessageType.SessionEnd;
import static uct.cs.networks.enums.MessageType.SessionStart;
import static uct.cs.networks.enums.MessageType.ValidateCertRequest;
import static uct.cs.networks.enums.MessageType.ValidateCertResponse;
import uct.cs.networks.interfaces.IMessage;
import uct.cs.networks.messages.*;
import uct.cs.networks.models.SystemUser;
import uct.cs.networks.proto.MessageProtocol;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class MessageFactory {

    public static MessageProtocol CreateMessage(SystemUser sender, SystemUser receiver, MessageType type, byte[] imageData, String textData, Object data) throws IOException
    {
       IMessage message = null;
       boolean createHash = true;
        switch (type) {
            case SendText -> {
                
                message =  new SendTextMessage(textData, sender, receiver);
                break;
            }

            case SendImageWithText -> {
                message = new SendImageWithTextMessage(imageData, textData, sender, receiver);
                break;
            }

            case ValidateCertRequest -> {
                message = new ValidateCertMessageRequest(sender, receiver);
                break;
            }

            case ValidateCertResponse -> {
                message = new ValidateCertMessageResponse(sender, receiver);
                break;
            }

            case SessionStart -> {
                message = new SessionStartMessage(sender, receiver);
                createHash = false;
                break;
            }

            case SessionEnd -> {
                message = new SessionEndMessage(sender, receiver);
                createHash = false;
                break;
            }
            case ServerBroadcastUserList -> {
                message = new BroadcastSystemUsersMessage(sender, receiver);
                createHash = false;
                break;
            }
            case SystemUserAuth -> {
                message = new SystemUserAuthenticationMessage(sender);
                createHash = false;
                break;
            }
        }

        if(message != null)
            return new MessageProtocol(message, createHash);
        
        return null;
    }
    
    public static MessageProtocol getMessage(Object obj) throws IOException, ClassNotFoundException 
    {
      return (MessageProtocol) obj;      
    }

    private static IMessage getMessage2(Object obj) throws IOException, ClassNotFoundException 
    {
        IMessage message = (IMessage) obj;

        switch (message.getType()) {
            case ValidateCertRequest -> {
                return (ValidateCertMessageRequest) message;
            }

            case ValidateCertResponse -> {
                return (ValidateCertMessageResponse) message;
            }

            case SessionStart -> {
                return (SessionStartMessage) message;
            }

            case SessionEnd -> {
                return (SessionEndMessage) message;
            }
            case ServerBroadcastUserList -> {
                return (BroadcastSystemUsersMessage) message;
            }
            case SystemUserAuth -> {
                return (SystemUserAuthenticationMessage) message;
            }
        }

        return message;
    }
}
