package decisionTree.learning;

import java.util.List;
import java.util.Random;
import dataset.LabeledDataset;

/**
 * Seleccion aleatoria de features.
 */
public class RandomFeatureStrategy<T, L> implements FeatureSelectionStrategy<T, L> {

    private final Random random;

    public RandomFeatureStrategy() {
        this(new Random());
    }

    public RandomFeatureStrategy(Random random) {
        this.random = random;
    }

    @Override
    public String chooseFeature(LabeledDataset<T, L> dataset, List<String> availableFeatures) {
        if (availableFeatures == null || availableFeatures.isEmpty()) return null;
        return availableFeatures.get(random.nextInt(availableFeatures.size()));
    }
}
