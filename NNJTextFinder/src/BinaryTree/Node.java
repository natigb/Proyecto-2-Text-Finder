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
        public Boolean docExists(String name){
            for(int i = 0; i < documents.getSize(); i++){
                if (name == documents.serchByIndex(i).getData().getDoc().getName()){
                    return true;
                }
            }
            return false;
        }
        public DocumentIndex searchDocByName(String name){
            for(int i = 0; i < documents.getSize(); i++){
                if (name == documents.serchByIndex(i).getData().getDoc().getName()){
                    return documents.serchByIndex(i).getData();
                }
            }
            return null;
        
        }
        
    }
