/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textfinderv1;

/**
 *
 * @author Nati Gonzalez
 */
public class BSTree {
    
    private Node root;
    
    public BSTree(){
        this.root = null;
    }
    
    /**
     * Recorre en orden el árbol empezando de la raíz
     */
    public void walkInOrder(){
        walkInOrderAux(this.root);        
    }
    /**
     * Función auxiliar de walkInOrder
     * @param x Nodo a partir del que está recorriendo 
     */
    private void walkInOrderAux(Node x){
        if (x!= null){
            walkInOrderAux(x.left);
            System.out.println(x.key);
            walkInOrderAux(x.right);
        
        }
    }
    /**
     * Método para agregar un nuevo elemento al árbol
     * @param key Llave del elemento
     * @param data Datos del nodo
     */
    public void insert (int key, Object data){
        Node newNode = new Node(key);
        newNode.data = data;
        
        if (root == null){
            root = newNode;
        }
        else{
            Node current = root;
            while (current != null){
                newNode.parent = current;
                if (newNode.key >= current.key){
                    current = current.right;
                }
                else{
                    current = current.left;
                }
            }
            if (newNode.key < newNode.parent.key){
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
    public boolean contains(int key){
        return containsAux(root, key);
    }
    /**
     * Función auxiliar de contains
     * @param current el nodo que está revisando
     * @param key llave que se está buscando
     * @return true si encuentra un nodo con la misma llave, false de lo contrario
     */
    private boolean containsAux(Node current, int key){
        if (current == null){
            return false;
        }
        if (key == current.key){
            return true;
        }
        return key < current.key? containsAux(current.left, key):
                containsAux(current.right, key);
    }
    /**
     * Elimina el elemento específicado por la llave del árbol
     * @param key llave del elemnto que se quiere eliminar
     */
    public void delete (int key){
        root = deleteAux(root, key);
    }
    /**
     * Función auxiliar de delete, es la que recorre el árbol buscando la llave que se quiere eliminar y la elimina
     * @param current nodo que está revisando
     * @param key llave del nodo que se quiere eliminar
     * @return 
     */
    private Node deleteAux(Node current, int key){
        if (current == null){
            return null;
        }
        if (key == current.key){
            if (current.left == null && current.right == null){
                return null;
            }
            if (current.right== null){
                return current.left;
            }
            if (current.left == null){
                return current.right;
            }
            int min = findMinAux(current.right);
            current.key = min;
            current.right = deleteAux(current.right, min);
            return current;
                    
        }
        if (key < current.key){
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
    public int findMax(){
        return findMaxAux(root);
    }
    /** 
     * Función auxiliar de findMax, se le puede indicar por cual nodo empezar a buscar
     * @param root
     * @return el valor de la llave
     */
    private int findMaxAux(Node root){
        return root.right == null? root.key: findMaxAux(root.right);
    }
    /**
     * Encuentra la llave con el valor mínimo en el árbol
     * @return la llave con el valor mínimo
     */
    public int findMin(){
        return findMinAux(root);
    }
    /** 
     * Función auxiliar de findMin, se le puede indicar por cual nodo empezar a buscar
     * @param root
     * @return el valor de la llave
     */
    private int findMinAux(Node root){
        return root.left == null? root.key: findMinAux(root.left);
    }
    private class Node{
        int key;
        Node parent;
        Node left;
        Node right;
        Object data;
         
        public Node(int key){
            this.key = key;
            this.parent = null;
            this.left = null;
            this.right = null;
            this.data = null;
        }

        
        
    }
    public Node getRoot() {
        return root;
    }
}
