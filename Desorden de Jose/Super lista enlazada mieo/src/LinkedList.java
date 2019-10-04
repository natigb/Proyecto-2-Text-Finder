public class LinkedList {

    private Node head;
    private int size;

    public LinkedList(){
        this.head= null;
        this.size = 0;
    }

    /**
     * Metodo que se encarga de insertar un nodo con un dato al inicio de la lista enlazada.
     *
     * @param data Dato tipo Object que se desea que el nuevo nodo contenga.
     */

    public void insertFirst(Object data){
        Node newNode = new Node(data);
        newNode.setNext(this.head);
        this.head = newNode;
        this.size++;
    }

    /**
     * Función qe crea un nodo a partir de los datos que se ingresen y lo pone al inicio de la lista
     * @param data Dato tipo Object que se desea que el nuevo nodo contenga.
     */

    public void insertLast(Object data){
        if (head==null){
            head = new Node(data);
        }
        else{
            Node current = head;
            Node newNode = new Node(data);
            while (current.getNext()!= null){
                current= current.getNext();
            }
            current.setNext(newNode);
        }
        this.size++;

    }

    /**
     * Metodo que se encarga de eliminar el nodo al inicio de la lista enlazada.
     */
    public void deleteFirst(){
        if (this.head != null){
            this.head = this.head.getNext();
            this.size--;
        }
    }

    /**
     * Función que permite buscar un nodo de la lista por el ídice en el que se encuentra
     * @param index Indice de la lista que se quiere buscar
     * @return datos que contiene el nodo que se encuetra en ese índice. Null si la lista está vacía.
     */
    public Object serchByIndex(int index) {
        if (head != null) {
            Node current = this.head;
            for (int x = 0; x < index; x++) {
                current = current.getNext();
            }
            return current.getData();
        }
        return null;
    }

    /**
     * Metodo que se encarga de cambiar el dato del nodo en una posicion X de la lista enlazada.
     *
     * @param index Indice de la lista donde se quiere insetar el dato. (Empezando en 0).
     * @param data Dato tipo Object que se desea que el nuevo nodo contenga.
     */
    public void setByIndex(int index,Object data){
        Node current= this.head;
        for (int x = 0; x < index ; x++ ){
            current = current.getNext();
        }
        current.setData(data);

    }

    /**
     * Metodo que se encarga de eliminar el nodo con un dato especifico en la primer posicion en que lo encuentre en lista enlazada.
     *
     * @param data Dato tipo Object que se desea eliminar en una posicion desconocida.
     */
    public void eliminarX(Object data){
        //Primero valida para por si es una lista de 2 nodos
        if (head != null){
            if (size>1) {
                if (head.getData() == data) {
                    deleteFirst();
                } else {
                    if (head.getNext().getNext() != null){
                        Node current = this.head;
                        while (current.getNext().getNext() != null) {
                            if (current.getNext().getData() == data) {
                                current.setNext(current.getNext().getNext());
                                size--;
                                break;
                            }
                            current = current.getNext();
                        }
                        if (current.getNext().getData() == data) {
                            current.setNext(current.getNext().getNext());
                            size--;
                        }
                    }else{
                        if (head.getNext().getData() == data){
                            head.setNext(null);
                            size = 1;
                        }
                    }
                }
            }else{
                if (head.getData() == data){
                    head = null;
                    size = 0;
                }
            }
        }
    }
}
