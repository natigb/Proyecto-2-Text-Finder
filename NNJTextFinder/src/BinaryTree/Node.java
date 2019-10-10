/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BinaryTree;

import LinkedList.LinkedList;
import Logic.Document;

/**
 *
 * @author Jose
 */
 class Node{
        String key;
        Node parent;
        Node left;
        Node right;
        LinkedList<Document> documents;
         
        public Node(String key){
            this.key = key;
            this.parent = null;
            this.left = null;
            this.right = null;
            this.documents = new LinkedList();
        }

        public void add(Document doc){
            documents.insertLast(doc);
        }
        
    }
