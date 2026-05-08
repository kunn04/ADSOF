package dataset;

import java.util.*;

/**
 * Esta clase representa una característica (Feature) de un dataset,
 * almacenando valores comparables y permitiendo operaciones estadísticas básicas.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Versión: 1.0
 * Nombre del fichero: Feature.java
 */
public class Feature<V extends Comparable<? super V>> extends ArrayList<V> {

    /** 
     * Obtiene el valor mínimo de la característica.
     * @return valor mínimo o null si está vacía
     */
    public V min() {
        if (this.isEmpty()) return null;

        return Collections.min(this);
    }

    /** 
     * Obtiene el valor máximo de la característica.
     * @return valor máximo o null si está vacía
     */
    public V max() {
        if (this.isEmpty()) return null;

        return Collections.max(this);
    }

    /** 
     * Calcula la distribución de frecuencias de los valores.
     * @return mapa con cada valor y su frecuencia
     */
    public Map<V, Integer> distribution() {
        Map<V, Integer> freqOfValue = new LinkedHashMap<>();
        
        for (V value : this) {
            freqOfValue.put(value, freqOfValue.getOrDefault(value, 0) + 1);
        }

        return freqOfValue;
    }
    
}
