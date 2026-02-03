/**
 * Esta clase contiene la información de un libro así como su comportamiento en una biblioteca
 * @author: Adrián Gómez y Javier Agüero. adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es
 * @version: 1.0
 * Nombre del fichero: Libro.java
 */
public class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private int ejemplaresDisponibles;
    private String genero;
    private int anyoPublicacion;

    /**
     * Constructor de la clase Libro
     * Contiene la información de Libro
     * @param isbn String del isbn de un libro
     * @param titulo String del titulo de un libro
     * @param autor String del autor de un libro
     * @param ejemplaresDisponibles int del número de ejemplares disponibles de un libro
     */
    public Libro(String isbn, String titulo, String autor, int ejemplaresDisponobles) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.ejemplaresDisponibles = ejemplaresDisponobles;
    }

    /**
     * Constructor de la clase Libro
     * Contiene la información de Libro con las nuevas modificaciones
     * @param isbn String del isbn de un libro
     * @param titulo String del titulo de un libro
     * @param autor String del autor de un libro
     * @param ejemplaresDisponibles int del número de ejemplares disponibles de un libro
     * @param generi String del genero de un libro
     * @param anyoPublicacion int del año de publicación de un libro
     */
    public Libro(String isbn, String titulo, String autor, int ejemplaresDisponobles, String genero, int anyoPublicacion) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.ejemplaresDisponibles = ejemplaresDisponobles;
        this.genero = genero;
        this.anyoPublicacion = anyoPublicacion;
    }

    /**
     * Método para verificar si el libro está disponible
     */
    public boolean estaDisponible() {
        return this.ejemplaresDisponibles > 0;
    }

    /**
     * Método para prestar el libro
     */
    public boolean prestar() {
        if (estaDisponible()) {
            this.ejemplaresDisponibles--;
            return true;
        }
        return false;
    }

    /**
     * Método para devolver el libro
     */
    public void devolver() {
        this.ejemplaresDisponibles++;
    }

    /**
     * Método para obtener la descripción del libro
     */
    private String descripcion() {
        String estado = this.estaDisponible() ? "Disponible" : "No disponible";
        return "'"+this.titulo + "' de " + this.autor + " [" + estado + "[";
    }

    /**
     * Método para obtener el género del libro
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Método para obtener el año de publicación del libro
     */
    public int getAnyoPublicacion() {
        return anyoPublicacion;
    }

    /**
     * Método para imprimir la información del libro
     */
    @Override
    public String toString(){
        String ret = "ISBN: ";
        ret += this.isbn + ". " + this.descripcion() + " ("+this.ejemplaresDisponibles+ " ejemplares disponibles)";
        if (this.genero != null){
            ret += this.genero;
        }
        if (this.anyoPublicacion != 0){
            ret += this.anyoPublicacion;
        }
        return ret;
    }
}
