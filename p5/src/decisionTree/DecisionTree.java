package decisionTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import dataset.Dataset;
import decisionTree.visitor.DecisionTreeVisitor;

/**
 * Esta clase implementa un arbol de decision generico,
 * permitiendo clasificar objetos en funcion de condiciones.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Versión: 1.0
 * Nombre del fichero: DecisionTree.java
 */

public class DecisionTree<T> {

    private String rootNodeName = null;
    private final Map<String, Node<T>> nodes = new LinkedHashMap<>();
    private final Map<String, List<T>> lastUnclassified = new LinkedHashMap<>();

    /**
     * Crea o recupera un nodo. El primero en crearse se asume como raiz.
     * @param name nombre del nodo
     * @return nodo correspondiente
     */
    public Node<T> node(String name) {
        if (rootNodeName == null) {
            rootNodeName = name;
        }
        return nodes.computeIfAbsent(name, Node::new);
    }

    public String getRootNodeName() {
        return rootNodeName;
    }

    public Map<String, Node<T>> getNodes() {
        return java.util.Collections.unmodifiableMap(nodes);
    }

    public Map<String, List<T>> getUnclassified() {
        return java.util.Collections.unmodifiableMap(lastUnclassified);
    }

    /**
     * Logica principal de evaluacion para un solo objeto.
     * @param object objeto a evaluar
     * @param unclassified mapa para registrar objetos atascados
     * @return etiqueta final alcanzada
     */
    private String predictSingle(T object, Map<String, List<T>> unclassified) {
        if (rootNodeName == null) {
            return null;
        }

        String currentNodeName = rootNodeName;

        while (true) {
            Node<T> node = nodes.get(currentNodeName);

            if (node == null || (node.getBranches().isEmpty() && node.getFallbackBranch() == null)) {
                return currentNodeName;
            }

            boolean matched = false;
            for (Branch<T> branch : node.getBranches()) {
                if (branch.getCondition().test(object)) {
                    currentNodeName = branch.getTargetNode();
                    matched = true;
                    break;
                }
            }

            if (!matched) {
                Branch<T> fallback = node.getFallbackBranch();
                if (fallback != null) {
                    currentNodeName = fallback.getTargetNode();
                } else {
                    if (unclassified != null) {
                        unclassified.computeIfAbsent(currentNodeName, k -> new ArrayList<>()).add(object);
                    }
                    return currentNodeName;
                }
            }
        }
    }

    /**
     * Predice las etiquetas para multiples objetos (varargs).
     * @param objects objetos a evaluar
     * @return mapa de etiquetas a lista de objetos
     */
    @SafeVarargs
    public final Map<String, List<T>> predict(T... objects) {
        return predict(Arrays.asList(objects));
    }

    /**
     * Predice las etiquetas para una coleccion de objetos.
     * @param objects coleccion de objetos
     * @return mapa de etiquetas a lista de objetos
     */
    public Map<String, List<T>> predict(Collection<T> objects) {
        lastUnclassified.clear();
        Map<String, List<T>> results = new LinkedHashMap<>();
        for (T obj : objects) {
            String label = predictSingle(obj, lastUnclassified);
            results.computeIfAbsent(label, k -> new ArrayList<>()).add(obj);
        }
        return results;
    }

    /**
     * Version para Dataset.
     * @param dataset conjunto de datos
     * @return mapa de etiquetas a lista de objetos
     */
    public Map<String, List<T>> predict(Dataset<T> dataset) {
        return predict(dataset.getObjects());
    }

    /**
     * Devuelve un Predicate que representa las condiciones necesarias
     * para llegar desde la raiz hasta una etiqueta dada.
     * @param label etiqueta destino
     * @return predicado correspondiente
     */
    public Predicate<T> getPredicate(String label) {
        if (rootNodeName == null) return obj -> false;

        List<Predicate<T>> pathConditions = new ArrayList<>();
        if (findPath(rootNodeName, label, pathConditions, new HashSet<>())) {
            return pathConditions.stream().reduce(obj -> true, Predicate::and);
        }

        return obj -> false;
    }

    /**
     * Busca recursivamente el camino hacia una etiqueta destino.
     * @param currentNodeName nodo actual
     * @param targetLabel etiqueta objetivo
     * @param path lista de condiciones acumuladas
     * @param visited nodos visitados
     * @return true si se encuentra el camino, false en caso contrario
     */
    private boolean findPath(String currentNodeName, String targetLabel, List<Predicate<T>> path, Set<String> visited) {
        if (currentNodeName.equals(targetLabel)) return true;
        if (visited.contains(currentNodeName)) return false;

        visited.add(currentNodeName);
        Node<T> node = nodes.get(currentNodeName);
        if (node == null) return false;

        for (Branch<T> branch : node.getBranches()) {
            path.add(branch.getCondition());
            if (findPath(branch.getTargetNode(), targetLabel, path, visited)) {
                return true;
            }
            path.remove(path.size() - 1);
        }

        Branch<T> fallback = node.getFallbackBranch();
        if (fallback != null) {
            Predicate<T> noneOfBranches = obj -> {
                for (Branch<T> b : node.getBranches()) {
                    if (b.getCondition().test(obj)) return false;
                }
                return true;
            };

            path.add(noneOfBranches);
            if (findPath(fallback.getTargetNode(), targetLabel, path, visited)) {
                return true;
            }
            path.remove(path.size() - 1);
        }

        return false;
    }

    /**
     * Acepta un visitor para recorrer el arbol en profundidad.
     * @param visitor visitor a aplicar
     */
    public void accept(DecisionTreeVisitor<T> visitor) {
        visitor.visitTree(this);
        if (rootNodeName == null) return;
        traverse(rootNodeName, visitor, new HashSet<>(), 0);
    }

    private void traverse(String nodeName, DecisionTreeVisitor<T> visitor, Set<String> visited, int depth) {
        if (!visited.add(nodeName)) return;
        Node<T> node = nodes.get(nodeName);
        if (node == null) return;

        node.accept(visitor, depth);
        for (Branch<T> branch : node.getBranches()) {
            branch.accept(visitor, depth + 1);
            traverse(branch.getTargetNode(), visitor, visited, depth + 2);
        }

        Branch<T> fallback = node.getFallbackBranch();
        if (fallback != null) {
            fallback.accept(visitor, depth + 1);
            traverse(fallback.getTargetNode(), visitor, visited, depth + 2);
        }
    }
}