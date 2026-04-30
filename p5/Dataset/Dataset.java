import java.util.*;

public class Dataset<T> {

    private Featurizer<T> featurizer;
    private Map<String, Feature<? extends Comparable>> data;
    private List<T> objects = new ArrayList<>();

    public Dataset(Featurizer<T> featurizer) {
        this.featurizer = featurizer;
        this.data = new LinkedHashMap<>();
        for (String featureName : featurizer.getFeatureNames()) {
            data.put(featureName, new Feature<>());
        }
    }

    public List<T> getObjects() {
        return objects;
    }

    public void add(T object) {
        objects.add(object);
        Map<String, Object> values = featurizer.getFeatureValue(object);
        for (String name : data.keySet()) {
            Feature feature = data.get(name);
            feature.add((Comparable) values.get(name));
        }
    }

    public Dataset<T> addAll(T[] objects) {
        for (T object : objects) {
            this.add(object);
        }
        return this;
    }

    public <V extends Comparable<? super V>> Feature<V> feature(String name) {
        return (Feature<V>) data.get(name);
    }

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

        // Limpiamos las columnas y reinsertamos solo los valores únicos
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
    public String toString() {
        return data.toString();
    }
}
