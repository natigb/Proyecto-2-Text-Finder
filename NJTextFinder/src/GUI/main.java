package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
    public static void main(String[] args) {
        launch(args);
    }
    
}
