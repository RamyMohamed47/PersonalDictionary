
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Functions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

// The account file.txt which contains username & password\
// and also contains the words and its functions.
public class Accounts {
     Scanner scan = null ;      // To read from file.
     String userName ;          // The username.
     String name ;              // The User's name.
     String passwordHashed ;    // This user's password (Hashed).
     public boolean dark = false;            // Color mode.
     SearchingFunctions searchingFunctions;     // declaring an instance of this class.
     
     // Constructor
     public Accounts(String _userName){
        
         try {
            scan  = new Scanner(new File(_userName +".txt") , "UTF-8");
            passwordHashed = scan.nextLine();
            name = scan.nextLine();
            userName = scan.nextLine();
            searchingFunctions = new SearchingFunctions(scan);
            
         } catch (FileNotFoundException ex) {
             System.out.println("No Such file");
             Logger.getLogger(Accounts.class.getName()).log(Level.SEVERE, null, ex);    // Auto Generated
         }
             
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswordHashed() {
        return passwordHashed;
    }

    public void setPasswordHashed(String passwordHashed) {
        this.passwordHashed = passwordHashed;
    }
   
   
                                    // Used in Editing account?
     public boolean validateOldPassword(String password){
        System.out.println(Hashing.hash(passwordHashed));
       System.out.println(Hashing.hash(password));

      return  passwordHashed.equals(Hashing.hash(password));
    }
     
   
    public void logout(){
           PrintWriter write = null;
           try {
               write = new PrintWriter(new File("current user.txt"));
               name = "" ;
               passwordHashed  = "";
               write.println();
           } catch (FileNotFoundException ex1) {
               Logger.getLogger(Accounts.class.getName()).log(Level.SEVERE, null, ex1);
           } finally {
               write.close();
           }
    }
    
    public void updateCurrentUser( String password, String name , String userName){
           PrintWriter write = null;
           try {
               write = new PrintWriter(new File("current user.txt"));
               write.println(password);
               write.println(name);
               write.println(userName);
               editLocaleVariables(password,name ,userName);
           } catch (FileNotFoundException ex1) {
               Logger.getLogger(Accounts.class.getName()).log(Level.SEVERE, null, ex1);
           } finally {
               write.close();
           }
    }
    
    public void updateNewPassowrd(String password){
        String temp = Hashing.hash(password);
        passwordHashed = temp ;
        updateCurrentUser(passwordHashed,name , userName);
        searchingFunctions.replace(0 , temp);
    }
    
    
    public void editName(String _name){
         name = _name;
         updateCurrentUser(passwordHashed,_name ,userName );
         searchingFunctions.replace(1 , _name);
    }
    
     
    public void editLocaleVariables(String _hashedPassword, String _name, String _userName){
         try {
             userName =  _userName ;
             name = _name;
             passwordHashed = _hashedPassword ;
             scan  = new Scanner(new File(userName +".txt") , "UTF-8");
             searchingFunctions = new SearchingFunctions(scan);
         } catch (FileNotFoundException ex) {
             Logger.getLogger(Accounts.class.getName()).log(Level.SEVERE, null, ex);
         }
    }


}
