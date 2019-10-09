package Logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Jose
 */
public class UniveralReader {
    
    
    public static String read() throws FileNotFoundException, IOException{
        String text = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Library/ArchivoTXT.txt"))) {
            String currentLine = reader.readLine();
            text = currentLine;
            while (currentLine != null){
                currentLine = reader.readLine();
                text = text+currentLine;
            }
        }catch(IOException ex){
            //Logger.getLogger(GUI.TextFinderFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print(ex);
        }
        return text;
    }
    
}
