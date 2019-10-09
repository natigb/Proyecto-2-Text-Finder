/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinkedList;

/**
 *
 * @author Jose and Natalia
 */
public class Node<T> {
    private T data;
    private Node<T> next;

    /**
     * Constructor de la clase.
     */
    public Node(T data) {
        this.next = null;
        this.data = data;
    }

    //         ____________________
    //________/Getters and Setters
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return this.next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
    
}
