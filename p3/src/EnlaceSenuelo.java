package src;

/**
 * Esta clase representa un enlace señuelo entre dos usuarios en una red social, con un coste asociado. El enlace tiene un factor extra que multiplica su coste y una probabilidad de retorno que hace que el destino del enlace sea el origen en lugar del destino real.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Version: 1.0
 * Nombre del fichero: EnlaceSenuelo.java
 */
public class EnlaceSenuelo extends Enlace {
    
    private int factorExtra;
    private double probabilidadRetorno;

    /**
     * Crea un enlace señuelo entre dos usuarios con un coste, factor extra y probabilidad de retorno.
     * @param origen El usuario que envía el mensaje.
     * @param destino El usuario que recibe el mensaje.
     * @param coste El coste base del enlace.
     * @param factorExtra El factor que multiplica el coste.
     * @param probabilidadRetorno La probabilidad de que el destino sea el origen.
     */
    public EnlaceSenuelo(Usuario origen, Usuario destino, int coste, int factorExtra, double probabilidadRetorno) {
        super(origen, destino, coste);
        this.factorExtra = factorExtra;
        this.probabilidadRetorno = probabilidadRetorno;
    }

    /**
     * Devuelve el coste especial del enlace señuelo.
     * @return El coste especial del enlace.
     */
    @Override
    public int costeEspecial() {
        return super.getCoste() * this.factorExtra;
    }

    /**
     * Devuelve el destino del enlace señuelo, que puede ser el origen dependiendo de la probabilidad de retorno.
     * @return El usuario que recibe el mensaje.
     */
    @Override
    public Usuario getDestino() {
        if (Math.random() < this.probabilidadRetorno) {
            return super.getOrigen(); 
        }
        
        return super.getDestino(); 
    }

    /**
     * Devuelve una representación en cadena del enlace señuelo.
     * @return La cadena que representa el enlace.
     */
    @Override
    public String toString() {
        return super.toString() + " [Señuelo: f=" + factorExtra + ", prob=" + probabilidadRetorno + "]";
    }
}