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
import java.io.File;
import java.io.IOException;
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
        
        File hola = new File("C:\\Users\\Jose\\Documents\\TEC\\Semestre II\\Datos I\\Proyecto 2\\Proyecto-2-Text-Finder\\NNJTextFinder\\src\\Library\\prueba.txt");
        System.out.println(UniversalReader.read(hola,"txt"));
        
        //int[] arr = new int[]{11,6,3,5,8,5,3,3,6,89,65,4};
        //FileSorter.sortByDate(arr);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        launch(args);
        System.out.println("ahhh");
        
        
        
    }
    
}
