package decisionTree.learning;

import java.util.List;
import dataset.LabeledDataset;

/**
 * Esta clase representa una estrategia de selección de características (FeatureSelectionStrategy) para el aprendizaje de árboles de decisión, que define cómo elegir la mejor característica para dividir un dataset durante el proceso de construcción del árbol.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Versión: 1.0
 * Nombre del fichero: FeatureSelectionStrategy.java
 */
public interface FeatureSelectionStrategy<T, L> {
    String chooseFeature(LabeledDataset<T, L> dataset, List<String> availableFeatures);
}
