/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import LinkedList.LinkedList;

/**
 * Clase que guarda un documento y una lista de posiciones 
 * @author Natalia Gonzalez
 */
public class DocumentIndex {
    
    private Document doc;
    private LinkedList<Integer> position;

    public DocumentIndex(Document doc) {
        this.doc = doc;
        this.position = new LinkedList();
    }
    /**
     * Agrega una posición a la lista
     * @param pos 
     */
    public void addPosition(int pos){
        position.insertLast(pos);
    }
    
    //              _________________________
    //_____________/ Getters y Setters
    public void setDoc(Document doc) {
        this.doc = doc;
    }
    
    public Document getDoc() {
        return doc;
    }

    public LinkedList getPosition() {
        return position;
    }
    
    
    
}
