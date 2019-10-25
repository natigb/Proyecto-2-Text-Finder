/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import BinaryTree.BSTree;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import java.text.Normalizer;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import LinkedList.LinkedList;
import LinkedList.Node;
import Logic.Document;
import Logic.DocumentIndex;
import Logic.FileSorter;
import Logic.Library;
import static Logic.SortBy.Date;
import static Logic.SortBy.Name;
import static Logic.SortBy.Size;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Locale;
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
import javafx.scene.Cursor;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.text.TextFlow;
import javafx.scene.text.Text;

import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.text.FontWeight;

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
    private LinkedList<Integer> sentencePositions;
    private Boolean sentenceSearched;
    
    @FXML
    private Button addFile;
    @FXML
    private Button searchButton;
    @FXML
    private TextField searchText;
    @FXML
    private TextFlow viewText;
    @FXML
    private VBox resultText;
    @FXML
    private VBox vboxLib;
    @FXML
    private ScrollPane scrollpane;
    @FXML
    private MenuButton sortChoice;
    @FXML
    private RadioButton byWord;
    @FXML
    private RadioButton bySentence;
    
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
        resultText.getChildren().clear();
        docsFound = new LinkedList<>();
        sentencePositions = new LinkedList<Integer>();
        String searched = searchText.getText();
        
        System.out.println(searched+ "--> Esto fue lo que se busco");
        if (!sentenceSearched){
            System.out.println("por palabras");
            String[] sentence = searched.split(" ");
            System.out.println(Arrays.toString(sentence));
            for(String word : sentence){
                //word =Document.deletePunctuationMarks(word, word.length());
                LinkedList<Document> newDocsFound = library.listOfDocs(word);
                System.out.println(word+ "--> Esto fue lo que se mando a buscar");
                //showResults(newDocsFound);
                if(newDocsFound != null){
                    //showResults(newDocsFound);
                    docsFound.mergeLinkedList(newDocsFound);
                    
                }
            }
            if (docsFound == null){
                docsFound = new LinkedList<>();
                notFoundEx();
            }else{
                System.out.println(Arrays.toString(FileSorter.toArray(docsFound)));
                docsFound.deleteReapeatedData();
                showResults(docsFound);
            }
            
        }else{
            searchBySentence(searched);
            showResults(docsFound);
        }
        
        //showResults();
    }
    
    private void searchBySentence(String word){
            String[] sentence = word.split(" ");
            System.out.println("Es oracion");
            LinkedList<DocumentIndex> tempDocs = library.listOfIndxDocs(sentence[0]);
            if (tempDocs!=null){
                System.out.println("Esta la primera palabra");
                Node<DocumentIndex> currentDocIndx = tempDocs.getHead();
                while (currentDocIndx.getNext() != null){
                    Document currentDoc = currentDocIndx.getData().getDoc();
                    if (currentDoc.containsSentence(sentence,currentDocIndx.getData().getPosition())){
                        docsFound.insertFirst(currentDoc);
                        System.out.println("doc insertado");
                        sentencePositions.insertFirst(currentDoc.getSentenceIndx());
                        System.out.println((currentDoc.getTexto().contains(word))+word+"si esta!");
                    }
                    currentDocIndx = currentDocIndx.getNext();
                }
                Document currentDoc = currentDocIndx.getData().getDoc();
                if (currentDoc.containsSentence(sentence,currentDocIndx.getData().getPosition())){
                    System.out.println("doc insertado");
                    docsFound.insertFirst(currentDoc);
                    sentencePositions.insertLast(currentDoc.getSentenceIndx());
                    System.out.println((currentDoc.getTexto().contains(word))+word+"si esta!");
                }//else{
                    //System.out.println("No hay oraciones coindicentes");
                    //notFoundEx();
                //}
            }else{
                System.out.println("No est� ni la primera palabra");
                notFoundEx();
            }
        
    }
        
    private void notFoundEx(){
        //docsFound = new LinkedList<Document>();
        resultText.getChildren().clear();
        Text notFound = new Text("No results found");
        resultText.getChildren().add(notFound);
    }
    
    private void showResults(LinkedList<Document> docsFound){
        int firstPos;
        String searched = searchText.getText();
        String[] sentence = searched.split(" ");
        if (docsFound.getHead() != null){
        results = FileSorter.sortDocumentsBy(docsFound, sortCriterion);
        //resultText.getChildren().clear();
        for (int i=0; i < results.getSize(); i++){
            Document currentDoc = results.serchByIndex(i).getData();
            //System.out.println(searchText.getText().serchByIndex(0).getData()+"esta es searched text");
            if (sentenceSearched){
                LinkedList listOfPos = library.listOfPositions(currentDoc,sentence[0]);
                    
                    if (listOfPos.getHead() != null){
                        firstPos = (int)listOfPos.serchByIndex(0).getData();
                        createResultReference(currentDoc,firstPos,i,sentence.length);
                    }
            }
            else{
                for(String word : sentence){
                    LinkedList listOfPos = library.listOfPositions(currentDoc,word);
                    
                    if (listOfPos.getHead() != null){
                        firstPos = (int)listOfPos.serchByIndex(0).getData();
                        createResultReference(currentDoc,firstPos,i,sentence.length);
                        }
                    }
                }
        
            }
        }
    }
    
        public void createResultReference(Document currentDoc,int firstPos, int i,int size){
            String context = currentDoc.getContent()[firstPos]+ " ";
            String bfContext="";
            String atContext="";
            for (int j=1; j<10; j++){
                if (firstPos-j>=0){
                    context= currentDoc.getContent()[firstPos-j]+" "+context;
                    bfContext= currentDoc.getContent()[firstPos-j]+" "+bfContext;
                    
                }
            }
            for (int j=10; j>0; j--){

                if (firstPos+j+5<currentDoc.getContent().length){
                    context= context +" "+currentDoc.getContent()[firstPos+j+size-1];
                    atContext= currentDoc.getContent()[firstPos+j+size-1]+" "+atContext;
                                    
                }
            }
            
            context = ": "+currentDoc.getName()+"\n";
            Text contextT = new Text(context);
            contextT.setFont(new Font("Arial",18));
            String searchedFor = currentDoc.getContent()[firstPos]; 
            if (sentenceSearched){
            for (int z=1 ; z < size;z++){
                searchedFor += " "+currentDoc.getContent()[firstPos+z];
                //searchedFor = searchText.getText();
            }
            }
            Text word= new Text(searchedFor);
            word.setFont(new Font("Arial",15));
            word.setFill(Color.web("blue", 0.8));
            Text beforeContxt = new Text("..."+bfContext);
            Text afterContxt = new Text(" "+atContext+"...");
            Text numTxt = new Text(Integer.toString(i));
            numTxt.setFont(new Font("Arial",18));
            
            TextFlow tf = new TextFlow();
            
            tf.getChildren().addAll(numTxt,contextT,beforeContxt, word, afterContxt);
            tf.setOnMouseClicked(openDocResult);
            resultText.getChildren().add(tf);
            
        }
    
        public void sizeSort (ActionEvent e){
            sortCriterion = Size;
            resultText.getChildren().clear();
            showResults(docsFound);
            sortChoice.setText("Size");
        }
        
        public void dateSort (ActionEvent e){
            sortCriterion = Date;
            resultText.getChildren().clear();
            showResults(docsFound);
            sortChoice.setText("Date");
        }
        
        public void nameSort (ActionEvent e){
            sortCriterion = Name;
            resultText.getChildren().clear();
            showResults(docsFound);
            sortChoice.setText("Name");
        }
       
        public void bySentence(ActionEvent e){
            byWord.setSelected(false);
            sentenceSearched = true;
            
        }
        
        public void byWord(ActionEvent e){
            bySentence.setSelected(false);
            sentenceSearched = false;
        }
  
    
    EventHandler<MouseEvent> openDocLib= new EventHandler<MouseEvent>(){
        @Override
            public void handle(MouseEvent t) {
                Document doc = (Document)(t.getSource());
                
                
                if (t.getButton()== MouseButton.SECONDARY){
                    ContextMenu context = new ContextMenu();
                    MenuItem elim = new MenuItem("Delete file");
                    MenuItem open = new MenuItem("Open file");
                    MenuItem update = new MenuItem("Update file");
                    elim.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                        library.getLibrary().printList();
                        System.out.println("Elimin� un documento");
                        doc.setText(null);
                        library.deleteDoc(doc);
                        System.out.println(" ");
                        library.getLibrary().printList();
                        String userDir = System.getProperty("user.dir");
                        File file = new File(userDir + "\\src\\Library\\" + doc.getName());
                        file.delete();
                        arrangeVBox();
                        
                        }
                     });
                    
                    open.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            
                            try {
                                Desktop.getDesktop().open(doc.getOrgFile());
                            } catch (IOException ex) {
                                Logger.getLogger(TextFinderFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                     });
                    
                    update.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                        File toUpdate;    
                        if (doc.getOrgFile().getAbsolutePath().equals(doc.getDesFile().getAbsolutePath())){
                            toUpdate = Document.seekFile();
                            String userDir = System.getProperty("user.dir");
                            File file = new File(userDir + "\\src\\Library\\" + doc.getName());
                            file.delete();
                        }else{
                        toUpdate = doc.getOrgFile();
                        }
                        
                        library.getLibrary().printList();
                        System.out.println("Elimin� un documento");
                        doc.setText(null);
                        library.deleteDoc(doc);
                        System.out.println(" ");
                        library.getLibrary().printList();
                        //String userDir = System.getProperty("user.dir");
                        //File file = new File(userDir + "\\src\\Library\\" + doc.getName());
                        //file.delete();
                        arrangeVBox();
                        try {
                                addDocument(toUpdate);
                            } catch (IOException ex) {
                                Logger.getLogger(TextFinderFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        arrangeVBox();
                        }
                     });
                    
                    
                    context.getItems().addAll(open,update,elim);
                    doc.setContextMenu(context);

                }
                else{
                    viewText.getChildren().clear();
                    Text text = new Text(doc.getTexto());
                    viewText.getChildren().add(text);
                }
            }
    };
    EventHandler<MouseEvent> openDocResult= new EventHandler<MouseEvent>(){
        @Override
            public void handle(MouseEvent t) {
                
                int firstPos = 0;
                boolean firstFound = false;
                String[] sentence = searchText.getText().split(" ");;
                viewText.getChildren().clear();
                TextFlow l = (TextFlow)(t.getSource());
                Text text = (Text)(l.getChildren().get(0));
                int index = Integer.parseInt(text.getText());
                Document doc = (Document)results.serchByIndex(index).getData();
                String word = searchText.getText();
                LinkedList<Integer> allPositions= new LinkedList();
                System.out.println("hola");
                //if (!sentenceSearched){
                    //sentence = searchText.getText().split(" ");
                    //firstPos = (int)library.listOfPositions(doc, sentence[0]).serchByIndex(0).getData();
                //}
                for (int i=0; i<doc.getContent().length; i++){
                    boolean equal = false;
                    Text space = new Text(" ");
                    
                    if (word.contains("")){
                        sentence = word.split(" ");
                    }
                    Text words = new Text(doc.getTexto().split(" ")[i]);
                    //words.setFont(new Font("Arial",12));
                    if (!sentenceSearched){
                        for (int j=0;j<sentence.length;j++){
                                if(BSTree.comparar(sentence[j],doc.getContent()[i])==0){
                                    words.setFill(Color.web("blue", 0.8));
                                    break;
                                }
                        }
                    }else{
                        if(BSTree.comparar(sentence[0], doc.getContent()[i])==0){
                            for (int j=0;j<sentence.length;j++){
                                if(BSTree.comparar(sentence[j],doc.getContent()[i+j])!=0){
                                    equal = false;
                                    break;
                                }
                                equal = true;
                            }
                            if (equal){
                                if (!firstFound){
                                    firstPos = i;
                                    firstFound = true;
                                }
                                for(int k=0; k<sentence.length;k++){
                                    words = new Text(doc.getContent()[i+k]);
                                    space = new Text(" ");
                                    words.setFill(Color.web("blue", 0.8));
                                    //words.setFont(new Font("Arial",12));    
                                    viewText.getChildren().addAll(words,space);
                                }
                                i+=sentence.length-1;
                            }    
                        }
                    }
                    if(!equal){
                        viewText.getChildren().addAll(words,space);
                    }
                }
                String[] renglones=doc.getTexto().split("\n");
                int pos= 0;
                System.out.println(firstPos);
                for(int j=0;j<renglones.length;j++){
                    
                    if (pos>=firstPos){
                        pos=j;
                        break;
                    }
                    pos+=renglones[j].split(" ").length-1;
                    System.out.println(renglones[j]);
                }
               
                Double scrollPos = Double.valueOf(pos)/Double.valueOf(renglones.length);
               
                viewText.heightProperty().addListener(observable -> scrollpane.setVvalue(scrollPos));
                scrollpane.setVvalue(scrollPos);
                
                
                
            }
    };
    
    public void arrangeVBox(){
        vboxLib.getChildren().clear();
        for (int i=0; i<library.getLibrary().getSize();i++){
            vboxLib.getChildren().add((library.getLibrary().serchByIndex(i).getData()));
        }
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO 
        
        resultText.setSpacing(30);
        bySentence.setSelected(true);
        sentenceSearched = true;
        String userDir = System.getProperty("user.dir");
        File thisLibrary = new File(userDir + "\\src\\Library");
        try {
            addToLibrary(thisLibrary);
        } catch (IOException ex) {
            Logger.getLogger(TextFinderFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Image img = new Image("GUI/CSS and aesthetics/templateD.jpg");
        //BackgroundImage bc = new BackgroundImage(img,BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        //viewText.setBackground(new Background(bc));
    } 
}
