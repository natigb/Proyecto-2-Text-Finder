/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import LinkedList.LinkedList;
import Logic.Document;
import Logic.DocumentIndex;
import Logic.FileSorter;
import Logic.Library;
import static Logic.SortBy.Date;
import static Logic.SortBy.Name;
import static Logic.SortBy.Size;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
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
    
    private Logic.SortBy sortCriterion = Name;
    private LinkedList<Document> docsFound;
    
    @FXML
    private Button addFile;
    @FXML
    private Button searchButton;
    @FXML
    private TextField searchText;
    @FXML
    private VBox vbox;
    
    
    @FXML
    private MenuButton sortChoice;
    
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
        //LinkedList docsFound = library.listOfDocs(word);
        docsFound = library.listOfDocs(word);
        docsFound.printList();
        showFoundDocs();
    }
    
    private void showFoundDocs(){
        vbox.getChildren().clear();
        if (docsFound != null){
            docsFound = FileSorter.sortDocumentsBy(docsFound, sortCriterion);
            for (int i=0; i < docsFound.getSize(); i++){
                Document currentDoc = docsFound.serchByIndex(i).getData();
                Label label = new Label();
                label.setText(currentDoc.getName());
                vbox.getChildren().add(label);
                }
            }
        }
    
        
        public void sizeSort (ActionEvent e){
            sortCriterion = Size;
            showFoundDocs();
            sortChoice.setText("Size");
        }
        
        public void dateSort (ActionEvent e){
            sortCriterion = Date;
            showFoundDocs();
            sortChoice.setText("Date");
        }
        
        public void nameSort (ActionEvent e){
            sortCriterion = Name;
            showFoundDocs();
            sortChoice.setText("Name");
        }
        
    
        

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //String st[] = { "Arnab", "Andrew", "Ankit", "None" };
        //ObservableList<String> availableChoices = FXCollections.ObservableArrayList("apples", "oranges"); 
        //sortChoice.setItems(availableChoices);
        
        //sortChoice.getItems().removeAll(sortChoice.getItems());
        //sortChoice.getItems().addAll("Name", "Size", "Date");
    }    
    
}
