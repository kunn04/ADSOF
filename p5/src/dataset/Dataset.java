package dataset;

import java.util.*;

/**
 * Esta clase representa un conjunto de datos genérico (Dataset),
 * que almacena objetos junto con sus características (features).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Versión: 1.0
 * Nombre del fichero: Dataset.java
 */

public class Dataset<T> {

    private Featurizer<T> featurizer;
    private Map<String, Feature<? extends Comparable>> data;
    private List<T> objects = new ArrayList<>();

    /** 
     * Constructor de la clase.
     * Inicializa el dataset con un featurizer que define las características.
     * @param featurizer objeto encargado de extraer características de los objetos
     */
    public Dataset(Featurizer<T> featurizer) {
        this.featurizer = featurizer;
        this.data = new LinkedHashMap<>();
        for (String featureName : featurizer.getFeatureNames()) {
            data.put(featureName, new Feature<>());
        }
    }

    /** 
     * Obtiene la lista de objetos almacenados en el dataset.
     * @return lista de objetos
     */
    public List<T> getObjects() {
        return this.objects;
    }

    /** 
     * Añade un objeto al dataset y extrae sus características.
     * @param object objeto a añadir
     */
    public void add(T object) {
        objects.add(object);
        Map<String, Object> values = featurizer.getFeatureValue(object);
        for (String name : data.keySet()) {
            Feature feature = data.get(name);
            feature.add((Comparable) values.get(name));
        }
    }

    /** 
     * Añade múltiples objetos al dataset.
     * @param objects array de objetos a añadir
     * @return el propio dataset actualizado
     */
    public Dataset<T> addAll(T[] objects) {
        for (T object : objects) {
            this.add(object);
        }
        return this;
    }

    /** 
     * Obtiene una característica por su nombre.
     * @param name nombre de la característica
     * @return objeto Feature correspondiente
     */
    public <V extends Comparable<? super V>> Feature<V> feature(String name) {
        return (Feature<V>) data.get(name);
    }

    /** 
     * Elimina filas duplicadas del dataset basándose en sus características.
     */
    public void removeDuplicates() {
        if(data.isEmpty()) return;

        int size = data.values().iterator().next().size();
        Set<List<Comparable>> uniqueRows = new LinkedHashSet<>();
        List<T> uniqueObjects = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            List<Comparable> row = new ArrayList<>();
            for (Feature<?> col : data.values()) {
                row.add(col.get(i));
            }
            if(uniqueRows.add(row)) {
                uniqueObjects.add(objects.get(i));
            }
        }

        data.values().forEach(List::clear);
        for (List<Comparable> row : uniqueRows) {
            int colIdx = 0;
            for (Feature col : data.values()) {
                ((Feature) col).add((Comparable) row.get(colIdx++));
            }
        }

        this.objects = uniqueObjects;
    }

    @Override
    /** 
     * Devuelve una representación en cadena del dataset.
     * @return representación en cadena de los datos
     */
    public String toString() {
        return data.toString();
    }
}