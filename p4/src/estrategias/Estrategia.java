package estrategias;

/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: Estrategia.java
 */

import sensores.Sensor;

public interface Estrategia {
    /**
     * Genera una lectura simulada para el sensor indicado.
     * @param sensor sensor sobre el que se simula la lectura
     * @return valor simulado
     */
    double generarValor(Sensor sensor);
}
