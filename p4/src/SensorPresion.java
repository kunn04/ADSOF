import java.time.*;

public class SensorPresion extends Sensor {
    public SensorPresion(double offset, LocalDate fecha_instalacion, Estrategia estrategia) {
        super(offset, fecha_instalacion, TipoSensor.PRES, estrategia);
    }

    @Override
    public double getMin() {
        return 300.0;
    }

    @Override
    public double getMax() {
        return 1100.0;
    }

    @Override
    public String getUnidad() {
        return "hPa";
    }
    
}
