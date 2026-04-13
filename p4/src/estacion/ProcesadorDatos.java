package estacion;

/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: ProcesadorDatos.java
 */

import java.time.*;
import java.util.*;

import conversores.Conversor;
import excepciones.ConversorIncompatibleException;
import sensores.Sensor;

public class ProcesadorDatos {
    private Sensor sensor;
    private Conversor conversor;
    private List<Double> valores = new ArrayList<>();
    private List<LocalDateTime> fechas = new ArrayList<>();

    /** 
     * Constructor de la clase.
     * @param sensor sensor a procesar
     * @param conversor conversor a utilizar
     * @throws ConversorIncompatibleException si el conversor es incompatible
     */
    public ProcesadorDatos(Sensor sensor, Conversor conversor) throws ConversorIncompatibleException {
        if (sensor == null || conversor == null) {
            throw new IllegalArgumentException("Sensor y conversor no pueden ser null");
        }
        if (!sensor.getUnidad().equals(conversor.getUnidadOrigen())) {
            throw new ConversorIncompatibleException(
                    "El conversor empieza en "
                            + conversor.getUnidadOrigen()
                            + " pero el sensor mide en "
                            + sensor.getUnidad());
        }
        this.sensor = sensor;
        this.conversor = conversor;
    }

    /**
     * Obtiene la lista de valores procesados.
     * @return lista de valores procesados
     */
    public List<Double> getValores() {
        return valores;
    }

    /**
     * Procesa los datos del sensor.
     */
    public void procesarDatos() {
        double valor = sensor.getValor_Ul();
        double valorConvertido = conversor.convertir(valor);
        valores.add(valorConvertido);
        fechas.add(LocalDateTime.now());
    }

    /**
     * Obtiene el sensor.
     * @return sensor
     */
    public Sensor getSensor() {
        return sensor;
    }

    /**
     * Obtiene el conversor.
     * @return conversor
     */
    public Conversor getConversor() {
        return conversor;
    }

    /**
     * Obtiene el valor minimo procesado.
     * @return valor minimo procesado
     */
    public double getMinimo() {
        return Collections.min(valores);
    }

    /** 
     * Obtiene el valor máximo procesado.
     * @return valor máximo procesado
     */
    public double getMaximo() {
        return Collections.max(valores);
    }

    /**
     * Obtiene la media de los valores procesados.
     * @return media de los valores procesados
     */
    public double getMedia() {
        double suma = 0;

        for (double valor : valores) {
            suma += valor;
        }

        return suma / valores.size();
    }

    /**
     * Retorna una representacion en cadena de los datos procesados con el formato:
     * ID_SENSOR (UNIDAD_ORIGEN) con conversor a UNIDAD_DESTINO: [VALOR1, VALOR2, ...] -- MIN: MINIMO MAX: MAXIMO AVG: MEDIA
     * @return representación en cadena de los datos procesados
     */
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
