/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: TestApartado4.java
 */
import java.time.LocalDate;

import estacion.EstacionMeteorologica;
import excepciones.ConversorIncompatibleException;
import excepciones.SensorDuplicadoException;
import sensores.SensorHumedad;
import sensores.SensorTemperatura;

public class TestApartado4 {
    /**
     * Metodo publico de la clase.
     */
    public static void main(String[] args) {
        EstacionMeteorologica estacion = new EstacionMeteorologica("Madrid Centro", 40.4168, -3.7038);

        SensorTemperatura temp = new SensorTemperatura(0.0, LocalDate.of(2024, 1, 1), null);
        SensorHumedad hum = new SensorHumedad(0.0, LocalDate.of(2024, 1, 1), null);

        try {
            estacion.addSensor(temp);
            estacion.addSensor(hum);
        } catch (SensorDuplicadoException | ConversorIncompatibleException e) {
            System.out.println("Error anadiendo sensores: " + e.getMessage());
            return;
        }

        estacion.lecturaSensor(20.0);
        estacion.lecturaSensor(21.0);

        estacion.setPorcentajeCambioBrusco(20.0);
        estacion.calibrarSensor(temp.getId(), 0.0, 365);
        estacion.lecturaSensor(80.0);

        estacion.calibrarSensor(temp.getId(), 0.0, 1);
        estacion.calibrarSensor(hum.getId(), 0.0, 365);
        estacion.lecturaSensor(150.0);

        estacion.lecturaSensor(50.0);

        System.out.println("\nEstado de estacion con alertas:");
        System.out.println(estacion);

        estacion.calibrarSensor(hum.getId(), 0.0, 365);
        estacion.lecturaSensor(60.0);

        System.out.println("\nTras recalibrar humedad:");
        System.out.println(estacion);
    }
}
