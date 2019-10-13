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
 * @author Nati Gonzalez and Jose 
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
        String userDir = System.getProperty("user.dir");
        System.setProperty("user.dir",((System.getProperty("user.dir"))+"\\src\\Library"));
        Files.copy(source.toPath(), dest.toPath(),REPLACE_EXISTING);
        System.setProperty("user.dir",userDir);
        
        Long Longdate = (Long)source.lastModified();
        int date = Longdate.intValue();
        documentAux(UniversalReader.read(source,extension),source.getName(),date,(int)(source.length()));
        if ("pdf".equals(extension)){
            UniversalReader.closePDF();
        }
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
    
    
    
