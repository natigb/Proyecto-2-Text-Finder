/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import LinkedList.LinkedList;

/**
 *
 * @author Nati Gonzalez
 */
public class DocumentIndex {
    
    private Document doc;
    private LinkedList<Integer> position;

    public DocumentIndex(Document doc) {
        this.doc = doc;
        this.position = new LinkedList();
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }
    
    public void addPosition(int pos){
        position.insertLast(pos);
    }
    public Document getDoc() {
        return doc;
    }

    public LinkedList getPosition() {
        return position;
    }
    
    
    
}
