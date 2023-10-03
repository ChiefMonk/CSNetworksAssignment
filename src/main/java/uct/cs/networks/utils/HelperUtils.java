package uct.cs.networks.utils;

import java.nio.file.Files;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
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
}
