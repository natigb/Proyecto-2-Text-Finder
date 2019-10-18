/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import BinaryTree.BSTree;
import LinkedList.LinkedList;
import LinkedList.Node;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.Arrays;
//import javafx.stage.FileChooser;



/**
 *
 * @author Nati Gonzalez and Jose 
 */
import javafx.scene.control.Label;
import javax.swing.JFileChooser;
public class Document extends Label{
    private String text;
    private String[] content;
    private String name;
    private int date;
    private int size;
    private Integer sentenceFirstWord;
    
    public Document() throws IOException{
        File originFile = seekFile();
        generateFileCopy(originFile);
    }
    
    public Document(File file) throws IOException{
        generateFileCopy(file);
    }
    
    private void documentAux(String content, String name, int date, int size) {
        
        this.text = content;
        this.content = divByWord(content);
        //this.content = content.split(" ");
        this.name = name;
        this.date = date;
        this.size = size;
        
    }
    
    private void generateFileCopy(File originFile) throws IOException {
        String userDir = System.getProperty("user.dir");
        File destitationFile = new File(userDir + "\\src\\Library\\" + originFile.getName());
        
        String extension = "";
        String fileName = originFile.getName();
        int i = fileName.lastIndexOf('.');
        if (i >= 0) {
            extension = fileName.substring(i+1);
        }
        if("txt".equals(extension) || "pdf".equals(extension) || "docx".equals(extension) ){ 
            copyFile(originFile,destitationFile,extension);
        }else{
         System.out.println("Invalid: wrong document extension"); 
        }
    }
    
    private void copyFile(File source, File dest, String extension) throws IOException {
        Files.copy(source.toPath(), dest.toPath(),REPLACE_EXISTING);
        
        Long Longdate = (Long)source.lastModified();
        int date = Longdate.intValue();
        documentAux(UniversalReader.read(source,extension),source.getName(),date,(int)(source.length()));
        if ("pdf".equals(extension)){
            UniversalReader.closePDF();
        }
}
    
    public static File seekFile() {
        JFileChooser selector = new JFileChooser();
        selector.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        selector.showSaveDialog(null);
        File selectedFile = selector.getSelectedFile();
        return selectedFile;
    }
    
    public String getTexto() {
        return text;
    }

    public void setTexto(String text) {
        this.text = text;
    }

        
    public String[] getContent() {
        return content;
    }

    public void setContent(String[] content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private String[] divByWord(String content) {
        String[] words = content.split(" ");
        int i = 0;
        for (String word : words ){
            word = deletePunctuationMarks(word,word.length());
            words[i] = word;
            i++;
        }
        System.out.println(Arrays.toString(words));
        return words;
    }

    public static String deletePunctuationMarks(String word, int length) {
        for (int i=0;i<length;i++){
            char symbol = word.charAt(i);
            if (!Character.isLetter(symbol)){
                StringBuilder newWord = new StringBuilder (word);
                newWord.deleteCharAt(i);
                word = newWord.toString();
                i--;
                length--;
            }
        }
        return word;
    }

    
    public boolean containsSentence(String[] words, LinkedList position) {
        Node current = position.getHead();
        boolean equal = false;
        while (current.getNext() != null){
            for (int i=0;i<words.length;i++){
                if(BSTree.comparar(words[i],content[(int)current.getData()+i])<0 || BSTree.comparar(words[i],content[(int)current.getData()+i])>0){
                    equal = false;
                    break;
                }
                equal = true;
                sentenceFirstWord = (int)position.getHead().getData();
            }
            current = current.getNext();
        }
        for (int i =0;i<words.length;i++){
                if(BSTree.comparar(words[i],content[(int)current.getData()+i])<0|| BSTree.comparar(words[i],content[(int)current.getData()+i])>0){
                    equal = false;
                    break;
                }
                equal = true;
                sentenceFirstWord = (int)position.getHead().getData();
            }
        return equal;
    }

    
    public Integer getSentenceIndx() {
        return sentenceFirstWord;
    }

     
}
    
    
    
