import java.util.*;
import java.time.*;

public abstract class Sensor {
    private String id;
    private double offset;
    private LocalDateTime fecha_Ul; 
    private double valor_Ul;   
    private LocalDate fecha_instalacion;
    private TipoSensor tipo;
    private static Map<TipoSensor, Integer> contadoresId = new HashMap<>();
    private List<Double> lecturas = new ArrayList<>();
    private Estrategia estrategia;

    public Sensor(double offset, LocalDate fecha_instalacion, TipoSensor tipo, Estrategia estrategia) {
        this.offset = offset;
        this.fecha_instalacion = fecha_instalacion;
        this.tipo = tipo;
        int nuevoContador = contadoresId.getOrDefault(tipo, 0) + 1;
        contadoresId.put(tipo, nuevoContador);
        this.id = tipo.name() + "-" + String.format("%04d", nuevoContador);

        if (estrategia == null) {
            switch (tipo) {
                case TEMP:
                    this.estrategia = new Estrategia_Rango(5); // cambios suaves
                    break;
                case HUM:
                    this.estrategia = new Estrategia_Media(10); // estable
                    break;
                case PRES:
                    this.estrategia = new Estrategia_Min_Max(0.05); // más variable
                    break;
            }
        }
        else {
            this.estrategia = estrategia;
        }
    }

    public String getId() {
        return id;
    }   

    public double getOffset() {
        return offset;
    }

    public LocalDateTime getFecha_Ul() {
        return fecha_Ul;
    }

    public double getValor_Ul() {
        return valor_Ul;
    }

    public LocalDate getFecha_instalacion() {
        return fecha_instalacion;
    }

    public TipoSensor getTipo() {
        return tipo;
    }

    public void lectura(double valor_simulado) {
        this.valor_Ul = valor_simulado - this.offset;
        this.fecha_Ul = LocalDateTime.now();
    }

    public List<Double> getLecturas() {
        return lecturas;
    }

    public void simularLectura() {
        double valor_simulado = estrategia.generarValor(this);
        this.lectura(valor_simulado);
        this.lecturas.add(this.valor_Ul);
    }

    public abstract double getMin();
    public abstract double getMax();
    public abstract String getUnidad();

    @Override
    public String toString() {
        String ret = this.id + " (desde: " + this.fecha_instalacion + "): Sensor ";
        switch (this.tipo) {
            case TEMP:
                ret += "Temperatura";
                break;
            case HUM:
                ret += "Humedad";
                break;
            case PRES:
                ret += "Presión";
                break;
        }
        ret += "(" + this.valor_Ul;
        ret += this.getUnidad();
        ret += ") última lectura: " + this.fecha_Ul;
        return ret;
    }
}
