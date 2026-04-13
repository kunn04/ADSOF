package conversores;

/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: Conversor_hPa_Pa.java
 */

public class Conversor_hPa_Pa implements Conversor {
    @Override
    /** 
     * Convierte un valor de presión de hectopascales a pascal.
     * @param valor valor a convertir
     * @return valor convertido a pascal
     */
    public double convertir(double valor) {
        return valor * 100; // Conversión de hPa a Pa
    }

    @Override
    /** 
     * Obtiene la unidad de origen del conversor.
     * @return unidad de origen
     */
    public String getUnidadOrigen() {
        return "hPa";
    }

    @Override
    /** 
     * Obtiene la unidad de destino del conversor.
     * @return unidad de destino
     */
    public String getUnidadDestino() {
        return "Pa";
    }
    
}
