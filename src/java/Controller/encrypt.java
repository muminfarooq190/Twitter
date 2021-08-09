

package Controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class encrypt {
    
    public static String Sha512(String password)
    {
       String newPassword = "";
       try
       {
           MessageDigest md = MessageDigest.getInstance("SHA-512");
           byte [] messagedigest = md.digest(password.getBytes());
           BigInteger number = new BigInteger(1, messagedigest);
           newPassword = number.toString(16);
           while(newPassword.length()<32)
           {
            newPassword =   "0"+newPassword ;
           }
           return newPassword;
       }
       catch(NoSuchAlgorithmException e)
       {
       
       }
       return newPassword;
    }
    public static String Md5(String password)
    {
       String newPassword = "";
       try
       {
           MessageDigest md = MessageDigest.getInstance("MD5");
           byte [] messagedigest = md.digest(password.getBytes());
           BigInteger number = new BigInteger(1, messagedigest);
           newPassword = number.toString(16);
           while(newPassword.length()<32)
           {
            newPassword =   "0"+newPassword ;
           }
           return newPassword;
       }
       catch(NoSuchAlgorithmException e)
       {
       
       }
       return newPassword;
    }
    public static String Sha_1(String password)
    {
       String newPassword = "";
       try
       {
           MessageDigest md = MessageDigest.getInstance("SHA-1");
           byte [] messagedigest = md.digest(password.getBytes());
           BigInteger number = new BigInteger(1, messagedigest);
           newPassword = number.toString(16);
           while(newPassword.length()<32)
           {
            newPassword =   "0"+newPassword ;
           }
           return newPassword;
       }
       catch(NoSuchAlgorithmException e)
       {
       
       }
       return newPassword;
    }
    
    
}
