package Logic;

import BinaryTree.BSTree;
import LinkedList.LinkedList;


/**
 *
 * @author
 */
public class Library {
   LinkedList<Document> library;
   BSTree tree = new BSTree();
   
   public void add(Document doc){
       DocumentIndex docIndex = new DocumentIndex(doc);
       for (int i=0; i<docIndex.getDoc().getContent().length;i++){
           tree.insert(docIndex.getDoc().getContent()[i], docIndex);
       }
       
       //System.out.println("__________");
       
      // System.out.println(tree.search("txt"));
      // System.out.println(tree.search("txt"));
              
   }
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
   
   
}
