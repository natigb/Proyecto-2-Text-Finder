/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BinaryTree;

import LinkedList.LinkedList;
import Logic.Document;
import Logic.DocumentIndex;

/**
 *
 * @author Jose
 */
 class Node{
        String key;
        Node parent;
        Node left;
        Node right;
        LinkedList<DocumentIndex> documents;
         
        public Node(String key){
            this.key = key;
            this.parent = null;
            this.left = null;
            this.right = null;
            this.documents = new LinkedList();
        }

        public void add(DocumentIndex doc){
            documents.insertLast(doc);
        }
        
    }