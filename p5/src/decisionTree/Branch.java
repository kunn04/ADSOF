package decisionTree;

import java.util.function.Predicate;
import decisionTree.visitor.DecisionTreeVisitor;

/**
 * Rama de un nodo, con condicion y nodo de destino.
 */
public class Branch<T> implements DecisionTreeElement<T> {

    private final String sourceNode;
    private final String targetNode;
    private final Predicate<T> condition;
    private final String label;
    private final boolean fallback;

    public Branch(String sourceNode, String targetNode, Predicate<T> condition, String label, boolean fallback) {
        this.sourceNode = sourceNode;
        this.targetNode = targetNode;
        this.condition = condition;
        this.label = label;
        this.fallback = fallback;
    }

    public String getSourceNode() {
        return sourceNode;
    }

    public String getTargetNode() {
        return targetNode;
    }

    public Predicate<T> getCondition() {
        return condition;
    }

    public String getLabel() {
        return label;
    }

    public boolean isFallback() {
        return fallback;
    }

    @Override
    public void accept(DecisionTreeVisitor<T> visitor, int depth) {
        visitor.visitBranch(this, depth);
    }
}
