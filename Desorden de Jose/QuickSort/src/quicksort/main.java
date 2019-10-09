/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quicksort;

import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;

/**
 *
 * @author Jose
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
       System.out.println("hello world");
       //Get the Collator for US English and set its strength to PRIMARY
    Collator usCollator = Collator.getInstance(Locale.getDefault());
    usCollator.setStrength(Collator.SECONDARY);
    if( usCollator.compare("abc", "ABC") == 0 ) {
     System.out.println("Strings are equivalent");
     
     System.out.println(usCollator.compare("aaola","badios"));
     
     
 }
    
    String[] arr = new String[]{"hola", "azul", "Jose", "Natalia", "ñame", "José", "correr", "Correcaminos", "zebra", "Sentido", "oso", "meme"};
    System.out.println(Arrays.toString(arr));
    Quicksort hola = new Quicksort();
    hola.quicksort(arr);
    System.out.println(Arrays.toString(arr));
    }
    
}
