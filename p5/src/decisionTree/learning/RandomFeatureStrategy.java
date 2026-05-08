package decisionTree.learning;

import java.util.List;
import java.util.Random;
import dataset.LabeledDataset;

/**
 * Esta clase representa una estrategia de selección de características (RandomFeatureStrategy) para el aprendizaje de árboles de decisión, que elige una característica al azar de la lista de características disponibles para dividir un dataset durante el proceso de construcción del árbol.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Versión: 1.0
 * Nombre del fichero: RandomFeatureStrategy.java
 */
public class RandomFeatureStrategy<T, L> implements FeatureSelectionStrategy<T, L> {

    private final Random random;

    public RandomFeatureStrategy() {
        this(new Random());
    }

    public RandomFeatureStrategy(Random random) {
        this.random = random;
    }

    @Override
    public String chooseFeature(LabeledDataset<T, L> dataset, List<String> availableFeatures) {
        if (availableFeatures == null || availableFeatures.isEmpty()) return null;
        return availableFeatures.get(random.nextInt(availableFeatures.size()));
    }
}
