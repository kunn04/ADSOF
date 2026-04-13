package excepciones;

/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: LecturaFueraRangoException.java
 */

public class LecturaFueraRangoException extends Exception {
    /** 
     * Constructor de la clase.
     * @param mensaje mensaje de la excepción
     */
    public LecturaFueraRangoException(String mensaje) {
        super(mensaje);
    }
}
