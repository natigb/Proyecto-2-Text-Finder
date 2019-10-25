/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

/**
 *
 * @author Jose
 */
public class DOCX {
    
    private File source;
    
    public static String readDocx(String path) throws FileNotFoundException, IOException{
        String text = "";
        try(FileInputStream file = new FileInputStream(path);){
            XWPFDocument docx = new XWPFDocument(file);
            
            List<XWPFParagraph> paragraphList = docx.getParagraphs();
            for (XWPFParagraph paragraph: paragraphList){
                text = text +"\n" + (paragraph.getText());
            }
        }catch(IOException ex){
          Logger.getLogger(UniversalReader.class.getName()).log(Level.SEVERE, null, ex);
          System.out.print(ex);  
        }        
        return text;
    }

    DOCX(File source) {
        this.source = source;
    }

    public File getSource() {
        return source;
    }
    

   
}
