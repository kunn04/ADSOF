package sensores;

/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: SensorHumedad.java
 */

import java.time.LocalDate;
import estrategias.Estrategia;

public class SensorHumedad extends Sensor {

    /** 
     * Constructor de la clase.
     * @param offset offset del sensor
     * @param fechaInstalacion fecha de instalacion del sensor
     * @param estrategia estrategia de procesamiento del sensor
     */
    public SensorHumedad(double offset, LocalDate fechaInstalacion, Estrategia estrategia) {
        super(offset, fechaInstalacion, TipoSensor.HUM, estrategia);
    }

    @Override
    /** 
     * Retorna el valor mínimo que puede medir el sensor.
     * @return valor mínimo del sensor
     */
    public double getMin() {
        return 0.0;
    }

    @Override
    /** 
     * Retorna el valor máximo que puede medir el sensor.
     * @return valor máximo del sensor
     */
    public double getMax() {
        return 100.0;
    }

    @Override
    /** 
     * Retorna la unidad de medida del sensor.
     * @return unidad de medida del sensor
     */
    public String getUnidad() {
        return "%";
    }
}
