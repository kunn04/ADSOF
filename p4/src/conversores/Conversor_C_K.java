package conversores;

/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: Conversor_C_K.java
 */

public class Conversor_C_K implements Conversor {
    @Override
    /** 
     * Convierte un valor de temperatura de Celsius a Kelvin.
     * @param valor valor a convertir
     * @return valor convertido a Kelvin
     */
    public double convertir(double valor) {
        return valor + 273.15; // Conversión de Celsius a Kelvin
    }

    @Override
    /** 
     * Obtiene la unidad de origen del conversor.
     * @return unidad de origen
     */
    public String getUnidadOrigen() {
        return "°C";
    }

    @Override
    /** 
     * Obtiene la unidad de destino del conversor.
     * @return unidad de destino
     */
    public String getUnidadDestino() {
        return "°K";
    }
    
}
