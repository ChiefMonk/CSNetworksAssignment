package uct.cs.networks.enums;

/**
 *
 * @author Shaylin Chetty (CHTSHA042@myuct.ac.za)
 * @author Chipo Hamayobe (HMYCHI001@myuct.ac.za)
 * @author Orefile Morule (MRLORE001@myuct.ac.za)
 * @author Enock Shezi (SHZENO001@myuct.ac.za)
 */

 public enum MessageType {   
        Unknown,
        ValidateCertRequest,
        ValidateCertResponse,
        SessionStart,      
        SendText,
        SendImageWithText,       
        SessionEnd,
        ServerBroadcastUserList,  
        SystemUserAuth,
    }
