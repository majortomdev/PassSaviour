
package com.kinahan.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 created by JKinahan
 */
public class HashKey {
    
    public String HashIt(String key) throws NoSuchAlgorithmException    {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(key.getBytes());
        String encryptedString = new String(messageDigest.digest());
        return encryptedString;
    }
     
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//    
//    
//    public static void main(String [] args){
//        HashKey hk = new HashKey();
//        try {
//            hk.HashIt("abc");
//        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(HashKey.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//}
