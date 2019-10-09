/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinkedList;

/**
 *
 * @author Jose and Natalia
 * @param <T>
 */
public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    //         __________________
    //________/Getters n' Setters
    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public void setTail(Node<T> tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    

    /**
     * Metodo que se encarga de insertar un nodo con un dato al inicio de la lista enlazada.
     *
     * @param data Dato tipo Object que se desea que el nuevo nodo contenga.
     */

    public void insertFirst(T data){
        Node newNode = new Node<T>(data);
        newNode.setNext(this.head);
        this.head = newNode;
        if (size == 0){
            this.tail = newNode;
        }
        this.size++;
    }

    /**
     * Funci�n qe crea un nodo a partir de los datos que se ingresen y lo pone al inicio de la lista
     * @param data Dato tipo Object que se desea que el nuevo nodo contenga.
     */

    public void insertLast(T data){
        if (head==null){
            head = new Node<T>(data);
            tail = new Node<T>(data);
        }
        else{
            Node current = head;
            Node newNode = new Node<T>(data);
            while (current.getNext()!= null){
                current= current.getNext();
            }
            current.setNext(newNode);
            this.tail = newNode;
        }
        this.size++;

    }

    /**
     * Metodo que se encarga de eliminar el nodo al inicio de la lista enlazada.
     */
    public void deleteFirst(){
        if (this.head != null){
            this.head = this.head.getNext();
            this.size--;
        }
    }

    /**
     * Funci�n que permite buscar un nodo de la lista por el �dice en el que se encuentra
     * @param index Indice de la lista que se quiere buscar
     * @return El nodo que se encuetra en ese �ndice. Null si la lista est� vac�a.
     */
    public Node<T> serchByIndex(int index) {
        if (head != null) {
            Node current = this.head;
            for (int x = 0; x < index; x++) {
                current = current.getNext();
            }
            return current;
        }
        return null;
    }

    /**
     * Metodo que se encarga de cambiar el dato del nodo en una posicion X de la lista enlazada.
     *
     * @param index Indice de la lista donde se quiere insetar el dato. (Empezando en 0).
     * @param data Dato tipo Object que se desea que el nuevo nodo contenga.
     */
    public void setDataByIndex(int index,T data){
        Node current = this.head;
        for (int x = 0; x < index ; x++ ){
            current = current.getNext();
        }
        current.setData(data);

    }

    /**
     * Metodo que se encarga de eliminar el nodo con un dato especifico en la primer posicion en que lo encuentre en lista enlazada.
     *
     * @param data Dato tipo Object que se desea eliminar en una posicion desconocida.
     */
    public void deleteByData(T data){
        //Primero valida para por si es una lista de 2 nodos
        if (head != null){
            if (size>1) {
                if (head.getData() == data) {
                    deleteFirst();
                } else {
                    if (head.getNext().getNext() != null){
                        Node current = this.head;
                        while (current.getNext().getNext() != null) {
                            if (current.getNext().getData() == data) {
                                current.setNext(current.getNext().getNext());
                                size--;
                                break;
                            }
                            current = current.getNext();
                        }
                        if (current.getNext().getData() == data) {
                            current.setNext(current.getNext().getNext());
                            size--;
                        }
                    }else{
                        if (head.getNext().getData() == data){
                            head.setNext(null);
                            size = 1;
                        }
                    }
                }
            }else{
                if (head.getData() == data){
                    head = null;
                    size = 0;
                }
            }
        }
    }
    
    //public void swap(int indx1,int indx2)
        
    
}
