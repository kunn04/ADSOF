package trayectos;

/**
 * Esta clase representa la linea de tren que se utiliza en el tramo de tren de un trayecto.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Version: 1.0
 * Nombre del fichero: Linea.java
 */


public enum Linea{
    C1("azul claro", 5),
    C4("azul oscuro", 10),
    C5("amarilla", 30);

    private String color;
    private int stopTime;

    /**
     * Constructor de la clase Linea, que recibe el color de la linea y el tiempo que tarda en cada parada.
     * @param color el color de la linea
     * @param stopTime el tiempo que tarda en cada parada
     */
    private Linea(String color, int stopTime){
        this.color = color;
        this.stopTime = stopTime;
    }

    /**
     * Metodo que devuelve el tiempo que tarda en cada parada de la linea.
      * @return el tiempo que tarda en cada parada de la linea
     */
    public int getStopTime(){
        return this.stopTime;
    }

    /**
     * Metodo que devuelve una cadena con el nombre de la linea y su color.
     * @return una cadena con el nombre de la linea y su color
     */
    public String toString(){
        return this.name()+" ("+this.color+")";
    }
}