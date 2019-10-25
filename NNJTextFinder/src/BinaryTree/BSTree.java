package BinaryTree;

import LinkedList.LinkedList;
import Logic.Document;
import Logic.DocumentIndex;
import java.text.Collator;
import java.util.Locale;

/**
 *  Clase del ·rbol binario de busqueda utilizado para guardar documentos seg˙n las palabras y poder buscar
 *  en ellos de forma eficiente.
 * @author Natalia Gonzalez
 */
public class BSTree {
    
    private Node root;

    public BSTree() {
        this.root = null;
    }

    

    /**
     * Recorre en orden el arbol empezando de la raÌz
     */
    public void traverseInOrder() {
        traverseInOrderAux(this.root);
    }

    /**
     * Funcion auxiliar de walkInOrder
     *
     * @param x Nodo a partir del que esta recorriendo
     */
    private void traverseInOrderAux(Node x) {

        if (x != null) {
            traverseInOrderAux(x.left);
            System.out.println(x.key);
            traverseInOrderAux(x.right);

        }
    }

    /**
     * Encuentra un nodo en el ·rbol a partir de su llave 
     * @param key La llave del nodo que se quiere buscar
     * @return El nodo que tiene la llave que se busca
     */
    public Node find(String key) {
        
        return findAux(this.root, key);
        
    }

    /**
     * MÈtodo auxiliar de find
     * @param x Nodo a partir del que esta recorriendo
     * @param key Llave del nodo que se quiere encontrar
     */
    private Node findAux(Node x, String key) {
        if (x == null) {
            return null;
        }
        if (comparar(x.key, key) == 1) {
            return findAux(x.left, key);
        } else if (comparar(x.key, key) == -1) {
            return findAux(x.right, key);
        } else {
            return x;
        }

    }
    /**
     * Retorna la lista una lista de documentos que contienen la palabra ingresada
     * @param key Llave a partir de la que se quieren buscar documentos
     * @return Lista de documentos que contienen esa llave
     */
    public LinkedList<DocumentIndex> getListOfDocs(String key) {
        if (contains(key)) {
            return find(key).documents;
        } else {
            return null;
        }
    }

    /**
     * Inserta un documento al ·rbol, la llave es una palabra y guarda el documento y la posiciÛn de esa palabra
     * en el documento en el nodo
     *
     * @param key palabra
     * @param doc documento guardado
     * @param position posiciÛn de la palabra en el documento
     */
    public void insert(String key, DocumentIndex doc, int position) {
        if (!contains(key)) {
            insertAux(key, doc);
            doc.addPosition(position);
        } else {
            if (!(find(key).docExists(doc.getDoc().getName()))) {
                find(key).add(doc);
                doc.addPosition(position);
            } else {
                find(key).searchDocByName(doc.getDoc().getName()).addPosition(position);
            }

            System.out.println("La llave ya existe");
        }
    }

    /**
     * MÈtodo auxiliar para agregar un nuevo elemento al arbol 
     *
     * @param key Llave del elemento a agregar
     * @param doc Documento con las posiciones en las que se encuentran las palabras
     *
     */
    private void insertAux(String key, DocumentIndex doc) {
        Node newNode = new Node(key);
        newNode.add(doc);

        if (root == null) {
            root = newNode;
        } else {
            Node current = root;
            while (current != null) {
                newNode.parent = current;
                if (comparar(newNode.key, current.key) == 1 || comparar(newNode.key, current.key) == 0) {
                    current = current.right;
                } else {
                    current = current.left;
                }
            }
            if (comparar(newNode.key, newNode.parent.key) == -1) {
                newNode.parent.left = newNode;
            } else {
                newNode.parent.right = newNode;
            }
        }
    }

    /**
     * Funci√≥n que busca en el √°rbol binario de b√∫squeda si contiene la llave
     * especificada
     *
     * @param key llave que se est√° buscando
     * @return true si encuentra un nodo con la misma llave, false de lo
     * contrario
     */
    public boolean contains(String key) {
        return containsAux(root, key);
    }

    /**
     * Funci√≥n auxiliar de contains
     *
     * @param current el nodo que est√° revisando
     * @param key llave que se est√° buscando
     * @return true si encuentra un nodo con la misma llave, false de lo
     * contrario
     */
    private boolean containsAux(Node current, String key) {
        if (current == null) {
            return false;
        }
        if (comparar(key, current.key) == 0) {
            return true;
        }
        return (comparar(key, current.key) == -1) ? containsAux(current.left, key)
                : containsAux(current.right, key);
    }
    /**
     * Elimina un documento de las listas de los nodos en que se encuentra.
     * @param doc Documento que se quiere eliminar
     */
    public void deleteDoc(Document doc){
        for (int i=0; i<doc.getContent().length;i++){
            LinkedList<DocumentIndex> docInList = find(doc.getContent()[i]).documents;

            
            for(int j=0;j<docInList.getSize();j++){
                if (docInList.serchByIndex(j).getData().getDoc().getName().equals(doc.getName())){
                    docInList.deleteByIndex(j);
                    break;
                }
                if (0 == docInList.getSize()){
                  delete(doc.getContent()[i]);
                  break;
                } 
            }
             
        }
    }
    /**
     * Elimina el elemento espec√≠ficado por la llave del √°rbol
     *
     * @param key llave del elemnto que se quiere eliminar
     */
    public void delete(String key) {
        if (contains(key)){
            root = deleteAux(root, key);
        }
    }

    /**
     * Funci√≥n auxiliar de delete, es la que recorre el √°rbol buscando la
     * llave que se quiere eliminar y la elimina
     *
     * @param current nodo que est√° revisando
     * @param key llave del nodo que se quiere eliminar
     * @return
     */
    private Node deleteAux(Node current, String key) {
        if (current == null) {
            return null;
        }
        if (comparar(key, current.key) == 0) {
            if (current.left == null && current.right == null) {
                return null;
            }
            if (current.right == null) {
                return current.left;
            }
            if (current.left == null) {
                return current.right;
            }
            String min = findMinAux(current.right);
            current.key = min;
            current.right = deleteAux(current.right, min);
            return current;

        }
        if (comparar(key, current.key) == -1) {
            current.left = deleteAux(current.left, key);
            return current;
        }
        current.right = deleteAux(current.right, key);
        return current;
    }
    /**
     * Retorna una lista con las posiciones de una palabra especÌfica en un documento especÌfico
     * @param doc Documento en el que se quieren buscar las palabras
     * @param word Palabra que se quiere buscar en el documento
     * @return Lista enlazada con las posiciones de la palabra en el documento
     */
    public LinkedList getPositions(Document doc, String word) {
        if (contains(word)) {
            if (find(word).searchDocByName(doc.getName()) != null) {
                return find(word).searchDocByName(doc.getName()).getPosition();
            }
        }
        return new LinkedList();

    }

    /**
     * Encuentra la llave con el valor m·ximo en el ·rbol
     *
     * @return la llave con el valor m·ximo
     */
    public String findMax() {
        return findMaxAux(root);
    }

    /**
     * Funci√≥n auxiliar de findMax, se le puede indicar por cual nodo empezar a
     * buscar
     *
     * @param root
     * @return el valor de la llave
     */
    private String findMaxAux(Node root) {
        return root.right == null ? root.key : findMaxAux(root.right);
    }

    /**
     * Encuentra la llave con el valor m√≠nimo en el √°rbol
     *
     * @return la llave con el valor m√≠nimo
     */
    public String findMin() {
        return findMinAux(root);
    }

    /**
     * Funci√≥n auxiliar de findMin, se le puede indicar por cual nodo empezar a
     * buscar
     *
     * @param root
     * @return el valor de la llave
     */
    private String findMinAux(Node root) {
        return root.left == null ? root.key : findMinAux(root.left);
    }

    /**
     * FunciÛn para comparar 2 strings
     *
     * @param word1
     * @param word2
     * @return 0 si son iguales, 1 si word 1 es mayor a word2 y de lo contrario -1
     */
    public static int comparar(String word1, String word2) {
        Collator espCollator = Collator.getInstance(Locale.getDefault());
        espCollator.setStrength(Collator.PRIMARY);
        return espCollator.compare(word1, word2);
    }
    //         __________________
    //________/Getters n' Setters
    public Node getRoot() {
        return root;
    }
    
    
}
