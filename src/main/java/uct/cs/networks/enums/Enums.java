package uct.cs.networks.enums;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */

public class Enums 
{    
    public static enum MessageType {       
        ValidateCertificate,
        SessionStart,      
        SendImageMessage,
        SendTextMessage,
        SessionEnd,
        ServerBroadcastUserList,       
    }

    public static enum MessageFormat {
        Text,
        Binary,    
    }
}
