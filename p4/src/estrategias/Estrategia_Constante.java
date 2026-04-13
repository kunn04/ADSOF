package estrategias;

/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: Estrategia_Constante.java
 */

import sensores.Sensor;

public class Estrategia_Constante implements Estrategia {
    private final double valorConstante;

    /** 
     * Constructor de la clase.
     * @param valorConstante valor constante a generar
     */
    public Estrategia_Constante(double valorConstante) {
        this.valorConstante = valorConstante;
    }

    @Override
    /**
     * Metodo publico de la clase.
     * @param sensor sensor para el cual generar el valor
     * @return valor constante
     */
    public double generarValor(Sensor sensor) {
        return valorConstante;
    }
}
