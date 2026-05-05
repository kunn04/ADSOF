package decisionTree;

import java.util.*;
import java.util.function.Predicate;
import dataset.*;

/**
 * Esta clase implementa un árbol de decisión genérico,
 * permitiendo clasificar objetos en función de condiciones.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Versión: 1.0
 * Nombre del fichero: DecisionTree.java
 */

public class DecisionTree<T> {
    
    private String rootNodeName = null;
    private final Map<String, Node> nodes = new LinkedHashMap<>();

    /**
     * Constructor interno para configurar los nodos y sus ramas.
     */
    public class Node {
        private final String name;
        private final List<Branch> branches = new ArrayList<>();
        private String fallbackNode = null;

        /** 
         * Constructor de la clase Node.
         * @param name nombre del nodo
         */
        public Node(String name) {
            this.name = name;
        }

        /** 
         * Getter del nombre del nodo.
         * @return name nombre del nodo
         */
        public String getName() {
            return this.name;
        }

        /** 
         * Añade una condición que lleva a otro nodo.
         * @param targetNode nodo destino
         * @param condition condición a evaluar
         * @return el propio nodo para encadenamiento
         */
        public Node withCondition(String targetNode, Predicate<T> condition) {
            branches.add(new Branch(targetNode, condition));
            return this;
        }

        /** 
         * Define el nodo al que ir si no se cumple ninguna condición.
         * @param targetNode nodo destino por defecto
         */
        public void otherwise(String targetNode) {
            this.fallbackNode = targetNode;
        }
    }

    /**
     * Clase interna que representa una rama del árbol.
     */
    private class Branch {
        String targetNode;
        Predicate<T> condition;

        /** 
         * Constructor de la clase Branch.
         * @param targetNode nodo destino
         * @param condition condición asociada
         */
        Branch(String targetNode, Predicate<T> condition) {
            this.targetNode = targetNode;
            this.condition = condition;
        }
    }

    /**
     * Crea o recupera un nodo. El primero en crearse se asume como raíz.
     * @param name nombre del nodo
     * @return nodo correspondiente
     */
    public Node node(String name) {
        if (rootNodeName == null) {
            rootNodeName = name;
        }
        return nodes.computeIfAbsent(name, Node::new);
    }

    /**
     * Lógica principal de evaluación para un solo objeto.
     * @param object objeto a evaluar
     * @return etiqueta final alcanzada
     */
    private String predictSingle(T object) {
        if (rootNodeName == null) {
            throw new IllegalStateException("El árbol de decisión está vacío.");
        }

        String currentNodeName = rootNodeName;

        while (true) {
            Node node = nodes.get(currentNodeName);

            // Es un nodo hoja si no ha sido definido con ramas o fallback
            if (node == null || (node.branches.isEmpty() && node.fallbackNode == null)) {
                return currentNodeName;
            }

            boolean matched = false;
            for (Branch branch : node.branches) {
                if (branch.condition.test(object)) {
                    currentNodeName = branch.targetNode;
                    matched = true;
                    break;
                }
            }

            // Mecanismo de control si no se cumple ninguna condición
            if (!matched) {
                if (node.fallbackNode != null) {
                    currentNodeName = node.fallbackNode;
                } else {
                    throw new IllegalStateException(
                        String.format("Objeto atascado en el nodo '%s'. No cumple ninguna condición y no tiene 'otherwise'.", currentNodeName)
                    );
                }
            }
        }
    }

    /** 
     * Predice las etiquetas para múltiples objetos (varargs).
     * @param objects objetos a evaluar
     * @return mapa de etiquetas a lista de objetos
     */
    public final Map<String, List<T>> predict(T... objects) {
        return predict(Arrays.asList(objects));
    }

    /** 
     * Predice las etiquetas para una colección de objetos.
     * @param objects colección de objetos
     * @return mapa de etiquetas a lista de objetos
     */
    public Map<String, List<T>> predict(Collection<T> objects) {
        Map<String, List<T>> results = new LinkedHashMap<>();
        for (T obj : objects) {
            String label = predictSingle(obj);
            results.computeIfAbsent(label, k -> new ArrayList<>()).add(obj);
        }
        return results;
    }

    /**
     * Versión para Dataset.
     * @param dataset conjunto de datos
     * @return mapa de etiquetas a lista de objetos
     */
    public Map<String, List<T>> predict(Dataset<T> dataset) {
        return predict(dataset.getObjects());
    }

    /**
     * Devuelve un Predicate que representa las condiciones necesarias
     * para llegar desde la raíz hasta una etiqueta dada.
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
        Node node = nodes.get(currentNodeName);
        if (node == null) return false;

        // 1. Probar por las ramas con condición
        for (Branch branch : node.branches) {
            path.add(branch.condition);
            if (findPath(branch.targetNode, targetLabel, path, visited)) {
                return true;
            }
            path.remove(path.size() - 1);
        }

        // 2. Probar por el camino 'otherwise'
        if (node.fallbackNode != null) {
            Predicate<T> noneOfBranches = obj -> {
                for (Branch b : node.branches) {
                    if (b.condition.test(obj)) return false;
                }
                return true;
            };
            
            path.add(noneOfBranches);
            if (findPath(node.fallbackNode, targetLabel, path, visited)) {
                return true;
            }
            path.remove(path.size() - 1);
        }

        return false;
    }
}