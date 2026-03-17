import java.time.*;

public class TestApartado1 {
    public static void main(String[] args) {
        EstacionMeteorologica estacion = new EstacionMeteorologica("Madrid Centro", 40.4168, -3.7038);

        SensorTemperatura sensorTemp = new SensorTemperatura(0.5, LocalDate.of(2023, 1, 1));
        SensorHumedad sensorHum = new SensorHumedad(1.0, LocalDate.of(2023, 2, 1));
        SensorPresion sensorPres = new SensorPresion(2.0, LocalDate.of(2023, 3, 1));
        SensorPresion sensorPres2 = new SensorPresion(3.0, LocalDate.of(2023, 3, 1));


        System.out.println(estacion);

        estacion.addSensor(sensorTemp);
        estacion.addSensor(sensorHum);
        estacion.addSensor(sensorPres);
        estacion.addSensor(sensorPres2);
        estacion.lecturaSensor(50);
        System.out.println("Sensores en la estación:");
        for(Sensor s: estacion.getSensores()) {
            System.out.println(s);  
        }
    }
}
