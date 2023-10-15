package uct.cs.networks.utils;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.Key;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

import uct.cs.networks.models.SystemUserNew;
import uct.cs.networks.proto.MessageProtocol;
import uct.cs.networks.proto.ProtocolBody;

/**
 *
 * @author Shaylin Chetty (CHTSHA042@myuct.ac.za)
 * @author Chipo Hamayobe (HMYCHI001@myuct.ac.za)
 * @author Orefile Morule (MRLORE001@myuct.ac.za)
 * @author Enock Shezi (SHZENO001@myuct.ac.za)
 */

public class HelperUtils {
    private static final String DATETIME_FORMAT = "yyyy.MM.dd.HH.mm.ss";
    public static final String SERVER_ID = "388f371c-b0d8-4dbc-a36b-0e4303a472d9";

    public static String GetCuttentUtcTimestamp() {
        return new SimpleDateFormat(DATETIME_FORMAT).format(new java.util.Date());
    }

    public static byte[] getImageData(String imagePath) {
        try {
            File file = new File(imagePath);
            if (!Files.exists(file.toPath())) {
                return null;
            }

            return Files.readAllBytes(file.toPath());
        } catch (IOException ex) {
            Logger.getLogger(HelperUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static String convertProtocolBodyToBase64String(ProtocolBody body) throws IOException {
        return convertObjectToBase64String(body);
    }

    public static ProtocolBody convertBase64StringToProtocolBody(String base64String)
            throws IOException, ClassNotFoundException {
        Object obj = convertBase64StringToObject(base64String);
        return (ProtocolBody) obj;
    }

    public static String convertKeytoBase64String(Key key) throws IOException {
        return convertObjectToBase64String(key);
    }

    public static Key convertBase64toKey(String base54String) throws IOException, ClassNotFoundException {
        Object obj = convertBase64StringToObject(base54String);
        return (Key) obj;
    }

    public static MessageProtocol convertBase64StringToMessageProtocol(String base64String)
            throws IOException, ClassNotFoundException {
        Object obj = convertBase64StringToObject(base64String);
        return (MessageProtocol) obj;
    }

    public static String convertObjectToBase64String(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(obj);
        }
        return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
    }

    public static Object convertBase64StringToObject(String base64String) throws IOException, ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(base64String);
        Object obj;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data))) {
            obj = objectInputStream.readObject();
        }
        return obj;
    }

    public static String convertSystemUserNew2String(SystemUserNew sysUser) throws IOException {
        return convertObjectToBase64String(sysUser);
    }

    public static SystemUserNew convertBase64StringToSystemUserNew(String base64String)
            throws IOException, ClassNotFoundException {
        Object obj = convertBase64StringToObject(base64String);
        return (SystemUserNew) obj;
    }

    public static String byteArray2String(byte[] arr) {
        InputStream inputStream = new ByteArrayInputStream(arr);
        String result = "nothing";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator()); // Add line separator if needed
            }
            result = stringBuilder.toString();
            // System.out.println(result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
