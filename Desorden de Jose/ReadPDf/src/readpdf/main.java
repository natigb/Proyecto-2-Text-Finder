/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readpdf;

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
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        PDFReader pdfreader = new PDFReader();
        try{
            //String text = pdfreader.toText("src\\readpdf\\hola2.pdf");
            //System.out.println(text);
            //pdfreader.close();
            pdfreader.toTxt("src\\readpdf\\hola2.pdf");
           
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
