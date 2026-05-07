import java.util.function.Predicate;
import dataset.*;
import person.*;
import decisionTree.*;
/**
 * Esta clase prueba la obtención de predicados a partir del árbol de decisión,
 * así como su uso para validar objetos y filtrar datasets.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Versión: 1.0
 * Nombre del fichero: TestApartado3.java
 */
public class TestApartado3 {
    /** 
     * Método principal de ejecución.
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        DecisionTree<Person> dt = buildDecisionTree();
        
        // 1. Obtenemos el predicado para la etiqueta "old male"
        Predicate<Person> isOldMale = dt.getPredicate("old male");
        
        // 2. Obtenemos el predicado para la etiqueta "female" (que viene de un 'otherwise')
        Predicate<Person> isFemale = dt.getPredicate("female");

        Person p1 = new Person("Pedro", 66, 75, 180, true);  
        Person p2 = new Person("Ana", 47, 54, 158, false);    
        Person p3 = new Person("Luis", 34, 75, 176, true);   

        System.out.println("--- Verificación de Predicados ---");
        System.out.println(p1.getName() + " es 'old male'?: " + isOldMale.test(p1)); // true
        System.out.println(p3.getName() + " es 'old male'?: " + isOldMale.test(p3)); // false
        System.out.println(p2.getName() + " es 'female'?: " + isFemale.test(p2));   // true
        System.out.println(p3.getName() + " es 'female'?: " + isFemale.test(p3));   // false
        
        // 3. Prueba con un Dataset
        Dataset<Person> dataSet = buildDataSet();
        System.out.println("\n--- Filtrado de Dataset usando el predicado 'old male' ---");
        for(Person p : dataSet.getObjects()) {
            if(isOldMale.test(p)) {
                System.out.println("Encontrado: " + p);
            }
        }
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
