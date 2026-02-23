package trayectos;

/**
 * Esta clase representa el ritmo al que se camina en el tramo a pie de un trayecto.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Version: 1.0
 * Nombre del fichero: Ritmo.java
 */

public enum Ritmo{
    SUAVE(15),
    MODERADO(10),
    RAPIDO(8);

    private int tiempo;

    /**
     * Constructor de la clase Ritmo, que recibe el tiempo que tarda en recorrer un km a ese ritmo.
     * @param tiempo el tiempo que tarda en recorrer un km a ese ritmo
     */
    private Ritmo(int tiempo){
        this.tiempo = tiempo;
    }

    /**
     * Metodo que devuelve el tiempo que tarda en recorrer un km a ese ritmo.
     * @return el tiempo que tarda en recorrer un km a ese ritmo
     */
    public int getTimeKm(){
        return this.tiempo;
    }

    /** 
     * Metodo que devuelve una cadena con el nombre del ritmo.
     * @return una cadena con el nombre del ritmo
     */
    public String toString(){
        return " (ritmo "+this.name()+")";
    }
}