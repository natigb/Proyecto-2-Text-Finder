package Logic;

/**
 *
 * @author Nati Gonzalez
 */
public class Documento {
    String[] contenido;
    String nombre;
    String fecha;
    int tamaño;

    public Documento(String contenido, String nombre, String fecha, int tamaño) {
        this.contenido = contenido.split(" ");
        this.nombre = nombre;
        this.fecha = fecha;
        this.tamaño = tamaño;
    }
    
}
