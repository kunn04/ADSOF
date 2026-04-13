/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: TestApartado2Extensibilidad.java
 */
import java.time.LocalDate;

import estrategias.Estrategia_Constante;
import estrategias.Estrategia_Media;
import estrategias.Estrategia_Rango;
import sensores.SensorHumedad;
import sensores.SensorTemperatura;

public class TestApartado2Extensibilidad {
    /**
     * Metodo publico de la clase.
     */
    public static void main(String[] args) {
        SensorTemperatura sensorTemp = new SensorTemperatura(
                0.0,
                LocalDate.of(2024, 1, 1),
                new Estrategia_Constante(25.0));

        for (int i = 0; i < 5; i++) {
            sensorTemp.simularLectura();
        }
        System.out.println("Lecturas con Estrategia_Constante:");
        System.out.println(sensorTemp.getLecturas());

        sensorTemp.setEstrategia(new Estrategia_Rango(10.0));
        sensorTemp.simularLectura();
        sensorTemp.simularLectura();
        System.out.println("Lecturas tras cambiar a Estrategia_Rango:");
        System.out.println(sensorTemp.getLecturas());

        SensorHumedad sensorHum = new SensorHumedad(
                0.0,
                LocalDate.of(2024, 2, 1),
                new Estrategia_Media(5.0));

        sensorHum.lectura(40.0);
        sensorHum.lectura(50.0);
        sensorHum.simularLectura();
        System.out.println("Lecturas con Estrategia_Media:");
        System.out.println(sensorHum.getLecturas());
    }
}
