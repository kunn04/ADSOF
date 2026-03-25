import java.time.*;


public class SensorHumedad extends Sensor {
    public SensorHumedad(double offset, LocalDate fecha_instalacion, Estrategia estrategia) {
        super(offset, fecha_instalacion, TipoSensor.HUM, estrategia);
    }

    @Override
    public double getMin() {
        return 0.0;
    }

    @Override
    public double getMax() {
        return 100.0;
    }

    @Override
    public String getUnidad() {
        return "%";
    }
    
}
