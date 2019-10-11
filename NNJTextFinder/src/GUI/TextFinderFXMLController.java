/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Logic.Document;
import Logic.Library;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Jose
 */
public class TextFinderFXMLController implements Initializable {
    private Library library = new Library();
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        System.out.println("You clicked!");
        Document gola = new Document();
        
        library.add(gola);
        label.setText("Jose o Nati agregó un archivo...noice!");
        library.printTree();
        
        
        library.listOfDocs("arbol").printList();
    
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
