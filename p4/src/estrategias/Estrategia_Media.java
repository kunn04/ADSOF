package estrategias;

/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: Estrategia_Media.java
 */

import java.util.List;
import java.util.Random;

import sensores.Sensor;

public class Estrategia_Media implements Estrategia {
    private Random rand = new Random();
    private double porcentajeRango;

    /** 
     * Constructor de la clase.
     * @param porcentajeRango porcentaje del rango a utilizar
     */
    public Estrategia_Media(double porcentajeRango) {
        this.porcentajeRango = porcentajeRango;
    }

    @Override
    /**
     * Metodo publico de la clase.
     * @param sensor sensor para el cual generar el valor
     * @return valor generado
     */
    public double generarValor(Sensor sensor) {
        List<Double> lecturas = sensor.getLecturas();

        if (lecturas.isEmpty()) {
            return new Estrategia_Min_Max(0.1).generarValor(sensor);
        }

        double media = 0;
        for (double lectura : lecturas) {
            media += lectura;
        }
        media /= lecturas.size();
        double rango = media * porcentajeRango / 100.0;
        return media + (rand.nextDouble() * 2 - 1) * rango;
    }
}
