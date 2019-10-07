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
    
    Node root;
    
    public BSTree(){
        this.root = null;
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
