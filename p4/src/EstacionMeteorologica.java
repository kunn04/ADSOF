import java.util.*;

public class EstacionMeteorologica {
    private String nombre;
    private double latitud;
    private double longitud;
    private List<Sensor> sensores = new ArrayList<>();

    public EstacionMeteorologica(String nombre, double latitud, double longitud) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void addSensor(Sensor sensor) {
        for(Sensor s: sensores) {
            if(s.getId().equals(sensor.getId())) {
                return;//hacerlo con excepcion
            }
        }
        this.sensores.add(sensor);
    }

    public List<Sensor> getSensores() {
        return sensores;
    }

    public Sensor getSensorId(String id) {
        for(Sensor s: sensores) {
            if(s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    public List<Sensor> getSensoresTipo(TipoSensor tipo) {
        List<Sensor> sensoresTipo = new ArrayList<>();
        for(Sensor s: sensores) {
            if(s.getTipo() == tipo) {
                sensoresTipo.add(s);
            }
        }
        return sensoresTipo;
    }

    public void lecturaSensor(double valor_simulado) {
        for(Sensor s: sensores) {
            s.lectura(valor_simulado);
        }
    }

    @Override
    public String toString() {
        return "Estación Meteorológica: " + this.nombre + "\nUbicación: " + this.latitud + ", " + this.longitud;
    }    
}
