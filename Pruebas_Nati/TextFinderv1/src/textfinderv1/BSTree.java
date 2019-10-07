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
    public void insert (int key, Object value){
        Node newNode = new Node(key);
        newNode.value = value;
        
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
    public boolean contains(int key){
        return containsAux(root, key);
    }
    
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
    
    public Node getRoot() {
        return root;
    }
    
    
    
    private class Node{
        int key;
        Node parent;
        Node left;
        Node right;
        Object value;
         
        public Node(int key){
            this.key = key;
            this.parent = null;
            this.left = null;
            this.right = null;
            this.value = null;
        }

        
        
    }
}
