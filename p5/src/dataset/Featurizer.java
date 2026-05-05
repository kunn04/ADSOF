package dataset;

import java.util.*;

/**
 * Esta interfaz define un featurizer, encargado de extraer características
 * de un objeto genérico.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Versión: 1.0
 * Nombre del fichero: Featurizer.java
 */

public interface Featurizer<T> {

    /** 
     * Obtiene los nombres de las características.
     * @return lista de nombres de características
     */
    List<String> getFeatureNames();

    /** 
     * Extrae los valores de las características de un objeto.
     * @param object objeto del que se extraen las características
     * @return mapa con nombre de característica y su valor
     */
    Map<String, Object> getFeatureValue(T object);
}
