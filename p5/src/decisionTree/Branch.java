package decisionTree;

import java.util.function.Predicate;
import decisionTree.visitor.DecisionTreeVisitor;

/**
 * Esta clase representa una rama de un nodo en un árbol de decisión, que define la condición para seguir esa rama y el nodo de destino al que conduce.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Versión: 1.0
 * Nombre del fichero: Branch.java
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
