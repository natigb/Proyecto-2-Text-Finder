/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textfinderv1;

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
