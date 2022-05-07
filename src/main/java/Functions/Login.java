/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Functions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login {

    String passwordHashed;
    String name ;
    String userName;

    public String getPasswordHashed() {
        return passwordHashed;
    }

    public void setPasswordHashed(String passwordHashed) {
        this.passwordHashed = passwordHashed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    //-1 can't sign in 0 user 1 admin
    public int signInFromFile(){
         try {
             Scanner scanner = new Scanner (new File ("current user.txt"));
             if (scanner.hasNext()){
                  passwordHashed = scanner.nextLine(); //first line is hashed password
                  name = scanner.nextLine(); //second line is user name
                  userName = scanner.nextLine(); // third line is user name
                  if (passwordHashed.equals("123")){
                      return 1;
                  }
                  return 0 ;
             }
             return -1 ;
         } catch (FileNotFoundException ex) {
            
            return -1;
         }
        
    }
    
     public boolean SignUp(String name, String password , String userName){
       Scanner scan = null ; 
        try {
               scan = new Scanner (new File (name) , "UTF-8");
               return false ;
              } catch (FileNotFoundException ex) {
                  
                  PrintWriter write = null;
                  PrintWriter write2 = null;
                  PrintWriter write3 = null;
           try {
               write = new PrintWriter(new File(userName + ".txt"));
               write2  =new PrintWriter(new FileWriter("admin.txt", true));
               write3  =new PrintWriter(new FileWriter("current user.txt"));
               
               write.println(Hashing.hash(password));
               write.println(name);
               write.println(userName); 
               
               write2.println(userName);
               
               write3.println(Hashing.hash(password));
               write3.println(name);
               write3.println(userName); 
               //editLocaleVariables(Hashing.hash(password) ,name ,userName);
               
               return true ;
           } catch (IOException ex1) {
               Logger.getLogger(Accounts.class.getName()).log(Level.SEVERE, null, ex1);
               return false ;
           } finally {
               write.close();
               write2.close();
               write3.close();
           }
         }   
    }
      public boolean signIn(String _name, String password){
        Scanner scan = null ;
        try {
               scan = new Scanner (new File (_name + ".txt") , "UTF-8");
              } catch (FileNotFoundException ex) {
              
             return false ;
         }
            
            passwordHashed = scan.nextLine(); //first line is hashed password
            name = scan.nextLine(); //second line is user name
            userName = scan.nextLine(); // third line is user name

            if ( passwordHashed.equals(Hashing.hash(password))){
               
                PrintWriter print = null;
                try {
                    print = new PrintWriter (new FileWriter ("current user.txt"));
                    //save current user
                    print.println(passwordHashed);
                    print.println(name);
                    print.println(userName);                
              } catch (IOException ex) {
              
             //Logger.getLogger(Accounts.class.getName()).log(Level.SEVERE, null, ex);
             return false ;
         }finally{
                 print.close();
                }
            }
            return  passwordHashed.equals(Hashing.hash(password));
        
    }
      public void signInAdmin (){
          
                PrintWriter print = null;
                try {
                    print = new PrintWriter (new FileWriter ("current user.txt"));
                    //save current user
                    print.println("123");
                    print.println("admin");
                    print.println("admin");                
              } catch (IOException ex) {
              System.out.println("can't write");
             //Logger.getLogger(Accounts.class.getName()).log(Level.SEVERE, null, ex);
         }finally{
                 print.close();
            }
            
    }
      
      public boolean resetPassword(String userName , String displayName, String password){
        Scanner scan = null ;
        String[] temp = new String[1000];
        int tempSize = 0 ;
        
        try {
               scan = new Scanner (new File (userName + ".txt") , "UTF-8");
              } catch (FileNotFoundException ex) {
              System.out.println("user not found");
             return false ;
         }     
            passwordHashed = scan.nextLine(); //first line is hashed password
            name = scan.nextLine(); //second line is user name
            temp[0] = passwordHashed;
            temp[1] = name;
            tempSize = 2 ;
            
           if ( name.equals(displayName)){ //data is confirmed
                PrintWriter fileWriter = null;
                OutputStream os = null ;
                while(scan.hasNext()){
                    temp[tempSize]= scan.nextLine();
                    tempSize ++ ;                 
                }
            
            temp[0] = Hashing.hash(password);
            
            try{
                os = new FileOutputStream(userName + ".txt");
                fileWriter = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
                for(int i=0; i<tempSize ; i++)
                {fileWriter.println(temp[i]); }
              return true ;
        }
        catch(IOException e)
        {System.out.println("Can't add more");}
            
        finally{
        fileWriter.close();
        }
           
        }
        return false;
        }
}
