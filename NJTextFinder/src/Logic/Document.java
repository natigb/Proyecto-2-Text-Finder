/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

/**
 *
 * @author Nati Gonzalez
 */
public class Document {
    private String[] content;
    private String name;
    private String date;
    private int size;

    public Document(String content, String name, String date, int size) {
        this.content = content.split(" ");
        this.name = name;
        this.date = date;
        this.size = size;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    


    
}
    
    
    
