package Logic;

import BinaryTree.BSTree;
import LinkedList.LinkedList;
import LinkedList.Node;


/**
 *
 * @author Natalia and Jose
 */
public class Library {
   LinkedList<Document> library = new LinkedList<Document>();
   BSTree tree = new BSTree();
   
   public LinkedList<DocumentIndex> listOfDocs(String key){
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
    public void add(Document doc){
        //if (library == null){
          //  library = new LinkedList<Document>();
        //}
        
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
       checkForEquals(doc.getName());
       
       //System.out.println("__________");
       
      // System.out.println(tree.search("txt"));
      // System.out.println(tree.search("txt"));
              
   }

    private void checkForEquals(String docName) {
        if (library.getSize() != 1){
        Node<Document> currentNode = this.library.getHead().getNext();
        while(currentNode.getNext()!= null){
            if (currentNode.getData().getName().equals(docName)){
                library.deleteByData(currentNode.getData());
                currentNode = null;
                library.printList();
                System.out.print("Archivo repetido eliminado de libreria virtual");
                break;
            }else{
                currentNode = currentNode.getNext();
            }
        }
        if (currentNode != null){
            if (currentNode.getData().getName().equals(docName)){
                library.deleteByData(currentNode.getData());
                library.printList();
                System.out.print("Archivo repetido eliminado de libreria virtual");
                }
            }    
        }
    }
   
   
}
