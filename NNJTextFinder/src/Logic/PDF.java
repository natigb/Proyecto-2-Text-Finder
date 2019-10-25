/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 *
 * @author Jose
 */
public class PDF{
    
    private File source;
    private static PDDocument doc;
    
    public static String readPdf(String path) throws IOException{
        File file = new File(path);
        try {
            doc = PDDocument.load(file);
        } catch (IOException ex) {
            Logger.getLogger(UniversalReader.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print(ex);
            doc = null;
        }
        return new PDFTextStripper().getText(doc); 
    }

    PDF(File source) {
        this.source = source;
    }

    public File getSource() {
        return source;
    }
    

    public void closePDF() throws IOException {
        doc.close();
    }

   
}
