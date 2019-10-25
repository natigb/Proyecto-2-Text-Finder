/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Clase para identificar un documento de tipo .txt
 * @author Jose
 */
public class TXT {
    
    private File source;
    
    /**
     * Se encarga de leer un archivo .txt
     * @param path la direccion del archivo que se leera
     * @return un string con todo el texto del documento
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static String readTxt(String path) throws FileNotFoundException, IOException{
        String text = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String currentLine = reader.readLine();
            while (currentLine != null){
                text = text+currentLine;
                currentLine = reader.readLine();
            }
        }catch(IOException ex){
            Logger.getLogger(UniversalReader.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print(ex);
        }
        return text;
    }

    TXT(File source) {
        this.source = source;
    }

    public File getSource() {
        return source;
    }
    

    
}
