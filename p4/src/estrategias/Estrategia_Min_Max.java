package estrategias;

/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: Estrategia_Min_Max.java
 */

import java.util.Random;

import sensores.Sensor;

public class Estrategia_Min_Max implements Estrategia {
    private Random rand = new Random();
    private double fueraRango;

    /** 
     * Constructor de la clase.
     * @param fueraRango porcentaje de valores fuera del rango
     */
    public Estrategia_Min_Max(double fueraRango) {
        this.fueraRango = fueraRango;
    }

    @Override
    /**
     * Metodo publico de la clase.
     * @param sensor sensor para el cual generar el valor
     * @return valor generado
     */
    public double generarValor(Sensor sensor) {
        double valorMin = sensor.getMin();
        double valorMax = sensor.getMax();

        if (rand.nextDouble() < fueraRango) {
            if (rand.nextBoolean()) {
                return valorMin - rand.nextDouble() * 10;
            }
            return valorMax + rand.nextDouble() * 10;
        }
        return valorMin + rand.nextDouble() * (valorMax - valorMin);
    }
}
