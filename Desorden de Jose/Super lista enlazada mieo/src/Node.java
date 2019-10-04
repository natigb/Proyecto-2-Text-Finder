public class Node {

    private Object data;
    private Node next;

    /**
     * Constructor de la clase.
     */
    public Node(Object data) {
        this.next = null;
        this.data = data;
    }

    //         ____________________
    //________/Getters and Setters
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getNext() {
        return this.next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

}