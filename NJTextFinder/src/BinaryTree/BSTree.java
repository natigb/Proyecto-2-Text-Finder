package BinaryTree;

import java.text.Collator;
import java.util.Locale;

/**
 *
 * @author Nati Gonzalez
 */
public class BSTree {
    private Node root;
    
    public BSTree(){
        this.root = null;
    }
    
    //         __________________
    //________/Getters n' Setters
    public Node getRoot() {
        return root;
    }
    
    /**
     * Recorre en orden el �rbol empezando de la ra�z
     */
    public void traverseInOrder(){
        traverseInOrderAux(this.root);        
    }
    /**
     * Funci�n auxiliar de walkInOrder
     * @param x Nodo a partir del que est� recorriendo 
     */
    private void traverseInOrderAux(Node x){
        if (x!= null){
            traverseInOrderAux(x.left);
            System.out.println(x.key);
           traverseInOrderAux(x.right);
        
        }
    }
    public void insert(String key, Object data){
        if (!contains(key)){
            insertAux(key,data);
        }
        else{System.out.println("La llave ya existe");}
    }
    /**
     * M�todo para agregar un nuevo elemento al �rbol
     * @param key Llave del elemento
     * @param data Datos del nodo
     */
    public void insertAux (String key, Object data){
        Node newNode = new Node(key);
        newNode.data = data;
        
        if (root == null){
            root = newNode;
        }
        else{
            Node current = root;
            while (current != null){
                newNode.parent = current;
                if (comparar(newNode.key,current.key)==1 || comparar(newNode.key,current.key)==0){
                    current = current.right;
                }
                else{
                    current = current.left;
                }
            }
            if (comparar(newNode.key, newNode.parent.key)==-1){
                newNode.parent.left = newNode;
            }
            else{
                newNode.parent.right = newNode;
            }
        }
    }
    /**
     * Funci�n que busca en el �rbol binario de b�squeda si contiene la llave especificada
     * @param key llave que se est� buscando
     * @return true si encuentra un nodo con la misma llave, false de lo contrario
     */
    public boolean contains(String key){
        return containsAux(root, key);
    }
    /**
     * Funci�n auxiliar de contains
     * @param current el nodo que est� revisando
     * @param key llave que se est� buscando
     * @return true si encuentra un nodo con la misma llave, false de lo contrario
     */
    private boolean containsAux(Node current, String key){
        if (current == null){
            return false;
        }
        if (comparar(key,current.key)==0){
            return true;
        }
        return (comparar(key,current.key)==-1)? containsAux(current.left, key):
                containsAux(current.right, key);
    }
    /**
     * Elimina el elemento espec�ficado por la llave del �rbol
     * @param key llave del elemnto que se quiere eliminar
     */
    public void delete (String key){
        root = deleteAux(root, key);
    }
    /**
     * Funci�n auxiliar de delete, es la que recorre el �rbol buscando la llave que se quiere eliminar y la elimina
     * @param current nodo que est� revisando
     * @param key llave del nodo que se quiere eliminar
     * @return 
     */
    private Node deleteAux(Node current, String key){
        if (current == null){
            return null;
        }
        if (comparar(key,current.key)==0){
            if (current.left == null && current.right == null){
                return null;
            }
            if (current.right== null){
                return current.left;
            }
            if (current.left == null){
                return current.right;
            }
            String min = findMinAux(current.right);
            current.key = min;
            current.right = deleteAux(current.right, min);
            return current;
                    
        }
        if (comparar(key,current.key)==-1){
            current.left = deleteAux(current.left, key);
            return current;
        }
        current.right = deleteAux(current.right, key);
        return current;
    }
    /**
     * Encuentra la llave con el valor m�ximo en el �rbol
     * @return la llave con el valor m�ximo
     */
    public String findMax(){
        return findMaxAux(root);
    }
    /** 
     * Funci�n auxiliar de findMax, se le puede indicar por cual nodo empezar a buscar
     * @param root
     * @return el valor de la llave
     */
    private String findMaxAux(Node root){
        return root.right == null? root.key: findMaxAux(root.right);
    }
    /**
     * Encuentra la llave con el valor m�nimo en el �rbol
     * @return la llave con el valor m�nimo
     */
    public String findMin(){
        return findMinAux(root);
    }
    /** 
     * Funci�n auxiliar de findMin, se le puede indicar por cual nodo empezar a buscar
     * @param root
     * @return el valor de la llave
     */
    private String findMinAux(Node root){
        return root.left == null? root.key: findMinAux(root.left);
    }
    
    private int comparar(String word1, String word2){
        Collator espCollator = Collator.getInstance(Locale.getDefault());
        espCollator.setStrength(Collator.SECONDARY);
        return espCollator.compare(word1, word2);
    }
    
}
