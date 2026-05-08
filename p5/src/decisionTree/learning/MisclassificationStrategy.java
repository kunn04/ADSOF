package decisionTree.learning;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import dataset.Feature;
import dataset.LabeledDataset;

/**
 * Elige la feature con menor error de clasificacion.
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
