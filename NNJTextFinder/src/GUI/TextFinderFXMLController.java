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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        File newFile = Document.seekFile();
        addToLibrary(newFile);
    }
        
    private void addToLibrary(File newFile) throws IOException{
        if (newFile.isFile()){
            addDocument(newFile);
        }
        if (newFile.isDirectory()){
            Files.walk(Paths.get(newFile.getAbsolutePath())).filter(Files::isRegularFile).forEach((p)->{
                try {
                    addDirectory(p);
                } catch (IOException ex) {
                    Logger.getLogger(TextFinderFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        
        }
    }
      private void addDirectory(Path p) throws IOException {
        String path = p.toString();
        File newFile = new File(path);
        addDocument(newFile);
    }
        
    private void addDocument(File newFile) throws IOException{
        Document newDoc = new Document(newFile);
        newDoc.setText(newDoc.getName());
        newDoc.setFont(new Font("Simular",20));
        newDoc.setOnMouseClicked(openDocLib);
       
        if (!library.add(newDoc)){
            vboxLib.getChildren().add(newDoc);
        }    
        library.printTree();
        
    
    }

    @FXML
    private void searchAction(ActionEvent event) throws IOException{
        results.clearList();
        String word = searchText.getText();
        docsFound = library.listOfDocs(word);
        if (docsFound == null){
            resultText.getChildren().clear();
            Text notFound = new Text("No results found");
            resultText.getChildren().add(notFound);
        }
        showResults();
    }
    
    private void showResults(){
        if (docsFound != null){
        results = FileSorter.sortDocumentsBy(docsFound, sortCriterion);
        resultText.getChildren().clear();
        for (int i=0; i < results.getSize(); i++){
            Document currentDoc = results.serchByIndex(i).getData();
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
            
            context = ": "+currentDoc.getName()+"\n";
            Text contextT = new Text(context);
            contextT.setFont(new Font("Arial",18));
            Text word= new Text(searchText.getText());
            word.setFont(new Font("Arial",15));
            word.setFill(Color.web("blue", 0.8));
            Text beforeContxt = new Text("..."+bfContext);
            Text afterContxt = new Text(" "+atContext+"...");
            Text numTxt = new Text(Integer.toString(i));
            numTxt.setFont(new Font("Arial",18));
            
            TextFlow tf = new TextFlow();
            
            tf.getChildren().addAll(numTxt,contextT,beforeContxt, word, afterContxt);
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
    }
    
        
        public void sizeSort (ActionEvent e){
            sortCriterion = Size;
            showResults();
            sortChoice.setText("Size");
        }
        
        public void dateSort (ActionEvent e){
            sortCriterion = Date;
            showResults();
            sortChoice.setText("Date");
        }
        
        public void nameSort (ActionEvent e){
            sortCriterion = Name;
            showResults();
            sortChoice.setText("Name");
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
        String userDir = System.getProperty("user.dir");
        File thisLibrary = new File(userDir + "\\src\\Library");
        try {
            addToLibrary(thisLibrary);
        } catch (IOException ex) {
            Logger.getLogger(TextFinderFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 

    
}
