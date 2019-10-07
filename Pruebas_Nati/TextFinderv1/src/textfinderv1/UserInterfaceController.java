/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textfinderv1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * FXML Controller class
 *
 * @author Nati Gonzalez
 */
public class UserInterfaceController implements Initializable {
    @FXML
    private TextFlow textflow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Text text = null;
        try {
            text = new Text(readtxt());
        } catch (IOException ex) {
            Logger.getLogger(UserInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        textflow.getChildren().add(text);
    }    
    
    private String readtxt() throws FileNotFoundException, IOException{
        BufferedReader reader = new BufferedReader(new FileReader("src/textfinderv1/Biblioteca/ArchivoDOCX.docx"));
        String currentLine = reader.readLine();
        String text = currentLine;
        while (currentLine != null){
            currentLine = reader.readLine();
            text = text+currentLine;
            
        }
        reader.close();
        return text;
    }
    
    /*private String readpdf(){
        XWPFDocument docx = new XWPFDocument(OPCPackage.openOrCreate(new File("hello.docx")));
        XWPFWordExtractor wx = new XWPFWordExtractor(docx);
        String text = wx.getText();
        
        return text;
    }*/
}
