package trayectos;

/**
 * Esta clase representa el tramo de un trayecto, que puede ser a pie o en tren. Es una clase abstracta que tiene dos subclases: TramoAPie y TramoTren.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Version: 1.0
 * Nombre del fichero: Biblioteca.java
 */

public abstract class TramoTrayecto {
    private String origen;
    private String destino;

    /** 
     * Constructor de la clase TramoTrayecto, que recibe el origen y destino del tramo.
     * @param origen el origen del tramo
     * @param destino el destino del tramo
     */
    public TramoTrayecto(String origen, String destino) {
        this.origen = origen;
        this.destino = destino;
    }

    /** 
     * Metodo que devuelve el tiempo que tarda en recorrer el tramo.
     * @return el tiempo que tarda en recorrer el tramo
     */
    public double tiempo() {
        return 0.0;
    }

    /** 
     * Metodo que devuelve una cadena con la representacion del tramo.
     * @return una cadena con la representacion del tramo
     */
    public String toString() {
        return "desde "+this.origen+" a "+this.destino+": "+this.tiempo()+" minutos";
    }
}