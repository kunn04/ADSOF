package conversores;

/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: ConversorConcatenado.java
 */

import excepciones.ConversorIncompatibleException;

public class ConversorConcatenado implements Conversor {
    private Conversor conversor1;
    private Conversor conversor2;

    /** 
     * Constructor de la clase.
     * @param conversor1 primer conversor
     * @param conversor2 segundo conversor
     * @throws ConversorIncompatibleException si los conversores no son compatibles
     */
    public ConversorConcatenado(Conversor conversor1, Conversor conversor2) throws ConversorIncompatibleException {
        if (conversor1 == null || conversor2 == null) {
            throw new IllegalArgumentException("Los conversores no pueden ser null");
        }
        if (!conversor1.getUnidadDestino().equals(conversor2.getUnidadOrigen())) {
            throw new ConversorIncompatibleException(
                    "No se puede concatenar: "
                            + conversor1.getUnidadDestino()
                            + " no coincide con "
                            + conversor2.getUnidadOrigen());
        }
        this.conversor1 = conversor1;
        this.conversor2 = conversor2;
    }

    @Override
    /**
     * Convierte un valor de una unidad a otra.
     * @param valor valor a convertir
     * @return valor convertido
     */
    public double convertir(double valor) {
        double resultadoIntermedio = conversor1.convertir(valor);
        return conversor2.convertir(resultadoIntermedio);
    }

    @Override
    /** 
     * Obtiene la unidad de origen del conversor.
     * @return unidad de origen
     */
    public String getUnidadOrigen() {
        return conversor1.getUnidadOrigen();
    }

    @Override
    /** 
     * Obtiene la unidad de destino del conversor.
     * @return unidad de destino
     */
    public String getUnidadDestino() {
        return conversor2.getUnidadDestino();
    }
    
}
