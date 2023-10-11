package uct.cs.networks.utils;

import uct.cs.networks.proto.MessageProtocol;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPOutputStream;
import java.io.*;
import java.net.*;
import java.util.zip.*;


/**
 *
 * @author Shaylin Chetty (CHTSHA042@myuct.ac.za)
 * @author Chipo Hamayobe (HMYCHI001@myuct.ac.za)
 * @author Orefile Morule (MRLORE001@myuct.ac.za)
 * @author Enock Shezi (SHZENO001@myuct.ac.za)
 */
public class CompressionHelper {
    
    public static byte[] compressMessage(MessageProtocol message) throws IOException
    {
         // Create a ByteArrayOutputStream to store the compressed object
         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
         
         // Create a GZIPOutputStream to compress the data
         GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
         
        // Write the object to the ObjectOutputStream
        // Create an ObjectOutputStream to serialize the object
        try (java.io.ObjectOutputStream objectOutputStream = new ObjectOutputStream(gzipOutputStream)) {
            // Write the object to the ObjectOutputStream
            objectOutputStream.writeObject(message);
            // Close the ObjectOutputStream
        }

        // Get the compressed byte array
         return byteArrayOutputStream.toByteArray();
    }
    
    public static MessageProtocol decompressMessage(ObjectInputStream stream) throws IOException, ClassNotFoundException
    {
        // Create a GZIPOutputStream to decompress the data
         GZIPInputStream gzipInputStream = new GZIPInputStream(stream);
         
         Object object;
         // Create an ObjectInputStream to deserialize the object
        try (ObjectInputStream objectInputStream = new ObjectInputStream(gzipInputStream)) {
            object = objectInputStream.readObject();
        }
         // Read the Object
         return MessageFactory.getMessage(object);
         
        
    }        
}
