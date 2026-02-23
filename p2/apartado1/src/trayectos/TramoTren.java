package trayectos;

/**
 * Esta clase representa el tramo a tren de un trayecto, que tiene una linea de tren y un numero de paradas. Es una subclase de TramoTrayecto.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Version: 1.0
 * Nombre del fichero: Biblioteca.java
 */

public class TramoTren extends TramoTrayecto {

    private Linea linea;
    private int numParadas;

    /** 
     * Constructor de la clase TramoTren, que recibe el origen, destino, linea y numero de paradas.
     * @param origen el origen del tramo
     * @param destino el destino del tramo
     * @param linea la linea del tramo
     * @param numParadas el numero de paradas del tramo
     */
    public TramoTren (String origen, String  destino, Linea linea, int numParadas){
        super(origen, destino);
        this.linea = linea;
        this.numParadas = numParadas;
    }

    /** 
     * Metodo que devuelve el tiempo que tarda en recorrer el tramo en tren.
     * @return el tiempo que tarda en recorrer el tramo en tren
     */
    @Override
    public double tiempo(){
        return this.numParadas*this.linea.getStopTime();
    }

    /** 
     * Metodo que devuelve una cadena con la representacion del tramo en tren.
     * @return una cadena con la representacion del tramo en tren
     */
    @Override
    public String toString(){
        return "En tren de la línea "+this.linea+" "+super.toString();
    }












}