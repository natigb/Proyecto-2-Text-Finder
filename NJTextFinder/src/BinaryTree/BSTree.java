/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BinaryTree;

import LinkedList.LinkedList;
import Logic.Document;
import java.text.Collator;
import java.util.Locale;

/**
 *
 * @author Nati Gonzalez
 */
public class BSTree {
    private Node root;
    
    private Node root;
    
    public BSTree(){
        this.root = null;
    }
    
    /**
     * Recorre en orden el árbol empezando de la raíz
     */
    public void traverseInOrder(){
        traverseInOrderAux(this.root);        
    }
    /**
     * Función auxiliar de walkInOrder
     * @param x Nodo a partir del que está recorriendo 
     */
    private void traverseInOrderAux(Node x){
        
        if (x!= null){
            traverseInOrderAux(x.left);
            System.out.println(x.key);
            traverseInOrderAux(x.right);
        
        }
    }
    
     /**
     * 
     * @param key
     */
    public LinkedList<Document> search(String key){
        return searchAux(this.root,key);        
    }
    /**
     * 
     * @param x Nodo a partir del que está recorriendo 
     */
    private LinkedList<Document> searchAux(Node x,String key){
        if (x != null){
            
                searchAux(x.left,key);
                if (comparar(x.key,key)==0){
                return x.documents;
            }
                //System.out.println(x.key);
                searchAux(x.right,key);
            }
        return null;
            
        }
    public void insert(String key, Document doc){
        if (!contains(key)){
            insertAux(key, doc);
        }
        else{System.out.println("La llave ya existe");}
    }
    /**
     * Método para agregar un nuevo elemento al árbol
     * @param key Llave del elemento
     * @param doc 
     * 
     */
    public void insertAux (String key, Document doc){
        Node newNode = new Node(key);
        newNode.add(doc);
        
        
        if (root == null){
            root = newNode;
        }
        else{
            Node current = root;
            while (current != null){
                newNode.parent = current;
                if (comparar(newNode.key,current.key)==1 || comparar(newNode.key,current.key)==0){
                    current = current.right;
                }
                else{
                    current = current.left;
                }
            }
            if (comparar(newNode.key, newNode.parent.key)==-1){
                newNode.parent.left = newNode;
            }
            else{
                newNode.parent.right = newNode;
            }
        }
    }
    /**
     * Función que busca en el árbol binario de búsqueda si contiene la llave especificada
     * @param key llave que se está buscando
     * @return true si encuentra un nodo con la misma llave, false de lo contrario
     */
    public boolean contains(String key){
        return containsAux(root, key);
    }
    /**
     * Función auxiliar de contains
     * @param current el nodo que está revisando
     * @param key llave que se está buscando
     * @return true si encuentra un nodo con la misma llave, false de lo contrario
     */
    private boolean containsAux(Node current, String key){
        if (current == null){
            return false;
        }
        if (comparar(key,current.key)==0){
            return true;
        }
        return (comparar(key,current.key)==-1)? containsAux(current.left, key):
                containsAux(current.right, key);
    }
    /**
     * Elimina el elemento específicado por la llave del árbol
     * @param key llave del elemnto que se quiere eliminar
     */
    public void delete (String key){
        root = deleteAux(root, key);
    }
    /**
     * Función auxiliar de delete, es la que recorre el árbol buscando la llave que se quiere eliminar y la elimina
     * @param current nodo que está revisando
     * @param key llave del nodo que se quiere eliminar
     * @return 
     */
    private Node deleteAux(Node current, String key){
        if (current == null){
            return null;
        }
        if (comparar(key,current.key)==0){
            if (current.left == null && current.right == null){
                return null;
            }
            if (current.right== null){
                return current.left;
            }
            if (current.left == null){
                return current.right;
            }
            String min = findMinAux(current.right);
            current.key = min;
            current.right = deleteAux(current.right, min);
            return current;
                    
        }
        if (comparar(key,current.key)==-1){
            current.left = deleteAux(current.left, key);
            return current;
        }
        current.right = deleteAux(current.right, key);
        return current;
    }
    /**
     * Encuentra la llave con el valor máximo en el árbol
     * @return la llave con el valor máximo
     */
    public String findMax(){
        return findMaxAux(root);
    }
    /** 
     * Función auxiliar de findMax, se le puede indicar por cual nodo empezar a buscar
     * @param root
     * @return el valor de la llave
     */
    private String findMaxAux(Node root){
        return root.right == null? root.key: findMaxAux(root.right);
    }
    /**
     * Encuentra la llave con el valor mínimo en el árbol
     * @return la llave con el valor mínimo
     */
    public String findMin(){
        return findMinAux(root);
    }
    /** 
     * Función auxiliar de findMin, se le puede indicar por cual nodo empezar a buscar
     * @param root
     * @return el valor de la llave
     */
    private String findMinAux(Node root){
        return root.left == null? root.key: findMinAux(root.left);
    }
    
    private int comparar(String word1, String word2){
        Collator espCollator = Collator.getInstance(Locale.getDefault());
        espCollator.setStrength(Collator.SECONDARY);
        return espCollator.compare(word1, word2);
    }
  
    //         __________________
    //________/Getters n' Setters
    public Node getRoot() {
        return root;
    }
    
}
