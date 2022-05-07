/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Functions;
import java.math.BigInteger;
import java.security.MessageDigest ;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Hashing {
        public static String hash(String password){
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hashedPassword = md.digest(password.getBytes());
                BigInteger passwordInInt = new BigInteger(1 , hashedPassword);
                String hashedPasswordText = passwordInInt.toString(16); //HEX
                while(hashedPasswordText.length()  < 16){
                    hashedPasswordText += "0" ; //make it 32 bit
                }
               return hashedPasswordText ;
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Hashing.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "Error";
        }
    
}
