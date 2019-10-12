/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import LinkedList.LinkedList;
import Logic.Document;
import Logic.DocumentIndex;
import Logic.Library;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


/**
 *
 * @author Natialia and Jose
 */
public class TextFinderFXMLController implements Initializable {
    private Library library = new Library();
    //private Image icon = new Image("res/icon");
    
    
    @FXML
    private Button addFile;
    @FXML
    private Button searchButton;
    @FXML
    private TextField searchText;
    @FXML
    private VBox vbox;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        System.out.println("You clicked!");
        Document gola = new Document();
        
        library.add(gola);
        library.printTree();
        
        
        //library.listOfDocs("arbol").printList();
        //System.out.println(" ");
        //library.listOfPositions(gola, "dghjkocx").printList();
        //library.listOfPositions(gola, "arbol").printList();
        //library.listOfPositions(gola, "al").printList();
        //library.listOfPositions(gola, "agregar").printList();
    
    
    }
    @FXML
    private void searchAction(ActionEvent event) throws IOException{
        vbox.getChildren().clear();
        String word = searchText.getText();
        LinkedList docsFound = library.listOfDocs(word);
        docsFound.printList();
        for (int i=0; i < docsFound.getSize(); i++){
            DocumentIndex currentDoc = (DocumentIndex)docsFound.serchByIndex(i).getData();
            Label label = new Label();
            label.setText(currentDoc.getDoc().getName());
            vbox.getChildren().add(label);
           
        }
        

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
