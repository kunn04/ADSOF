package decisionTree;

import decisionTree.visitor.DecisionTreeVisitor;

/**
 * Elemento visitable del arbol de decision.
 */
public interface DecisionTreeElement<T> {
    void accept(DecisionTreeVisitor<T> visitor, int depth);
}
