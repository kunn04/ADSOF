package sensores;

/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: SensorTemperatura.java
 */

import java.time.LocalDate;
import estrategias.Estrategia;

public class SensorTemperatura extends Sensor {
    public enum UnidadTemperatura {
        C("°C"),
        F("°F"),
        K("K");

        private final String simbolo;

        UnidadTemperatura(String simbolo) {
            this.simbolo = simbolo;
        }

        /**
         * Metodo publico de la clase.
         * @return simbolo de la unidad de temperatura
         */
        public String getSimbolo() {
            return simbolo;
        }
    }

    private UnidadTemperatura unidad = UnidadTemperatura.C;

    /** 
     * Constructor de la clase.
     * @param offset offset del sensor
     * @param fechaInstalacion fecha de instalacion del sensor
     * @param estrategia estrategia de procesamiento del sensor
     */
    public SensorTemperatura(double offset, LocalDate fechaInstalacion, Estrategia estrategia) {
        super(offset, fechaInstalacion, TipoSensor.TEMP, estrategia);
    }

    @Override
    /** 
     * Retorna el valor mínimo que puede medir el sensor.
     * @return valor mínimo del sensor
     */
    public double getMin() {
        return -273.15;
    }

    @Override
    /** 
     * Retorna el valor máximo que puede medir el sensor.
     * @return valor máximo del sensor
     */
    public double getMax() {
        return 1000.0;
    }

    /**
     * Metodo publico de la clase.
     * @param unidad unidad de temperatura
     */
    public void setUnidad(UnidadTemperatura unidad) {
        if (unidad == null) {
            throw new IllegalArgumentException("La unidad no puede ser null");
        }
        this.unidad = unidad;
    }

    @Override
    /** 
     * Retorna la unidad de medida del sensor.
     * @return unidad de medida del sensor
     */
    public String getUnidad() {
        return unidad.getSimbolo();
    }
}
