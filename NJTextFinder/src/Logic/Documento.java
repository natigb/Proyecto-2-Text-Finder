package Logic;

/**
 *
 * @author Nati Gonzalez
 */
public class Documento {
    String[] contenido;
    String nombre;
    String fecha;
    int tama�o;

    public Documento(String contenido, String nombre, String fecha, int tama�o) {
        this.contenido = contenido.split(" ");
        this.nombre = nombre;
        this.fecha = fecha;
        this.tama�o = tama�o;
    }
    
}
