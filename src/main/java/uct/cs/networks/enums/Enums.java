package uct.cs.networks.enums;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */

public class Enums {
    public static enum MessageDestination {
        Client,
        Server       
    }
    
    public static enum MessageType {
        ClientHandshake,
        ServerHandshake,
        ValidateCertificate,
        SessionStart,
        SessionEnd,
        SendMessage,
    }

    public static enum MessageFormat {
        Text,
        Binary,    
    }
}
