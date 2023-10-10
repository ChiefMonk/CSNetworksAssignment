package uct.cs.networks.messages;

import uct.cs.networks.enums.CertificateVerificationResult;
import uct.cs.networks.enums.MessageType;
import uct.cs.networks.interfaces.IMessage;
import uct.cs.networks.models.SystemUser;

/**
 *
 * @author Shaylin Chetty (CHTSHA042@myuct.ac.za)
 * @author Chipo Hamayobe (HMYCHI001@myuct.ac.za)
 * @author Orefile Morule (MRLORE001@myuct.ac.za)
 * @author Enock Shezi (SHZENO001@myuct.ac.za)
 */
public class ValidateCertMessageResponse extends MessageBase implements IMessage {
    
    private final SystemUser _verifyUser;
    private final CertificateVerificationResult _verificationResult;
     
    public ValidateCertMessageResponse(SystemUser sender, SystemUser receiver, SystemUser verifyUser, CertificateVerificationResult verificationResult) {
        super(MessageType.ValidateCertResponse, sender, receiver);
        _verifyUser = verifyUser;
        _verificationResult = verificationResult;
    }  
    
     public SystemUser getVerifyUser() {
        return _verifyUser;
    }
     
      public CertificateVerificationResult getVerificationResult() {
        return _verificationResult;
    }
}
