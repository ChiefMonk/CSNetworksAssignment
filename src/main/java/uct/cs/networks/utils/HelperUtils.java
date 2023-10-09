package uct.cs.networks.utils;

import java.nio.file.Files;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import uct.cs.networks.proto.ProtocolBody;

/**
 *
 * @author Shaylin Chetty (CHTSHA042@myuct.ac.za)
 * @author Chipo Hamayobe (HMYCHI001@myuct.ac.za)
 * @author Orefile Morule (MRLORE001@myuct.ac.za)
 * @author Enock Shezi (SHZENO001@myuct.ac.za)
 */

public class HelperUtils 
{    
    private static final String DATETIME_FORMAT = "yyyy.MM.dd.HH.mm.ss";
    
    public static String GetCuttentUtcTimestamp()
    {        
       return new SimpleDateFormat(DATETIME_FORMAT).format(new java.util.Date());              
    }        
    
    public static byte[] getImageData(String imagePath)
    {
        try 
        {
            File file = new File(imagePath);
            if(!Files.exists(file.toPath()))
            {
                return null;
            }
            
            return Files.readAllBytes(file.toPath());
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(HelperUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    public static String convertProtocolBodyToBase64String(ProtocolBody body) throws IOException 
    {
        return convertObjectToBase64String(body);
    }

    public static ProtocolBody convertBase64StringToProtocolBody(String base64String) throws IOException, ClassNotFoundException 
    {
        Object obj = convertBase64StringToObject(base64String);        
        return (ProtocolBody) obj;
    }

    public static String convertObjectToBase64String(Object obj) throws IOException 
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(obj);
        }
        return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
    }
    
    public static Object convertBase64StringToObject(String base64String) throws IOException, ClassNotFoundException 
    {
        byte[] data = Base64.getDecoder().decode(base64String);
        Object obj;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data))) {
            obj = objectInputStream.readObject();
        }
        return obj;
    }   
}
