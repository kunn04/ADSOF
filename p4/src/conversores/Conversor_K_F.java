package conversores;

/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: Conversor_K_F.java
 */

public class Conversor_K_F implements Conversor {
    @Override
    /** 
     * Convierte un valor de temperatura de Kelvin a Fahrenheit.
     * @param valor valor a convertir
     * @return valor convertido a Fahrenheit
     */
    public double convertir(double valor) {
        return (valor - 273.15) * 9/5 + 32; // Conversión de Kelvin a Fahrenheit
    }

    @Override
    /** 
     * Obtiene la unidad de origen del conversor.
     * @return unidad de origen
     */
    public String getUnidadOrigen() {
        return "°K";
    }

    @Override
    /** 
     * Obtiene la unidad de destino del conversor.
     * @return unidad de destino
     */
    public String getUnidadDestino() {
        return "°F";
    }
    
}
