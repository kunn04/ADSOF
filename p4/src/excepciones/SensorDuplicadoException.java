package excepciones;

/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: SensorDuplicadoException.java
 */

import sensores.Sensor;

public class SensorDuplicadoException extends Exception {
    private Sensor sensorEnConflicto;

    /** 
     * Constructor de la clase.
     * @param s sensor duplicado
     */
    public SensorDuplicadoException(Sensor s) {
        super("Error: El sensor con ID " + s.getId() + " ya existe en la estación.");
        this.sensorEnConflicto = s;
    }

    /**
     * Metodo publico de la clase.
     * @return sensor en conflicto
     */
    public Sensor getSensorEnConflicto() {
        return sensorEnConflicto;
    }
}
