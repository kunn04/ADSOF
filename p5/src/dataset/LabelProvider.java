package dataset;

/**
 * Asigna una etiqueta a un objeto del tipo parametrico.
 */
public interface LabelProvider<T, L> {
    L getLabel(T object);
}
