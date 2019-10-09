/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textfinderv1;

import java.io.IOException;
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
        ordenar.print(arr);
        
        BSTree tree = new BSTree();
        tree.insert(5, null);
        tree.insert(2, null);
        tree.insert(1, null);
        tree.insert(3, null);
        tree.insert(8, null);
        tree.insert(6, null);
        //tree.traverseInOrder();
        //System.out.println("Max"+tree.findMax());
        //System.out.println("Min"+tree.findMin());
        //tree.delete(1);
        //tree.delete(8);
        //System.out.println("Max"+tree.findMax());
        //System.out.println("Min"+tree.findMin());
        
        //tree.traverseInOrder();
        
    }
    
}
