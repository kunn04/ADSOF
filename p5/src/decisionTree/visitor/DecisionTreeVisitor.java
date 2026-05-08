package decisionTree.visitor;

import decisionTree.Branch;
import decisionTree.DecisionTree;
import decisionTree.Node;

/**
 * Visitor para recorrer un arbol de decision y sus elementos.
 */
public interface DecisionTreeVisitor<T> {
    void visitTree(DecisionTree<T> tree);
    void visitNode(Node<T> node, int depth);
    void visitBranch(Branch<T> branch, int depth);
}
