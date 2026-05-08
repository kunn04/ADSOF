package dataset;

/**
 * Esta clase representa un proveedor de etiquetas (LabelProvider) para un dataset, que define cómo obtener la etiqueta de un objeto dado.
 * que almacena objetos junto con sus características (features).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Versión: 1.0
 * Nombre del fichero: LabelProvider.java
 */
public interface LabelProvider<T, L> {
    L getLabel(T object);
}
