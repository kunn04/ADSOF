/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: TestApartado3.java
 */
import java.time.*;

import conversores.Conversor;
import conversores.Conversor_C_K;
import estacion.EstacionMeteorologica;
import excepciones.ConversorIncompatibleException;
import excepciones.SensorDuplicadoException;
import excepciones.UnidadNoPermitidaException;
import sensores.SensorTemperatura;

public class TestApartado3 {
    /**
     * Metodo publico de la clase.
     */
    public static void main(String[] args) {

        EstacionMeteorologica estacion =
                new EstacionMeteorologica("Madrid Centro", 40.4168, -3.7038);

        SensorTemperatura temp1 = new SensorTemperatura(
                0.5, LocalDate.of(2023,1,1), null);

        SensorTemperatura temp2 = new SensorTemperatura(
                0.5, LocalDate.of(2023,1,1), null);

        Conversor c1 = new Conversor_C_K();

        try {
            estacion.addSensor(temp1, c1); // C -> K
            estacion.addSensor(temp2);     // identidad
        } catch (SensorDuplicadoException | ConversorIncompatibleException | UnidadNoPermitidaException e) {
            System.out.println(e.getMessage());
        }

        estacion.lecturaSensor(20);

        for (int i = 0; i < 3; i++) {
            estacion.simularLecturas();
        }

        System.out.println(estacion);
    }
}
