package GUI;

import BinaryTree.BSTree;
import Logic.Document;
import Logic.FileSorter;
import Logic.Library;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Logic.UniveralReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.stage.FileChooser;

/**
 *
 * @author Jose and Natalia
 */
public class main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("TextFinderFXML.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Text Finder");
        stage.setScene(scene);
        stage.show();
        
        System.out.println("Hello there");
        
        //System.out.println(Logic.UniveralReader.readPdf());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //launch(args);
       
        Library libr = new Library();
        Document docum = new Document(UniveralReader.read(),"Archivo1","2/20",4);
        libr.add(docum);
        
    }
    
    
}
