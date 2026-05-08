package decisionTree.learning;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import dataset.Feature;
import dataset.LabeledDataset;

/**
 * Esta clase representa una estrategia de selección de características (MisclassificationStrategy) para el aprendizaje de árboles de decisión, que elige la característica que minimiza el número de errores de clasificación (misclassification) al dividir un dataset durante el proceso de construcción del árbol.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Versión: 1.0
 * Nombre del fichero: MisclassificationStrategy.java
 */
public class MisclassificationStrategy<T, L> implements FeatureSelectionStrategy<T, L> {

    @Override
    public String chooseFeature(LabeledDataset<T, L> dataset, List<String> availableFeatures) {
        if (availableFeatures == null || availableFeatures.isEmpty()) return null;

        String bestFeature = availableFeatures.get(0);
        int bestScore = Integer.MAX_VALUE;
        int size = dataset.size();

        for (String featureName : availableFeatures) {
            Feature<? extends Comparable<?>> feature = dataset.feature(featureName);
            Map<Object, Map<L, Integer>> countByValue = new LinkedHashMap<>();

            for (int i = 0; i < size; i++) {
                Object value = feature.get(i);
                L label = dataset.labelAt(i);
                Map<L, Integer> counts = countByValue.computeIfAbsent(value, k -> new LinkedHashMap<>());
                counts.put(label, counts.getOrDefault(label, 0) + 1);
            }

            int score = 0;
            for (Map<L, Integer> counts : countByValue.values()) {
                int total = 0;
                int max = 0;
                for (int c : counts.values()) {
                    total += c;
                    if (c > max) max = c;
                }
                score += (total - max);
            }

            if (score < bestScore) {
                bestScore = score;
                bestFeature = featureName;
            }
        }

        return bestFeature;
    }
}
