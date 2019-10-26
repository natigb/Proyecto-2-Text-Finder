/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BinaryTree;

import LinkedList.LinkedList;
import Logic.DocumentIndex;

/**
 * Clase Node ligada a la clase BSTree, el árbol binario de búsqueda, guarda la llave del nodo, la lista de
 * documentos asociados a esa llave y referencias al nodo padre, hijo derecho e hijo izquierdo.
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
        /**
         * Añade un Documento indexado a la lista de documentos del nodo
         * @param doc 
         */
        public void add(DocumentIndex doc){
            documents.insertLast(doc);
        }
        /**
         * Busca si un documento ya existe en la lista de documentos
         * @param name Nombre del documento que se está buscando
         * @return true si el documento exite, false de lo contrario
         */
        public Boolean docExists(String name){
            for(int i = 0; i < documents.getSize(); i++){
                if (name == documents.serchByIndex(i).getData().getDoc().getName()){
                    return true;
                }
            }
            return false;
        }
        /**
         * Busca el documento indexado en la lista de documentos según el nombre
         * @param name nombre del documento que se quiere encontró
         * @return el documento si lo encuentra, null si no lo encuentra
         */
        public DocumentIndex searchDocByName(String name){
            for(int i = 0; i < documents.getSize(); i++){
                if (name == documents.serchByIndex(i).getData().getDoc().getName()){
                    return documents.serchByIndex(i).getData();
                }
            }
            return null;
        
        }
        
    }
