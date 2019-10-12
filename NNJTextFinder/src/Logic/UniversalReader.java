package Logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;


/**
 *
 * @author Jose and Natalia
 */
public class UniversalReader {
    
    private static PDDocument doc;
    
    public static String read(File file,String extension)throws FileNotFoundException, IOException{
        
        switch(extension){
            case "txt":
                 return readTxt(file.getAbsolutePath());
            case "pdf":
                return readPdf(file.getAbsolutePath());
            case "docx":
                return readDocx(file.getAbsolutePath());
        
            default:
                return null;   
        }
    }
    
    //         ____________
    //________/Read TXT
    
    private static String readTxt(String path) throws FileNotFoundException, IOException{
        String text = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String currentLine = reader.readLine();
            text = currentLine;
            while (currentLine != null){
                currentLine = reader.readLine();
                text = text+currentLine;
            }
        }catch(IOException ex){
            Logger.getLogger(UniversalReader.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print(ex);
        }
        return text;
    }
    
    //         ____________
    //________/Read PDF
    
    private static String readPdf(String path) throws IOException{
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
    public static void closePDF() throws IOException{
        doc.close();
    }
    
    //         ____________
    //________/Read DOCX
    
    private static String readDocx(String path) throws FileNotFoundException, IOException{
        String text = null;
        try(FileInputStream file = new FileInputStream(path);){
            XWPFDocument docx = new XWPFDocument(file);
            
            List<XWPFParagraph> paragraphList = docx.getParagraphs();
            for (XWPFParagraph paragraph: paragraphList){
                text = text + (paragraph.getText());
            }
        }catch(IOException ex){
          Logger.getLogger(UniversalReader.class.getName()).log(Level.SEVERE, null, ex);
          System.out.print(ex);  
        }        
        return text;
    }
    
}