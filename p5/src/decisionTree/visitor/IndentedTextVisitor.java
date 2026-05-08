package decisionTree.visitor;

import decisionTree.Branch;
import decisionTree.DecisionTree;
import decisionTree.Node;

/**
 * Genera un texto indentado que resume la estructura del arbol.
 */
public class IndentedTextVisitor<T> implements DecisionTreeVisitor<T> {

    private final StringBuilder sb = new StringBuilder();

    @Override
    public void visitTree(DecisionTree<T> tree) {
        // No hace falta cabecera para este formato
    }

    @Override
    public void visitNode(Node<T> node, int depth) {
        indent(depth);
        sb.append(node.getName()).append("\n");
    }

    @Override
    public void visitBranch(Branch<T> branch, int depth) {
        indent(depth);
        if (branch.isFallback()) {
            sb.append("otherwise -> ").append(branch.getTargetNode()).append("\n");
            return;
        }
        String label = branch.getLabel();
        sb.append("if ")
            .append(label == null ? "condition" : label)
            .append(" -> ")
            .append(branch.getTargetNode())
            .append("\n");
    }

    public String getText() {
        return sb.toString();
    }

    private void indent(int depth) {
        for (int i = 0; i < depth; i++) {
            sb.append("  ");
        }
    }
}
