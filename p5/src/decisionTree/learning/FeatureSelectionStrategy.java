package decisionTree.learning;

import java.util.List;
import dataset.LabeledDataset;

/**
 * Estrategia para elegir la mejor feature en cada nodo.
 */
public interface FeatureSelectionStrategy<T, L> {
    String chooseFeature(LabeledDataset<T, L> dataset, List<String> availableFeatures);
}
