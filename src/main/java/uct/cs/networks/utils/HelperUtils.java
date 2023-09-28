/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uct.cs.networks.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public class HelperUtils {
    
    private static final String DATETIME_FORMAT = "yyyy.MM.dd.HH.mm.ss.ns";
    
    public static String GetCuttentUtcTimestamp()
    {        
        return DateTimeFormatter.ofPattern(DATETIME_FORMAT).format(Instant.now());      
    }
    
    public static LocalDateTime ToDateTime(String timestamp )
    {               
        return LocalDateTime.parse(timestamp, DateTimeFormatter.ofPattern(DATETIME_FORMAT));
    }      
}
