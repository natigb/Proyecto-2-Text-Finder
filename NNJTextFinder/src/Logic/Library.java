package Logic;

import BinaryTree.BSTree;
import LinkedList.LinkedList;
import LinkedList.Node;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 *
 * @author Natalia and Jose
 */
public class Library {
   LinkedList<Document> library = new LinkedList<>();
   BSTree tree = new BSTree();
   
   public LinkedList<Document> listOfDocs(String key){
       LinkedList<DocumentIndex> docIndxList = tree.getListOfDocs(key);
       LinkedList<Document> docList = new LinkedList<>();
       if (docIndxList == null){
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
     *
     * @param key
     * @return
     */
   public LinkedList<DocumentIndex> listOfIndxDocs(String key){
       return tree.getListOfDocs(key);
   }
   
   public void printTree(){
       tree.traverseInOrder();
   }

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
    
    public LinkedList listOfPositions(Document doc, String word){
        return tree.getPositions(doc, word);
    }
    
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
    
}
