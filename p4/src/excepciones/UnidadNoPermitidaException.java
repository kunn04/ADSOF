package excepciones;

/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: UnidadNoPermitidaException.java
 */

public class UnidadNoPermitidaException extends Exception {
    /** 
     * Constructor de la clase.
     * @param mensaje mensaje de la excepción
     */
    public UnidadNoPermitidaException(String mensaje) {
        super(mensaje);
    }
}
