import java.time.LocalDateTime;
import java.util.*;

public class EstacionMeteorologica {
    private String nombre;
    private double latitud;
    private double longitud;
    private List<Sensor> sensores = new ArrayList<>();
    private Map<Sensor, ProcesadorDatos> procesadores = new HashMap<>();

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

        Conversor c_identidad = new ConversorIdentidad(sensor.getUnidad());
        ProcesadorDatos pd = new ProcesadorDatos(sensor, c_identidad);
        this.procesadores.put(sensor, pd);
    }

    public void addSensor(Sensor sensor, Conversor conversor) {
        for(Sensor s: sensores) {
            if(s.getId().equals(sensor.getId())) {
                return;//hacerlo con excepcion
            }
        }
        this.sensores.add(sensor);

        ProcesadorDatos pd = new ProcesadorDatos(sensor, conversor);
        this.procesadores.put(sensor, pd);
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

    public void simularLecturas() {
        for(Sensor s: sensores) {
            s.simularLectura();
            procesadores.get(s).procesarDatos();
        }
    }

    //@Override
    //public String toString() {
    //    String ret = "Estación meteorológica: " + nombre + "\n";
    //    ret += "Ubicación: " + latitud + ", " + longitud + "\n";
    //    ret += "----------------------------------------------\n";
    //
    //    for (Sensor s : sensores) {
    //        ret += procesadores.get(s) + "\n";
    //    }
    //    return ret;
    //}
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Estación meteorológica: ").append(nombre).append("\n");
        sb.append("Ubicación: ").append(latitud).append(", ").append(longitud).append("\n");
        sb.append("----------------------------------------------\n");

        // Número de sensores
        sb.append("Sensores instalados: ").append(sensores.size()).append("\n");

        // Última lectura global
        LocalDateTime ultima = null;

        for (Sensor s : sensores) {
            if (s.getFecha_Ul() != null) {
                if (ultima == null || s.getFecha_Ul().isAfter(ultima)) {
                    ultima = s.getFecha_Ul();
                }
            }
        }

        sb.append("Última lectura: ").append(ultima).append("\n");

        // Procesadores
        for (Sensor s : sensores) {
            sb.append(procesadores.get(s)).append("\n");
        }

        return sb.toString();
    }
}
