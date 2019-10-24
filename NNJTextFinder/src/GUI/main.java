/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Logic.Document;
import Logic.FileSorter;
import Logic.Library;
import Logic.UniversalReader;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Jose
 */
public class main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("NJTextFinderFXML.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        System.out.println("Hello World");
        
        //Desktop.getDesktop().open(new File("C:\\Users\\Jose\\Documents\\TEC\\Semestre II\\Datos I\\Proyecto 2\\Proyecto-2-Text-Finder\\NNJTextFinder\\src\\Library\\El Estado.docx"));
        
        //File hola = new File("C:\\Users\\Jose\\Documents\\TEC\\Semestre II\\Datos I\\Proyecto 2\\Proyecto-2-Text-Finder\\NNJTextFinder\\src\\Library\\prueba.txt");
        //System.out.println(UniversalReader.read(hola,"txt"));

        //System.out.println(Document.deletePunctuationMarks("hola", 5));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        launch(args);
        
        
        
    }

    
    
}
