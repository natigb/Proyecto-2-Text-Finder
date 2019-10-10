package Logic;

import BinaryTree.BSTree;
import LinkedList.LinkedList;


/**
 *
 * @author Jose
 */
public class Library {
   LinkedList<Document> library;
   BSTree tree = new BSTree();
   
   public void add(Document doc){
       for (int i=0; i<doc.getContent().length;i++){
           tree.insert(doc.getContent()[i], doc);
       }
       tree.traverseInOrder();
       System.out.println("__________");
       
       System.out.println(tree.search("txt"));
       System.out.println(tree.search("txt"));
              
   }
   
}
