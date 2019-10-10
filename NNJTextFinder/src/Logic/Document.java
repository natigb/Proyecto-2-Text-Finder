/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import javafx.stage.FileChooser;

/**
 *
 * @author Nati Gonzalez
 */
public class Document {
    private String text;
    private String[] content;
    private String name;
    private int date;
    private int size;
    
    public Document() throws IOException{
        generateFileCopy();
    }
    
    private void documentAux(String content, String name, int date, int size) {
        
        this.text = content;
        this.content = content.split(" ");
        this.name = name;
        this.date = date;
        this.size = size;
    }
    
    private void generateFileCopy() throws IOException {
        File originFile = seekFile();
        File destitationFile = new File(originFile.getName());
        copyFile(originFile,destitationFile);
    }
    
    private void copyFile(File source, File dest) throws IOException {
        String userDir = System.getProperty("user.dir");
        System.setProperty("user.dir",((System.getProperty("user.dir"))+"\\src\\Library"));
        Files.copy(source.toPath(), dest.toPath(),REPLACE_EXISTING);
        System.setProperty("user.dir",userDir);
        documentAux(UniversalReader.read(source),source.getName(),(int)(source.lastModified()),(int)(source.length()));
}
    
    private File seekFile() {
        FileChooser selector = new FileChooser();
        File selectedFile = selector.showOpenDialog(null);
        return selectedFile;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
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
     
}
    
    
    
