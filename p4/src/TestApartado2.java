/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: TestApartado2.java
 */
import java.time.*;

import estacion.EstacionMeteorologica;
import excepciones.ConversorIncompatibleException;
import estrategias.Estrategia_Media;
import estrategias.Estrategia_Min_Max;
import estrategias.Estrategia_Rango;
import excepciones.SensorDuplicadoException;
import sensores.Sensor;
import sensores.SensorHumedad;
import sensores.SensorPresion;
import sensores.SensorTemperatura;

public class TestApartado2 {
    /**
     * Metodo publico de la clase.
     */
    public static void main(String[] args) {

        EstacionMeteorologica estacion = new EstacionMeteorologica(
                "Madrid Centro", 40.4168, -3.7038);

        SensorTemperatura sensorTemp = new SensorTemperatura(
                0.5, LocalDate.of(2023, 1, 1),
                new Estrategia_Rango(5));

        SensorHumedad sensorHum = new SensorHumedad(
                1.0, LocalDate.of(2023, 2, 1),
                new Estrategia_Media(10));

        SensorPresion sensorPres = new SensorPresion(
                2.0, LocalDate.of(2023, 3, 1),
                null);

        SensorPresion sensorPres2 = new SensorPresion(
                3.0, LocalDate.of(2023, 3, 1),
                new Estrategia_Min_Max(0.2));

        try {
            estacion.addSensor(sensorTemp);
            estacion.addSensor(sensorHum);
            estacion.addSensor(sensorPres);
            estacion.addSensor(sensorPres2);
        } catch (SensorDuplicadoException | ConversorIncompatibleException e) {
            System.out.println(e.getMessage());
        }

        estacion.lecturaSensor(50);

        for (int i = 0; i < 20; i++) {
            estacion.simularLecturas();
        }

        System.out.println("\nResultados:");

        for (Sensor s : estacion.getSensores()) {
            System.out.println(s);

            for (double v : s.getLecturas()) {
                if (v < s.getMin() || v > s.getMax()) {
                    System.out.println("Valor fuera de rango: " + v);
                }
            }
        }
    }
}
