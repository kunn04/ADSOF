import java.util.Random;
import dataset.LabeledDataset;
import dataset.LabelProvider;
import person.Person;
import person.PersonFeaturizer;
import decisionTree.DecisionTree;
import decisionTree.learning.GreedyTreeLearner;
import decisionTree.learning.MisclassificationStrategy;
import decisionTree.learning.RandomFeatureStrategy;

/**
 * Prueba de aprendizaje de árboles de decisión utilizando diferentes estrategias de selección de características (RandomFeatureStrategy y MisclassificationStrategy) para construir árboles a partir de un dataset etiquetado, y luego realizando predicciones con los árboles aprendidos.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Versión: 1.0
 * Nombre del fichero: TestApartado5.java
 */
public class TestApartado5 {

    public static void main(String[] args) {
        LabeledDataset<Person, Boolean> dataSet = buildLabeledDataSet();

        GreedyTreeLearner<Person, Boolean> randomLearner =
            new GreedyTreeLearner<>(new RandomFeatureStrategy<>(new Random(1)));
        DecisionTree<Person> randomTree = randomLearner.learn(dataSet);
        System.out.println("Random strategy:");
        System.out.println(randomTree.predict(dataSet));

        GreedyTreeLearner<Person, Boolean> misclassLearner =
            new GreedyTreeLearner<>(new MisclassificationStrategy<>());
        DecisionTree<Person> misclassTree = misclassLearner.learn(dataSet);
        System.out.println("Misclassification strategy:");
        System.out.println(misclassTree.predict(dataSet));
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
