package src;

/**
 * Esta clase representa un enlace entre dos usuarios en una red social, con un coste asociado. El coste puede ser modificado y se mantiene una suma total de los costes de todos los enlaces creados.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Version: 1.0
 * Nombre del fichero: Enlace.java
 */
public class Enlace {
    private Usuario origen;
    private Usuario destino;
    private int coste;

    private static int suma_costes = 0;

    /**
     * Constructor de la clase Enlace.
     * @param origen El usuario que origina el enlace.
     * @param destino El usuario al que apunta el enlace.
     * @param coste El coste del enlace.
     */
    public Enlace (Usuario origen, Usuario destino, int coste) {
        this.origen = origen;
        this.destino = destino;
        if(coste <= 0) {
            this.coste = 1;
        }
        else {
            this.coste = coste;
        }
        suma_costes += this.coste;
    }

    /**
     * Constructor de la clase Enlace.
     * @param origen El usuario que origina el enlace.
     * @param destino El usuario al que apunta el enlace.
     */
    public Enlace (Usuario origen, Usuario destino) {
        this(origen, destino, 1);
    }

    /**
     * Devuelve el usuario que origina el enlace.
     * @return El usuario que origina el enlace.
     */
    public Usuario getOrigen() {
        return origen;
    }

    /**
     * Devuelve el usuario al que apunta el enlace.
     * @return El usuario al que apunta el enlace.
     */
    public Usuario getDestino() {
        return destino;
    }

    /**
     * Devuelve el coste del enlace.
     * @return El coste del enlace.
     */
    public int getCoste() {
        return coste;
    }

    /**
     * Cambia el destino del enlace y actualiza el coste.
     * @param new_destino El nuevo usuario al que apunta el enlace.
     * @param new_coste El nuevo coste del enlace.
     */
    public void cambiarDestino(Usuario new_destino, int new_coste) {
        this.destino = new_destino;

        suma_costes -= this.coste;
        
        if(new_coste <= 0){
            this.coste = 1;
        }
        else {
            this.coste = new_coste;
        }
        
        suma_costes += this.coste;
    }

    /**
     * Devuelve la suma total de los costes de todos los enlaces creados.
     * @return La suma total de los costes.
     */
    public static int getSumaCostes() {
        return suma_costes;
    }

    /**
     * Devuelve el coste especial del enlace.
     * @return El coste especial del enlace.
     */
    public int costeEspecial() {
        return 0;
    }

    /**
     * Devuelve el coste real del enlace.
     * @return El coste real del enlace.
     */
    public int costeReal() {
        return coste + costeEspecial();
    }

    /**
     * Devuelve una representación en cadena del enlace.
     * @return Una representación en cadena del enlace.
     */
    @Override
    public String toString() {
        return "(@" + this.origen.getNombre() + "--" + this.coste + "-->" + "@" + this.destino.getNombre() + ")";
    }
}