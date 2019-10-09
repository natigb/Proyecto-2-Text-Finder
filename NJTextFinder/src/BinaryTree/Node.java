/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BinaryTree;

/**
 *
 * @author Jose
 */
class Node {
      
        String key;
        Node parent;
        Node left;
        Node right;
        Object data;
         
        public Node(String key){
            this.key = key;
            this.parent = null;
            this.left = null;
            this.right = null;
            this.data = null;
        }
}
