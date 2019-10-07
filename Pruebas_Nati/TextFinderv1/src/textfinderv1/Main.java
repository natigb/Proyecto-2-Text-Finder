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
        
        
        
        
        
        BSTree tree = new BSTree();
        tree.insert(5, null);
        tree.insert(2, null);
        tree.insert(1, null);
        tree.insert(3, null);
        tree.insert(8, null);
        tree.insert(6, null);
        tree.walkInOrder();
        System.out.println(tree.contains(5));
        System.out.println(tree.contains(342));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
