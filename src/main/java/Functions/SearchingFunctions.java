/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Functions;

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


public class SearchingFunctions {
    Scanner scannerStream ;
    String[] entries  ;
    int entriesLength ; 
    public SearchingFunctions(Scanner _scannerStream ){
        entries = new  String[1000];
        scannerStream = _scannerStream ;
        entriesLength = 0 ;
        while(scannerStream.hasNext()){
            String str = scannerStream.nextLine();
           
            entries[entriesLength] =str ;
            entriesLength ++ ;
        }
    }
  
    
   
    public int addSorted(String engName , String arabName, String _userName){
      //binary search    
        int first = 3 ;
        StringTokenizer str ;
        int last  = entriesLength-1 ;
        int middle = (first + last) / 2;      
        while (first <= last ){
            str = new StringTokenizer(entries[middle], "|");           
            String potenialWord = str.nextToken().trim();         
            if ( potenialWord.equalsIgnoreCase(engName.trim())){           
                return -1;  //can't duplicate
            }else if (potenialWord.compareToIgnoreCase(engName.trim()) < 0){//go to right              
                first = middle + 1 ;
            }else {//go to left                
                last = middle -1 ;
            }
             middle = (first + last) / 2;   
        }
        if (entries[middle].compareToIgnoreCase(engName) < 0)
            middle++;
           // For the very first word
        if(middle == 2)
            middle++;       
        insertAt(middle, engName, arabName);
         return 0 ; //sycc
    }
    
    // Insert at
    void insertAt(int location, String engName, String arabName)
    {
        for (int i = entriesLength; i > location; i--)
            entries[i] = entries[i - 1];
        entries[location] = engName + " | " + arabName;
        entriesLength++;
        
    }
    
    // Add new words into the file.
    // Operates when we close the file.
    public void updateFile()
    {
        PrintWriter fileWriter = null;
        try
        {
           OutputStream os = new FileOutputStream(entries[2] + ".txt");
           fileWriter = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
            for(int i=0; i<entriesLength ; i++)
            {
               fileWriter.println(entries[i]);
            }
            
        }
        catch(Exception e)
        {
            Logger.getLogger(SearchingFunctions.class.getName()).log(Level.SEVERE, null, e);
            }
        finally{
            fileWriter.close();
        }
    }
            
    

    public String[] getEntries() {
        return entries;
    }

    public int getEntriesLength() {
        return entriesLength;
    }
   
    public void replace(int i, String value){
        entries[i] = value ;
        PrintWriter pw = null;
        try {
            OutputStream os = new FileOutputStream(entries[2] + ".txt");
            pw = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
            for (int ii = 0 ; ii <entriesLength ; ii++){
              pw.println( entries[ii]);
            }
        } catch (IOException ex) {
            Logger.getLogger(SearchingFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            pw.close();
        }
    }
    
    public String[] search(String key){
        //binary search
        String userName = "N/A1@2" ;
        String password = "N/A";
        int first = 0 ;
        StringTokenizer str ;
        int last   = entriesLength ;
        int middle = first + last / 2;
        while (first < last ){
            
            str = new StringTokenizer(entries[middle], "|"); //name and passwords are separted by   | , its illegal for user to write this charchter in user name
            
            String potenialUser = str.nextToken().trim();
            if ( potenialUser.equalsIgnoreCase(key)){ //name == name
                userName = potenialUser ;
                password = str.nextToken().trim();
                break ; //end the while
            }else if (entries[middle].compareToIgnoreCase(key) > 0){//go to right
                first = middle + 1 ;

            }else {//go to left
                last = middle -1 ;
            }
             middle = first + last / 2;
        }
        String[] returno ={userName , password} ; 
        return  returno ;
    }
 
    
    public String searchEng(String key){
        //binary search
        String foundWord = " " ;
        String meaningWord = " " ;
        int first = 3 ;
        StringTokenizer str ;
        int last  =entriesLength-1 ;
        int middle = (first + last) / 2;
        
        while (first <= last ){
            str = new StringTokenizer(entries[middle], "|");  
            
            String potenialWord = str.nextToken().trim();
            if ( potenialWord.equalsIgnoreCase(key.trim())){
                foundWord = potenialWord ;
                meaningWord = str.nextToken().trim();
                break ; //end the while
            }else if (potenialWord.compareToIgnoreCase(key.trim()) < 0){//go to right
                
                first = middle + 1 ;

            }else {//go to left
                  
                last = middle -1 ;
            }
             middle = (first + last) / 2;
              
        }
         
        return  meaningWord ;
    }
    
    //takes arab as input 
    // start sequential search
    //output english world
    public String searchArab(String key){
       //seq search
        
        StringTokenizer strArabic ;
        for (int i = 3 ; i <entriesLength ; i++) {
            strArabic = new StringTokenizer(entries[i], "|");
            String englishWord = strArabic.nextToken().trim();
            String arabicWord;
            
            strArabic = new StringTokenizer(entries[i], "-|");
            while(strArabic.hasMoreTokens())
            {
                arabicWord = strArabic.nextToken().trim();
            if ( arabicWord.equals(key)){
                return englishWord ;       
            }
        }
    }
        return " ";
    }
    
    
    public void deleteWord(int idx)
    {
       for (int i = idx+3 ; i < entriesLength ; i++){
           entries[i] = entries[i+1];
       }
       entriesLength -- ;
    }
    
    public DefaultListModel filterList(char filter)
    {
        DefaultListModel temp = new DefaultListModel();
        int j=0;
        String character = filter + "";
        for(int i=3; i< entriesLength ; i++)
        {
            if(entries[i].toLowerCase().charAt(0) ==  character.toLowerCase().charAt(0))
            {
                temp.add(j, entries[i]);
                j++;
            }
        }
        return temp;
    }
    public boolean empty_filterList(char filter)
    {
            
            String character = filter + "";
            for(int i=3; i< entriesLength ; i++)
        {
            if(entries[i].toLowerCase().charAt(0) ==  character.toLowerCase().charAt(0))
            {
                return false;
            }
        }
            return true;
    }
   
}
