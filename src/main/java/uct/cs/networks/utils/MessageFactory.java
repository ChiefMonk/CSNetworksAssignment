/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uct.cs.networks.utils;

import java.io.IOException;
import java.security.Key;
import java.util.List;
import uct.cs.networks.enums.*;
import static uct.cs.networks.enums.MessageType.BroadcastUserList;
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

    public static MessageProtocol CreateBroadcastSystemUsersMessage(SystemUser sender, SystemUser receiver,
            List<SystemUser> users) throws IOException {
        return new MessageProtocol(new BroadcastSystemUsersMessage(sender, receiver, users), false, null);
    }

    public static MessageProtocol CreateValidateCertMessageResponse(SystemUser sender, SystemUser receiver,
            SystemUser verifyUser, CertificateVerificationResult verificationResult) throws IOException {
        return new MessageProtocol(new ValidateCertMessageResponse(sender, receiver, verifyUser, verificationResult),
                false, null);
    }

    public static MessageProtocol CreateValidateCertMessageRequest(SystemUser sender, SystemUser receiver,
            SystemUser verifyUser) throws IOException {
        return new MessageProtocol(new ValidateCertMessageRequest(sender, receiver, verifyUser), false, null);
    }

    private static Key createSharedKey() {
        AESEncryption aesEncryption = new AESEncryption();
        Key key = null;
        try {
            key = aesEncryption.getKeyFromKeyGenerator();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    public static MessageProtocol CreateMessage(SystemUser sender, SystemUser receiver, MessageType type,
            byte[] imageData, String textData) throws IOException {
        IMessage message = null;
        boolean createHash = true;
        switch (type) {
            case SendText -> {

                message = new SendTextMessage(sender, receiver, textData);
                break;
            }

            case SendImageWithText -> {
                message = new SendImageWithTextMessage(sender, receiver, imageData, textData);
                break;
            }

            case SessionStart -> {
                message = new SessionStartMessage(sender, receiver, createSharedKey());
                createHash = false;
                break;
            }

            case SessionEnd -> {
                message = new SessionEndMessage(sender, receiver);
                createHash = false;
                break;
            }
            case SystemUserAuth -> {
                message = new SystemUserAuthenticationMessage(sender);
                createHash = false;
                break;
            }
        }

        if (message != null)
            return new MessageProtocol(message, createHash, receiver);

        return null;
    }

    public static MessageProtocol getMessage(Object obj) throws IOException, ClassNotFoundException {
        return (MessageProtocol) obj;
    }

    private static IMessage getMessage2(Object obj) throws IOException, ClassNotFoundException {
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
            case BroadcastUserList -> {
                return (BroadcastSystemUsersMessage) message;
            }
            case SystemUserAuth -> {
                return (SystemUserAuthenticationMessage) message;
            }
        }

        return message;
    }
}
