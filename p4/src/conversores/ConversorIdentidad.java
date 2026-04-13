package conversores;

/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: ConversorIdentidad.java
 */

public class ConversorIdentidad implements Conversor {
    private String unidad;

    /** 
     * Constructor de la clase.
     * @param unidad unidad de origen y destino
     */
    public ConversorIdentidad(String unidad) {
        this.unidad = unidad;
    }

    @Override
    /** 
     * Convierte un valor de una unidad a otra.
     * @param valor valor a convertir
     * @return valor convertido
     */
    public double convertir(double valor) {
        return valor; // No se realiza ninguna conversión, se devuelve el mismo valor
    }

    @Override
    /** 
     * Obtiene la unidad de origen del conversor.
     * @return unidad de origen
     */
    public String getUnidadOrigen() {
        return unidad;
    }

    @Override
    /** 
     * Obtiene la unidad de destino del conversor.
     * @return unidad de destino
     */
    public String getUnidadDestino() {
        return unidad;
    }
    
}
