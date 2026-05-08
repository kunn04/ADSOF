package dataset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Esta clase representa un dataset etiquetado (LabeledDataset) que extiende de Dataset,
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Versión: 1.0
 * Nombre del fichero: LabeledDataset.java
 */
public class LabeledDataset<T, L> extends Dataset<T> {

    private final LabelProvider<T, L> labelProvider;
    private List<L> labels = new ArrayList<>();

    public LabeledDataset(Featurizer<T> featurizer, LabelProvider<T, L> labelProvider) {
        super(featurizer);
        this.labelProvider = labelProvider;
    }

    @Override
    public void add(T object) {
        super.add(object);
        labels.add(labelProvider.getLabel(object));
    }

    public List<L> getLabels() {
        return Collections.unmodifiableList(labels);
    }

    public L labelAt(int index) {
        return labels.get(index);
    }

    public LabelProvider<T, L> getLabelProvider() {
        return labelProvider;
    }

    @Override
    public void removeDuplicates() {
        if (data.isEmpty()) return;

        int size = data.values().iterator().next().size();
        Set<List<Comparable<?>>> uniqueRows = new LinkedHashSet<>();
        List<T> uniqueObjects = new ArrayList<>();
        List<L> uniqueLabels = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            List<Comparable<?>> row = new ArrayList<>();
            for (Feature<? extends Comparable<?>> col : data.values()) {
                row.add(col.get(i));
            }
            if (uniqueRows.add(row)) {
                uniqueObjects.add(objects.get(i));
                uniqueLabels.add(labels.get(i));
            }
        }

        data.values().forEach(List::clear);
        for (List<Comparable<?>> row : uniqueRows) {
            int colIdx = 0;
            for (String name : data.keySet()) {
                addValue(name, row.get(colIdx++));
            }
        }

        this.objects = uniqueObjects;
        this.labels = uniqueLabels;
    }
}
