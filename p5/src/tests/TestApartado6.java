import person.Person;
import decisionTree.DecisionTree;
import decisionTree.visitor.GraphvizVisitor;
import decisionTree.visitor.IndentedTextVisitor;

/**
 * Prueba de generación de representaciones visuales (DOT y texto indentado) de un árbol de decisión construido manualmente, utilizando los visitantes GraphvizVisitor e IndentedTextVisitor para recorrer el árbol y generar las representaciones correspondientes.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Versión: 1.0
 * Nombre del fichero: TestApartado6.java
 */
public class TestApartado6 {

    public static void main(String[] args) {
        DecisionTree<Person> dt = buildDecisionTree();

        GraphvizVisitor<Person> graphviz = new GraphvizVisitor<>();
        dt.accept(graphviz);
        System.out.println("--- DOT ---");
        System.out.println(graphviz.getDot());

        IndentedTextVisitor<Person> text = new IndentedTextVisitor<>();
        dt.accept(text);
        System.out.println("--- Indented ---");
        System.out.println(text.getText());
    }

    private static DecisionTree<Person> buildDecisionTree() {
        DecisionTree<Person> dt = new DecisionTree<>();
        dt.node("root")
            .withCondition("male", p -> p.isMale())
            .otherwise("female");
        dt.node("male")
            .withCondition("old male", p -> p.getAge() > 65)
            .withCondition("middle male", p -> p.getAge() <= 65 && p.getAge() > 34)
            .otherwise("young male");
        return dt;
    }
}
