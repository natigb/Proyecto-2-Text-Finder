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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.text.TextFlow;
import javafx.scene.text.Text;

import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


/**
 *
 * @author Natialia and Jose
 */
public class TextFinderFXMLController implements Initializable {
    private Library library = new Library();
    private LinkedList<Document> results = new LinkedList();
    
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
    private TextArea viewText;
    @FXML
    private VBox resultText;
    @FXML
    private VBox vboxLib;
    
    
    @FXML
    private MenuButton sortChoice;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        System.out.println("You clicked!");
        Document newDoc = new Document();
        newDoc.setText(newDoc.getName());
        newDoc.setFont(new Font("Simular",20));
        newDoc.setOnMouseClicked(openDocLib);
        library.add(newDoc);
        library.printTree();
        vboxLib.getChildren().add(newDoc);
        
    
    }

    //     ____________________________________
    //__/Propuesta de Jose
    @FXML
    private void searchAction(ActionEvent event) throws IOException{
        results.clearList();
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

    //     ____________________________________
    //__/Propuesta de Nati
    @FXML
    private void searchAction(ActionEvent event) throws IOException{
        results.clearList();
        String word = searchText.getText();
        LinkedList docsFound = library.listOfDocs(word);
        for (int i=0; i < docsFound.getSize(); i++){
            DocumentIndex currentDoc = (DocumentIndex)docsFound.serchByIndex(i).getData();
            results.insertLast(currentDoc.getDoc());
        }
        showResults();
    }
    
    private void showResults(){
        resultText.getChildren().clear();
        for (int i=0; i < results.getSize(); i++){
            Document currentDoc = (Document)results.serchByIndex(i).getData();
            int firstPos = (int)library.listOfPositions(currentDoc, searchText.getText()).serchByIndex(0).getData();
            String context = currentDoc.getContent()[firstPos]+ " ";
            
            for (int j=1; j<10; j++){
                if (firstPos-j>=0){
                    context= currentDoc.getContent()[firstPos-j]+" "+context;
                }
                if (firstPos+j<currentDoc.getContent().length){
                    context= context +" "+currentDoc.getContent()[firstPos+j];
                }
            }
            context = i+ ":"+currentDoc.getName()+": "+context;
           
            Label label = new Label();
            label.setText(context);
            label.setWrapText(true);
            label.setMaxSize(300, 200);
            label.setAlignment(Pos.CENTER);
            label.setOnMouseClicked(openDocResult);
            resultText.getChildren().add(label);
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
        
    
        

    
    }
     
    EventHandler<MouseEvent> openDocLib= new EventHandler<MouseEvent>(){
        @Override
            public void handle(MouseEvent t) {
                viewText.clear();
                Document doc = (Document)(t.getSource());
                viewText.setText(doc.getTexto());
            }
    };
    EventHandler<MouseEvent> openDocResult= new EventHandler<MouseEvent>(){
        @Override
            public void handle(MouseEvent t) {
                viewText.clear();
                Label l = (Label)(t.getSource());
                int index = Integer.parseInt(l.getText().split(":")[0]);
                Document doc = (Document)results.serchByIndex(index).getData();
                viewText.setText(doc.getTexto());
            }
    };
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO  
        resultText.setPadding(new Insets(10, 10, 30, 10));
    } 
}
