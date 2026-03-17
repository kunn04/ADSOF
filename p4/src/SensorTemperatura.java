import java.time.*;

public class SensorTemperatura extends Sensor {
    private String unidad = "°C";

    public SensorTemperatura(double offset, LocalDate fecha_instalacion) {
        super(offset, fecha_instalacion, TipoSensor.TEMP);
    }

    @Override
    public double getMin() {
        return -273.15;
    }

    @Override
    public double getMax() {
        return 1000.0;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    @Override
    public String getUnidad() {
        return unidad;
    }

    
}