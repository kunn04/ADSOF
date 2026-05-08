package decisionTree.learning;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import dataset.Featurizer;
import dataset.LabelProvider;
import dataset.LabeledDataset;
import decisionTree.DecisionTree;
import decisionTree.Node;

/**
 * Aprende un arbol de decision con un enfoque greedy.
 */
public class GreedyTreeLearner<T, L> {

    private static final String UNKNOWN_LABEL = "unknown";

    private final FeatureSelectionStrategy<T, L> strategy;
    private int nodeCounter = 0;

    public GreedyTreeLearner() {
        this(new RandomFeatureStrategy<>());
    }

    public GreedyTreeLearner(FeatureSelectionStrategy<T, L> strategy) {
        this.strategy = strategy;
    }

    public DecisionTree<T> learn(LabeledDataset<T, L> dataSet) {
        DecisionTree<T> tree = new DecisionTree<>();
        if (dataSet == null || dataSet.size() == 0) {
            tree.node(UNKNOWN_LABEL);
            return tree;
        }

        List<String> availableFeatures = new ArrayList<>(dataSet.getFeaturizer().getFeatureNames());
        buildSubtree(tree, dataSet, availableFeatures);
        return tree;
    }

    public DecisionTree<T> learn(Collection<T> objects, Featurizer<T> featurizer, LabelProvider<T, L> labelProvider) {
        LabeledDataset<T, L> dataSet = new LabeledDataset<>(featurizer, labelProvider);
        dataSet.addAll(objects);
        return learn(dataSet);
    }

    private String buildSubtree(DecisionTree<T> tree, LabeledDataset<T, L> dataSet, List<String> availableFeatures) {
        L sameLabel = allSameLabel(dataSet);
        if (sameLabel != null) {
            String labelName = String.valueOf(sameLabel);
            tree.node(labelName);
            return labelName;
        }

        if (availableFeatures.isEmpty()) {
            String labelName = String.valueOf(majorityLabel(dataSet));
            tree.node(labelName);
            return labelName;
        }

        String featureName = strategy.chooseFeature(dataSet, availableFeatures);
        if (featureName == null) {
            String labelName = String.valueOf(majorityLabel(dataSet));
            tree.node(labelName);
            return labelName;
        }

        String nodeName = "node_" + nodeCounter++;
        Node<T> node = tree.node(nodeName);

        List<String> remaining = new ArrayList<>(availableFeatures);
        remaining.remove(featureName);

        Map<Object, LabeledDataset<T, L>> subsets = splitByFeature(dataSet, featureName);
        for (Map.Entry<Object, LabeledDataset<T, L>> entry : subsets.entrySet()) {
            Object value = entry.getKey();
            LabeledDataset<T, L> subset = entry.getValue();
            String childName = buildSubtree(tree, subset, new ArrayList<>(remaining));
            String label = featureName + " == " + value;
            node.withCondition(
                childName,
                obj -> Objects.equals(getFeatureValue(obj, dataSet.getFeaturizer(), featureName), value),
                label
            );
        }

        String majorityLabelName = String.valueOf(majorityLabel(dataSet));
        tree.node(majorityLabelName);
        node.otherwise(majorityLabelName);

        return nodeName;
    }

    private L allSameLabel(LabeledDataset<T, L> dataSet) {
        if (dataSet.size() == 0) return null;
        L first = dataSet.labelAt(0);
        for (int i = 1; i < dataSet.size(); i++) {
            if (!Objects.equals(first, dataSet.labelAt(i))) return null;
        }
        return first;
    }

    private L majorityLabel(LabeledDataset<T, L> dataSet) {
        Map<L, Integer> counts = new LinkedHashMap<>();
        for (int i = 0; i < dataSet.size(); i++) {
            L label = dataSet.labelAt(i);
            counts.put(label, counts.getOrDefault(label, 0) + 1);
        }

        L best = null;
        int bestCount = -1;
        for (Map.Entry<L, Integer> entry : counts.entrySet()) {
            if (entry.getValue() > bestCount) {
                bestCount = entry.getValue();
                best = entry.getKey();
            }
        }
        return best;
    }

    private Map<Object, LabeledDataset<T, L>> splitByFeature(LabeledDataset<T, L> dataSet, String featureName) {
        Map<Object, LabeledDataset<T, L>> groups = new LinkedHashMap<>();
        int size = dataSet.size();

        for (int i = 0; i < size; i++) {
            Object value = dataSet.feature(featureName).get(i);
            LabeledDataset<T, L> subset = groups.computeIfAbsent(
                value,
                v -> new LabeledDataset<>(dataSet.getFeaturizer(), dataSet.getLabelProvider())
            );
            subset.add(dataSet.getObjects().get(i));
        }

        return groups;
    }

    private Comparable<?> getFeatureValue(T object, Featurizer<T> featurizer, String featureName) {
        return featurizer.getFeatureValue(object).get(featureName);
    }
}
