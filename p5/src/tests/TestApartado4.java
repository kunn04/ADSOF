import dataset.LabeledDataset;
import dataset.LabelProvider;
import person.Person;
import person.PersonFeaturizer;
import decisionTree.DecisionTree;
import decisionTree.learning.GreedyTreeLearner;

/**
 * Prueba de aprendizaje de un árbol de decisión a partir de un dataset etiquetado, utilizando el GreedyTreeLearner para construir el árbol y luego realizando predicciones con el árbol aprendido.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Versión: 1.0
 * Nombre del fichero: TestApartado4.java
 */
public class TestApartado4 {

    public static void main(String[] args) {
        LabeledDataset<Person, Boolean> dataSet = buildLabeledDataSet();
        GreedyTreeLearner<Person, Boolean> learner = new GreedyTreeLearner<>();
        DecisionTree<Person> tree = learner.learn(dataSet);

        System.out.println(tree.predict(dataSet));
        System.out.println(tree.predict(
            new Person("Mario", 20, 70, 175, true),
            new Person("Eva", 50, 60, 165, false)
        ));
    }

    private static LabeledDataset<Person, Boolean> buildLabeledDataSet() {
        Person[] people = {
            new Person("Pedro", 66, 75, 180, true),
            new Person("Ana", 47, 54, 158, false),
            new Person("Luis", 34, 75, 176, true),
            new Person("Rosa", 47, 54, 158, false)
        };

        LabelProvider<Person, Boolean> labelProvider = p -> p.getAge() >= 40;
        LabeledDataset<Person, Boolean> dataSet = new LabeledDataset<>(new PersonFeaturizer(), labelProvider);
        dataSet.addAll(people);
        return dataSet;
    }
}
