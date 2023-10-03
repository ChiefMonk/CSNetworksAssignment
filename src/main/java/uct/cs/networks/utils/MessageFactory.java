/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uct.cs.networks.utils;

import java.io.IOException;
import uct.cs.networks.enums.*;
import static uct.cs.networks.enums.MessageType.ServerBroadcastUserList;
import static uct.cs.networks.enums.MessageType.ServerSendCertificate;
import static uct.cs.networks.enums.MessageType.SessionEnd;
import static uct.cs.networks.enums.MessageType.SessionStart;
import static uct.cs.networks.enums.MessageType.ValidateCertRequest;
import static uct.cs.networks.enums.MessageType.ValidateCertResponse;
import uct.cs.networks.interfaces.IMessage;
import uct.cs.networks.messages.*;
import uct.cs.networks.models.SystemUser;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class MessageFactory {
   
    public static IMessage CreateMessage(SystemUser sender, SystemUser receiver, MessageType type, byte[] imageData, String message)
    {
        switch(type)
        {
            case SendText -> 
            {
                return new SendTextMessage(message, sender, receiver);
            }
            
            case SendImage -> 
            {
                return new SendImageMessage(imageData, message, sender, receiver);
            }
            
            case ValidateCertRequest -> 
            {
                return new ValidateCertMessageRequest(sender, receiver);
            }
            
            case ValidateCertResponse -> 
            {
                return new ValidateCertMessageResponse(sender, receiver);
            }
            
            case SessionStart -> 
            {
                return new SessionStartMessage(sender, receiver);
            }
            
            case SessionEnd -> 
            {
                return new SessionEndMessage(sender, receiver);
            }
            case ServerBroadcastUserList -> 
            {
                return new BroadcastSystemUsersMessage(sender, receiver);
            }
            case ServerSendCertificate -> 
            {
                return new SendCertificateToServerMessage(sender, receiver);
            }
        }
        
        return null;
    }
       
    
    public static IMessage getMessage(Object obj) throws IOException, ClassNotFoundException
    {
        IMessage message = (IMessage)obj ;   
       
        switch(message.getHeader().getType())
        {
            case ValidateCertRequest -> 
            {
                return (ValidateCertMessageRequest)message;
            }
            
            case ValidateCertResponse -> 
            {
                return (ValidateCertMessageResponse)message;
            }
            
            case SessionStart -> 
            {
                return (SessionStartMessage) message;
            }
            
            case SessionEnd -> 
            {
                return (SessionEndMessage)message;
            }
            case ServerBroadcastUserList -> 
            {
                return (BroadcastSystemUsersMessage)message;
            }
            case ServerSendCertificate -> 
            {
                return (SendCertificateToServerMessage)message;
            }
        }
        
        return message;
    }
}
