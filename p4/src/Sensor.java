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

    public Sensor(double offset, LocalDate fecha_instalacion, TipoSensor tipo) {
        this.offset = offset;
        this.fecha_instalacion = fecha_instalacion;
        this.tipo = tipo;
        int nuevoContador = contadoresId.getOrDefault(tipo, 0) + 1;
        contadoresId.put(tipo, nuevoContador);
        this.id = tipo.name() + "-" + String.format("%04d", nuevoContador);
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
