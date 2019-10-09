/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textfinderv1;

import java.io.IOException;
import java.text.Collator;
import java.util.Locale;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Nati Gonzalez
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("UserInterface.fxml"));
        Scene scene = new Scene(root,1300,650);
        //scene.getStylesheets().add("Proyecto1/StyleSheet.css");
        stage.setTitle("Text Finder");
        stage.setScene(scene);
        stage.show();
        
        
    }
    static int comparar(String word1, String word2){
        Collator espCollator = Collator.getInstance(Locale.getDefault());
        espCollator.setStrength(Collator.SECONDARY);
        return espCollator.compare(word1, word2);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //launch(args);
        //Documento doc = new Documento();
        //doc.ah();
        FileOrder ordenar = new FileOrder();
        int arr[] ={170,45,75,90,802,24,2,66};
        ordenar.sortBySize(arr);
       
        
        BSTree tree = new BSTree();
        tree.insert("Encantada");
        tree.insert("perro", null);
        tree.insert("paleta", null);
        tree.insert("casa", null);
        tree.insert("muñeca", null);
        tree.insert("cafe", null);
        tree.insert("cafe", null);
        tree.traverseInOrder();
        
        System.out.println(tree.contains("encantda"));
        
    }
    
}
