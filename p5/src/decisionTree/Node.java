package decisionTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import decisionTree.visitor.DecisionTreeVisitor;

/**
 * Esta clase representa un nodo en un árbol de decisión, que contiene una lista de ramas (condiciones) y una rama de respaldo (fallback) para manejar casos que no cumplen ninguna condición.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Versión: 1.0
 * Nombre del fichero: Node.java
 */
public class Node<T> implements DecisionTreeElement<T> {

    private final String name;
    private final List<Branch<T>> branches = new ArrayList<>();
    private Branch<T> fallbackBranch;

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public List<Branch<T>> getBranches() {
        return Collections.unmodifiableList(branches);
    }

    public Branch<T> getFallbackBranch() {
        return fallbackBranch;
    }

    public String getFallbackNode() {
        return fallbackBranch != null ? fallbackBranch.getTargetNode() : null;
    }

    public Node<T> withCondition(String targetNode, Predicate<T> condition) {
        return withCondition(targetNode, condition, targetNode);
    }

    public Node<T> withCondition(String targetNode, Predicate<T> condition, String label) {
        branches.add(new Branch<>(name, targetNode, condition, label, false));
        return this;
    }

    public void otherwise(String targetNode) {
        this.fallbackBranch = new Branch<>(name, targetNode, null, "otherwise", true);
    }

    @Override
    public void accept(DecisionTreeVisitor<T> visitor, int depth) {
        visitor.visitNode(this, depth);
    }
}
