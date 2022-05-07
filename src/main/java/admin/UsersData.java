/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package admin;

import Functions.Accounts;
import Functions.SearchingFunctions;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

public class UsersData {
   String[] usersDataList ;
   int usersDataListCount ;

    public String[] getUsersDataList() {
        return usersDataList;
    }

    public void setUsersDataList(String[] usersDataList) {
        this.usersDataList = usersDataList;
    }

    public int getUsersDataListCount() {
        return usersDataListCount;
    }

    public void setUsersDataListCount(int usersDataListCount) {
        this.usersDataListCount = usersDataListCount;
    }
   Scanner scan = null ;      // To read from file.
   public UsersData(){
       try {
            scan  = new Scanner(new File("admin.txt"));
            usersDataListCount = 0 ;
            usersDataList = new String[1000];
            while(scan.hasNext()){
                usersDataList[usersDataListCount] = scan.nextLine();
                usersDataListCount ++ ;
            }
           
            
         } catch (FileNotFoundException ex) {
             System.out.println("No Such file");
             Logger.getLogger(Accounts.class.getName()).log(Level.SEVERE, null, ex);    // Auto Generated
         }
   }
   

    public boolean deleteAccount(int index){
        File myObj = new File(usersDataList[index]+".txt"); 
       boolean resp = myObj.delete();
        if (resp){
             for (int i = index ; i < usersDataListCount ; i++){
             usersDataList[i] = usersDataList[i+1];
       }
       usersDataListCount -- ;
        updateFile();  
        return true;
       }
      return false ;
   }
    
  
   public void updateFile()
    {
        PrintWriter fileWriter = null;
        try
        {
           OutputStream os = new FileOutputStream("admin.txt");
           fileWriter = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
            for(int i=0; i<usersDataListCount ; i++)
            {
               fileWriter.println(usersDataList[i]);
            }
            
        }
        catch(IOException e)
        {
            Logger.getLogger(SearchingFunctions.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Can't add more");}
        finally{
            fileWriter.close();
        }
    }
    
    public void logout(){
           PrintWriter write = null;
           try {
               write = new PrintWriter(new File("current user.txt"));
               write.println();
           } catch (FileNotFoundException ex1) {
               Logger.getLogger(Accounts.class.getName()).log(Level.SEVERE, null, ex1);
           } finally {
               write.close();
           }
    }
}
