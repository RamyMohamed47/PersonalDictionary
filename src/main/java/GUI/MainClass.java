/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import admin.AdminHome;
import Functions.Accounts;
import Functions.Login;
import Functions.SearchingFunctions;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ziad asem
 */
public class MainClass {
    public static void main(String[] args) {
        Login sp = new Login();
        int result = sp.signInFromFile();
        if (result != -1){
            if (result == 0){ //user sign in
                 Accounts account = new Accounts(sp.getUserName()); 
                 new Home(account).setVisible(true);
            }else{
                  new AdminHome().setVisible(true);
            }
          
        }else{//new user
            //Accounts account = new Accounts(sp.getPasswordHashed() , sp.getName() , sp.getPasswordHashed());
            new SignInScreen().setVisible(true); 
         }
    }
}
