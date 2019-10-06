/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1.newpackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Jose
 */
public class TxtFileReader {
     
    private Scanner x;
    
    public void openFile(){
        try{
            System.out.println("this is dir" + System.getProperty("user.dir"));
            x = new Scanner(new File("src\\javafxapplication1\\newpackage\\hola.txt"));
        }
        catch(FileNotFoundException e){
            System.out.println(e);
        }
    }
    
    public void readFile(){
       while (x.hasNext()){ 
          System.out.print(x.next());
       }
    }
    
    public void closeFile(){
        x.close();
    }
    
}
