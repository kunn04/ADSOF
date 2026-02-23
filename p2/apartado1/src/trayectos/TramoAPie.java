package trayectos;

/**
 * Esta clase representa el tramo a pie de un trayecto, que se caracteriza por el ritmo al que se camina y el numero de km que se recorren.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Version: 1.0
 * Nombre del fichero: Biblioteca.java
 */

public class TramoAPie extends TramoTrayecto{
    private Ritmo ritmo;
    private double numKm;

    /** 
     * Constructor de la clase TramoAPie, que recibe el origen, destino, numero de km y ritmo.
     * @param origen el origen del tramo
     * @param destino el destino del tramo
     * @param numKm el numero de km del tramo
     * @param ritmo el ritmo al que se camina en el tramo
     */
    public TramoAPie(String origen, String destino, double numKm, Ritmo ritmo){
        super(origen, destino);
        this.numKm = numKm;
        this.ritmo = ritmo;
    }

    /** 
     * Constructor de la clase TramoAPie, que recibe el origen, destino y numero de km, y asigna el ritmo MODERADO por defecto.
     * @param origen el origen del tramo
     * @param destino el destino del tramo
     * @param numKm el numero de km del tramo
     */
    public TramoAPie(String origen, String destino, double numKm){
        super(origen, destino);
        this.ritmo = ritmo.MODERADO;
        this.numKm = numKm;
    }

    /** 
     * Metodo que devuelve el tiempo que tarda en recorrer el tramo a pie.
     * @return el tiempo que tarda en recorrer el tramo a pie
     */
    @Override
    public double tiempo(){
        return this.numKm * this.ritmo.getTimeKm();
    }

    /** 
     * Metodo que devuelve una cadena con la representacion del tramo a pie.
     * @return una cadena con la representacion del tramo a pie
     */
    @Override
    public String toString(){
        return "A pie "+super.toString()+this.ritmo;
    }

}