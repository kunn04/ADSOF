import java.time.*;

public class TestApartado3 {
    public static void main(String[] args) {

        EstacionMeteorologica estacion =
                new EstacionMeteorologica("Madrid Centro", 40.4168, -3.7038);

        SensorTemperatura temp1 = new SensorTemperatura(
                0.5, LocalDate.of(2023,1,1), null);

        SensorTemperatura temp2 = new SensorTemperatura(
                0.5, LocalDate.of(2023,1,1), null);

        // Conversores
        Conversor c1 = new Conversor_C_K();

        // Añadir sensores
        estacion.addSensor(temp1, c1); // C → K
        estacion.addSensor(temp2);     // identidad

        // Inicialización
        estacion.lecturaSensor(20);

        // Simulación
        for (int i = 0; i < 3; i++) {
            estacion.simularLecturas();
        }

        // Salida
        System.out.println(estacion);
    }
}
