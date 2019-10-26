package Logic;

import BinaryTree.BSTree;
import LinkedList.LinkedList;
import LinkedList.Node;

/**
 * Clase que se encarga de la administracion de documentos y control del arbol binario de busqueda
 * @author Natalia and Jose
 */
public class Library {
   private LinkedList<Document> library = new LinkedList<>();
   private BSTree tree = new BSTree();
   
   /**
    * Busca en el arbol los documentos que contienen una palabra
    * @param key 
    * @return lista de documentos que contienen la palabra específica
    */
   public LinkedList<Document> listOfDocs(String key){
       LinkedList<DocumentIndex> docIndxList = tree.getListOfDocs(key);
       LinkedList<Document> docList = new LinkedList<>();
       if (docIndxList == null || docIndxList.getHead() == null){
        return null;   
       }
       Node<DocumentIndex> currentNode = docIndxList.getHead();
       while (currentNode.getNext() != null){
           docList.insertFirst((currentNode.getData()).getDoc());
           currentNode = currentNode.getNext();
       }
       docList.insertFirst((currentNode.getData()).getDoc());
       return docList;    
   }
   
    /**
     * Busca en el arbol los documentos y las posiciones de una palabra en ese documento
     * @param key palabra que se quiere buscar
     * @return lista de documentos indexados que contiene el nodo con esa llave 
     */
   public LinkedList<DocumentIndex> listOfIndxDocs(String key){
       return tree.getListOfDocs(key);
   }
   /**
    * Función para imprimir el árbol 
    */
   public void printTree(){
       tree.traverseInOrder();
   }
    
    /**
     * Busca en el árbol las posiciones de una palbra en un documento específico
     * @param doc 
     * @param word
     * @return lista con las posiciones de la palabra en el documento especificado
     */
    public LinkedList listOfPositions(Document doc, String word){
        return tree.getPositions(doc, word);
    }
    
    /**
     * Añade en documento a la lista de documentos y al árbol binario de búsqueda
     * @param doc
     * @return 
     */
    public boolean add(Document doc){
       if (doc.getText() != null){ 
           
       for (int i=0; i<doc.getContent().length;i++){
           DocumentIndex docIndex = new DocumentIndex(doc);
           tree.insert(docIndex.getDoc().getContent()[i], docIndex,i);
        }
        library.insertFirst(doc);
       }
       else{
           System.out.print("Document is invalid or blank");
       }
       return checkForEquals(doc.getName());
       
              
    }
    
    /**
     * REcorre la lista de documentos en búsqueda de uno igual
     * @param docName
     * @return true si se repite, false de lo contrario
     */
    private boolean checkForEquals(String docName) {
        if (library.getSize() != 1){
        Node<Document> currentNode = this.library.getHead().getNext();
        while(currentNode.getNext()!= null){
            if (currentNode.getData().getName().equals(docName)){
                library.deleteByData(currentNode.getData());
                currentNode = null;
                library.printList();
                System.out.print("Archivo repetido eliminado de libreria virtual");
                return true;
            }else{
                currentNode = currentNode.getNext();
            }
        }
        if (currentNode != null){
            if (currentNode.getData().getName().equals(docName)){
                library.deleteByData(currentNode.getData());
                library.printList();
                System.out.print("Archivo repetido eliminado de libreria virtual");
                return true;
                }
            }    
        }
        return false;
    }
    
    /**
     * Elimina todo rastro del documento del ABB y de la lista de documentos que contiene la biblioteca
     * @param doc 
     */
    public void deleteDoc(Document doc){
        library.deleteByData(doc);
        tree.deleteDoc(doc);
    }
    
    
    //         __________________
    //________/Getters n' Setters
    public LinkedList<Document> getLibrary() {
        return library;
    }

    public void setLibrary(LinkedList<Document> library) {
        this.library = library;
    }

    public BSTree getTree() {
        return tree;
    }

    public void setTree(BSTree tree) {
        this.tree = tree;
    }
}
