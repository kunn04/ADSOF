import dataset.*;
import person.*;
import decisionTree.*;
/**
 * Esta clase prueba el funcionamiento del árbol de decisión,
 * incluyendo la clasificación de objetos individuales y datasets.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Versión: 1.0
 * Nombre del fichero: TestApartado2.java
 */
public class TestApartado2 {
    /** 
     * Método principal de ejecución.
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        Dataset<Person> dataSet = buildDataSet();
        DecisionTree<Person> dt = buildDecisionTree();

        System.out.println(dt.predict(dataSet));
        System.out.println(dt.predict(new Person("Miguel", 86, 72, 165, true), new Person("Clara", 42, 59, 162, false)));
    }

    /** 
     * Construye un árbol de decisión de ejemplo.
     * @return árbol de decisión
     */
    public static DecisionTree<Person> buildDecisionTree() {
        DecisionTree<Person> dt = new DecisionTree<>();
        dt.node("root")  // nodo raiz, al ser el primero que se añade
          .withCondition("male", p -> p.isMale())
          .otherwise("female");
        dt.node("male")
          .withCondition("old male", p -> p.getAge() > 65)
          .withCondition("middle male", p -> p.getAge() <= 65 && p.getAge() > 34)
          .otherwise("young male");
        return dt;
    }

    /** 
     * Construye un dataset de ejemplo con personas.
     * @return dataset de personas
     */
    public static Dataset<Person> buildDataSet() {
        Person people [] = { new Person("Pedro", 66, 75, 180, true), // name, age, weight, height, male?
                            new Person("Ana", 47, 54, 158, false),
                            new Person("Luis", 34, 75, 176, true),
                            new Person("Rosa", 47, 54, 158, false)
        };

        Dataset<Person> dataSet = new Dataset<>(new PersonFeaturizer());
        dataSet.addAll(people);
        return dataSet;
    }

}
