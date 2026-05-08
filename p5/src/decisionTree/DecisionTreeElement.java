package decisionTree;

import decisionTree.visitor.DecisionTreeVisitor;

/**
 * Interfaz común para elementos de un árbol de decisión (nodos y ramas) que pueden ser visitados por un DecisionTreeVisitor.
 */
public interface DecisionTreeElement<T> {
    void accept(DecisionTreeVisitor<T> visitor, int depth);
}
