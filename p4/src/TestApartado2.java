import java.time.*;

public class TestApartado2 {
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

        estacion.addSensor(sensorTemp);
        estacion.addSensor(sensorHum);  
        estacion.addSensor(sensorPres);
        estacion.addSensor(sensorPres2);

        // Inicialización para evitar problemas en estrategias dependientes
        estacion.lecturaSensor(50);

        // Simulación
        for (int i = 0; i < 20; i++) {
            estacion.simularLecturas();
        }

        System.out.println("\nResultados:");

        for (Sensor s : estacion.getSensores()) {
            System.out.println(s);

            // Verificar fuera de rango
            for (double v : s.getLecturas()) {
                if (v < s.getMin() || v > s.getMax()) {
                    System.out.println("⚠ Valor fuera de rango: " + v);
                }
            }
        }
    }
}
