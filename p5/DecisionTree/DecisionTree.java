import java.util.*;
import java.util.function.Predicate;

public class DecisionTree<T> {
    
    private String rootNodeName = null;
    private final Map<String, Node> nodes = new LinkedHashMap<>();

    /**
     * Builder interno para configurar los nodos y sus ramas.
     */
    public class Node {
        private final String name;
        private final List<Branch> branches = new ArrayList<>();
        private String fallbackNode = null;

        public Node(String name) {
            this.name = name;
        }

        public Node withCondition(String targetNode, Predicate<T> condition) {
            branches.add(new Branch(targetNode, condition));
            return this;
        }

        public void otherwise(String targetNode) {
            this.fallbackNode = targetNode;
        }
    }

    private class Branch {
        String targetNode;
        Predicate<T> condition;

        Branch(String targetNode, Predicate<T> condition) {
            this.targetNode = targetNode;
            this.condition = condition;
        }
    }

    /**
     * Crea o recupera un nodo. El primero en crearse se asume como raíz.
     */
    public Node node(String name) {
        if (rootNodeName == null) {
            rootNodeName = name;
        }
        return nodes.computeIfAbsent(name, Node::new);
    }

    /**
     * Lógica principal de evaluación para un solo objeto.
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

            // Mecanismo de notificación para objetos atascados
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


    public final Map<String, List<T>> predict(T... objects) {
        return predict(Arrays.asList(objects));
    }

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
     */
    public Map<String, List<T>> predict(Dataset<T> dataset) {
        return predict(dataset.getObjects());
    }

    /**
     * Devuelve un Predicate que es la conjunción de todas las condiciones 
     * necesarias para llegar desde la raíz hasta el nodo con el nombre dado.
     */
    public Predicate<T> getPredicate(String label) {
        if (rootNodeName == null) return obj -> false;
        
        List<Predicate<T>> pathConditions = new ArrayList<>();
        if (findPath(rootNodeName, label, pathConditions, new HashSet<>())) {
            // Combinamos todos los predicados encontrados con .and()
            return pathConditions.stream().reduce(obj -> true, Predicate::and);
        }
        
        return obj -> false; // Si no se encuentra la etiqueta
    }

    /**
     * Busca recursivamente el camino hacia la etiqueta destino.
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
            path.remove(path.size() - 1); // Backtracking
        }

        // 2. Probar por el camino 'otherwise' (si existe)
        if (node.fallbackNode != null) {
            // El predicado para 'otherwise' es: NO se cumple ninguna de las ramas anteriores
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