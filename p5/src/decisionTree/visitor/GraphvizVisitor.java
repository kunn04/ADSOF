package decisionTree.visitor;

import decisionTree.Branch;
import decisionTree.DecisionTree;
import decisionTree.Node;

/**
 * Genera la representacion DOT (Graphviz) de un arbol de decision.
 */
public class GraphvizVisitor<T> implements DecisionTreeVisitor<T> {

    private final StringBuilder sb = new StringBuilder();
    private boolean closed;

    @Override
    public void visitTree(DecisionTree<T> tree) {
        sb.append("digraph DecisionTree {\n");
    }

    @Override
    public void visitNode(Node<T> node, int depth) {
        sb.append("  \"").append(escape(node.getName())).append("\";\n");
    }

    @Override
    public void visitBranch(Branch<T> branch, int depth) {
        sb.append("  \"")
            .append(escape(branch.getSourceNode()))
            .append("\" -> \"")
            .append(escape(branch.getTargetNode()))
            .append("\"");

        String label = branch.isFallback() ? "otherwise" : branch.getLabel();
        if (label != null && !label.isEmpty()) {
            sb.append(" [label=\"").append(escape(label)).append("\"]");
        }

        sb.append(";\n");
    }

    public String getDot() {
        if (!closed) {
            sb.append("}\n");
            closed = true;
        }
        return sb.toString();
    }

    private String escape(String text) {
        if (text == null) return "";
        return text.replace("\"", "\\\"");
    }
}
