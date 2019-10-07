/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readpdf;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.pdmodel.PDDocument;

/**
 *
 * @author Jose
 */
public class PDFReader {
    
    private PDDocument doc;
    
    public PDFReader(){
        
    }
    
    public String toText(String filePath) throws IOException{
        
        File file = new File(filePath);
        doc = PDDocument.load(file);
        return new PDFTextStripper().getText(doc);
        
    }

    /**
     *
     * @return true
     * @throws IOException
     */
    public void close() throws IOException{
        doc.close();
    }
      
    
    public void toTxt(String filePath) throws IOException{
        File file = new File(filePath);
        doc = PDDocument.load(file);
        String text = new PDFTextStripper().getText(doc);
        
        String fileName = "Hola3.txt";
        PrintWriter output = new PrintWriter(fileName); 
        output.println(text);
        output.close();
        
    }
    
}
