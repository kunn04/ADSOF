package conversores;

/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: Conversor.java
 */

public interface Conversor {
    /**
     * Convierte un valor de una unidad a otra.
     * @param valor valor a convertir
     * @return valor convertido
     */
    double convertir(double valor);

    /**
     * Obtiene la unidad de origen del conversor.
     * @return unidad de origen
     */
    String getUnidadOrigen();

    /**
     * Obtiene la unidad de destino del conversor.
     * @return unidad de destino
     */
    String getUnidadDestino();
}
