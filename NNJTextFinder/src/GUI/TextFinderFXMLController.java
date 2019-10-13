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
            String bfContext="";
            String atContext="";
            
            for (int j=1; j<10; j++){
                if (firstPos-j>=0){
                    context= currentDoc.getContent()[firstPos-j]+" "+context;
                    bfContext= currentDoc.getContent()[firstPos-j]+" "+bfContext;
                    
                }
                if (firstPos+j<currentDoc.getContent().length){
                    context= context +" "+currentDoc.getContent()[firstPos+j];
                    atContext= currentDoc.getContent()[firstPos+j]+" "+atContext;
                }
            }
            
            context = " :"+currentDoc.getName()+": "+context;
            Text word= new Text(searchText.getText());
            word.setFont(new Font("Arial",15));
            word.setFill(Color.web("red", 0.8));
            Text beforeContxt = new Text(": "+bfContext);
            Text afterContxt = new Text(" "+atContext);
            Text numTxt = new Text(Integer.toString(i));
            
            TextFlow tf = new TextFlow();
            
            tf.getChildren().addAll(numTxt,beforeContxt, word, afterContxt);
            /*Label label = new Label();
            label.setText(context);
            label.setWrapText(true);
            label.setMaxSize(300, 200);
            label.setAlignment(Pos.CENTER);
            label.setOnMouseClicked(openDocResult);*/
            tf.setOnMouseClicked(openDocResult);
            resultText.getChildren().add(tf);
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
                TextFlow l = (TextFlow)(t.getSource());
                Text text = (Text)(l.getChildren().get(0));
                int index = Integer.parseInt(text.getText());
                Document doc = (Document)results.serchByIndex(index).getData();
                viewText.setText(doc.getTexto());
            }
    };
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO  
        resultText.setSpacing(30);
    } 
}
