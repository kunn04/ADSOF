import java.time.*;
import java.util.*;

public class ProcesadorDatos {
    private Sensor sensor;
    private Conversor conversor;
    private List<Double> valores = new ArrayList<>();
    private List<LocalDateTime> fechas = new ArrayList<>();

    public ProcesadorDatos(Sensor sensor, Conversor conversor) {
        this.sensor = sensor;
        this.conversor = conversor;
    }

    public List<Double> getValores() {
        return valores;
    }

    public void procesarDatos() {
        double valor = sensor.getValor_Ul();
        double valorConvertido = conversor.convertir(valor);
        valores.add(valorConvertido);
        fechas.add(LocalDateTime.now());
    }

    public double getMinimo() {
        return Collections.min(valores);
    }

    public double getMaximo() {
        return Collections.max(valores);
    }

    public double getMedia() {
        double suma = 0;

        for (double valor : valores) {
            suma += valor;
        }

        return suma / valores.size();
    }

@Override
public String toString() {
    StringBuilder sb = new StringBuilder();

    String origen = conversor.getUnidadOrigen();
    String destino = conversor.getUnidadDestino();

    sb.append(sensor.getId())
      .append(" (").append(origen).append(")");

    // Solo mostrar conversión si es distinta
    if (!origen.equals(destino)) {
        sb.append(" con conversor a ").append(destino);
    }

    sb.append(": [");

    // valores formateados
    for (int i = 0; i < valores.size(); i++) {
        sb.append(String.format("%.2f", valores.get(i)));
        if (i < valores.size() - 1) {
            sb.append(", ");
        }
    }

    sb.append("]");

    // estadísticas
    sb.append(" -- MIN: ").append(String.format("%.2f", getMinimo()));
    sb.append(" MAX: ").append(String.format("%.2f", getMaximo()));
    sb.append(" AVG: ").append(String.format("%.2f", getMedia()));

    return sb.toString();
}
}
