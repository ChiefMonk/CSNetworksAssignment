/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uct.cs.networks.utils;

import java.io.IOException;
import uct.cs.networks.enums.Enums.*;
import uct.cs.networks.interfaces.IMessage;
import uct.cs.networks.messages.SendTextMessage;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class MessageFactory {
   
    public static IMessage CreateMessage(MessageType type, String message)
    {
        switch(type)
        {
            case SendTextMessage -> 
            {
                return new SendTextMessage(message);
            }
        }
        
        return new SendTextMessage(message);
    }
  
    
    public static IMessage getMessage(Object obj) throws IOException, ClassNotFoundException
    {
        IMessage message = (IMessage)obj ;   
        //System.out.println(message);     
        return message;
    }
}
