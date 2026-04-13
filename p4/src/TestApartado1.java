/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: TestApartado1.java
 */
import java.time.*;

import estacion.EstacionMeteorologica;
import excepciones.ConversorIncompatibleException;
import excepciones.SensorDuplicadoException;
import sensores.Sensor;
import sensores.SensorHumedad;
import sensores.SensorPresion;
import sensores.SensorTemperatura;

public class TestApartado1 {
    /**
     * Metodo publico de la clase.
     */
    public static void main(String[] args) {
        EstacionMeteorologica estacion = new EstacionMeteorologica("Madrid Centro", 40.4168, -3.7038);

        SensorTemperatura sensorTemp = new SensorTemperatura(0.5, LocalDate.of(2023, 1, 1), null);
        SensorHumedad sensorHum = new SensorHumedad(1.0, LocalDate.of(2023, 2, 1), null);
        SensorPresion sensorPres = new SensorPresion(2.0, LocalDate.of(2023, 3, 1), null);
        SensorPresion sensorPres2 = new SensorPresion(3.0, LocalDate.of(2023, 3, 1), null);


        System.out.println(estacion);
        try {
            estacion.addSensor(sensorTemp);
            estacion.addSensor(sensorHum);
            estacion.addSensor(sensorPres);
            estacion.addSensor(sensorPres2);
        } catch (SensorDuplicadoException | ConversorIncompatibleException e) {
            System.out.println(e.getMessage());
        }
        estacion.lecturaSensor(50);
        System.out.println("Sensores en la estación:");
        System.out.println(estacion.getSensores());
    }
}
