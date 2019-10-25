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
 * Clase adatador que se encarga de enviar lo solicitado, sin importar la extension del documento 
 * @author Jose and Natalia
 */
public class UniversalReader implements UniversalFile {
    
    private static PDDocument doc;
    
    /**
     * Ejecuta el la lectura de un documento .txt
     * @param file Ruta del archivo a leer 
     * @return string con todo el contenido del documento 
     * @throws FileNotFoundException
     * @throws IOException 
     */
    @Override
    public String read(TXT file)throws FileNotFoundException, IOException{ 
        return file.readTxt(file.getSource().getAbsolutePath());
    }

    /**
     * Ejecuta el la lectura de un documento .txt
     * @param file Ruta del archivo a leer 
     * @return string con todo el contenido del documento 
     * @throws FileNotFoundException
     * @throws IOException 
     */
    @Override
    public String read(DOCX file) throws FileNotFoundException, IOException {
        return ((DOCX)file).readDocx(file.getSource().getAbsolutePath());
    }

    /**
     * 
     * @param file Ruta del archivo a leer 
     * @return string con todo el contenido del documento 
     * @throws FileNotFoundException
     * @throws IOE
     */
    @Override
    public String read(PDF file) throws FileNotFoundException, IOException {
        return ((PDF)file).readPdf(file.getSource().getAbsolutePath());
    }
}
    
  