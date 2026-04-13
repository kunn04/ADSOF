package conversores;

/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: Conversor_Pa_mbar.java
 */

public class Conversor_Pa_mbar implements Conversor {
    @Override
    /** 
     * Convierte un valor de presión de pascal a milibar.
     * @param valor valor a convertir
     * @return valor convertido a milibar
     */
    public double convertir(double valor) {
        return valor / 100; // Conversión de Pa a mbar
    }

    @Override
    /**
     * Metodo publico de la clase.
     */
    public String getUnidadOrigen() {
        return "Pa";
    }

    @Override
    /**
     * Metodo publico de la clase.
     */
    public String getUnidadDestino() {
        return "mbar";
    }
    
}
