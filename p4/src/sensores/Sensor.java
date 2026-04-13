package sensores; 

/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: Sensor.java
 */
import java.util.*;
import java.time.*;
import estrategias.Estrategia;
import estrategias.Estrategia_Media;
import estrategias.Estrategia_Min_Max;
import estrategias.Estrategia_Rango;

public abstract class Sensor {
    private static final int DIAS_VALIDEZ_CALIBRACION_POR_DEFECTO = 365;
    private static final Map<TipoSensor, Integer> CONTADORES_ID = new EnumMap<>(TipoSensor.class);

    private final String id;
    private double offset;
    private LocalDateTime fechaUltimaLectura;
    private double valorUltimaLectura;
    private final LocalDate fechaInstalacion;
    private final TipoSensor tipo;
    private final List<Double> lecturas;
    private Estrategia estrategia;

    private LocalDateTime fechaUltimaCalibracion;
    private int diasValidezCalibracion;
    private boolean correctamenteCalibrado;

    /** 
     * Constructor de la clase.
     * @param offset offset del sensor
     * @param fechaInstalacion fecha de instalacion del sensor
     * @param tipo tipo de sensor
     * @param estrategia estrategia de procesamiento del sensor
     */
    public Sensor(double offset, LocalDate fechaInstalacion, TipoSensor tipo, Estrategia estrategia) {
        if (fechaInstalacion == null) {
            throw new IllegalArgumentException("La fecha de instalacion no puede ser null");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de sensor no puede ser null");
        }

        this.offset = offset;
        this.fechaInstalacion = fechaInstalacion;
        this.tipo = tipo;
        int nuevoContador = CONTADORES_ID.getOrDefault(tipo, 0) + 1;
        CONTADORES_ID.put(tipo, nuevoContador);
        this.id = tipo.name() + "-" + String.format("%04d", nuevoContador);

        this.lecturas = new ArrayList<>();
        this.fechaUltimaCalibracion = LocalDateTime.now();
        this.diasValidezCalibracion = DIAS_VALIDEZ_CALIBRACION_POR_DEFECTO;
        this.correctamenteCalibrado = true;

        if (estrategia != null) {
            this.estrategia = estrategia;
        } else {
            this.estrategia = estrategiaPorDefecto(tipo);
        }
    }

    /** 
     * Retorna la estrategia por defecto para un tipo de sensor dado.
     * @param tipo tipo de sensor
     * @return estrategia por defecto
     */
    private Estrategia estrategiaPorDefecto(TipoSensor tipo) {
        switch (tipo) {
            case TEMP:
                return new Estrategia_Rango(5.0);
            case HUM:
                return new Estrategia_Media(10.0);
            case PRES:
                return new Estrategia_Min_Max(0.05);
            default:
                throw new IllegalStateException("Tipo de sensor no soportado: " + tipo);
        }
    }

    /**
     * Metodo publico de la clase.
     * @return ID del sensor
     */
    public String getId() {
        return id;
    }

    /**
     * Metodo publico de la clase.
     * @return offset del sensor
     */
    public double getOffset() {
        return offset;
    }

    /**
     * Metodo publico de la clase.
     * @return fecha de la última lectura
     */
    public LocalDateTime getFecha_ultima_lectura() {
        return fechaUltimaLectura;
    }

    /**
     * Metodo publico de la clase.
     * @return fecha de la última lectura
     */
    public LocalDateTime getFecha_Ul() {
        return fechaUltimaLectura;
    }

    /**
     * Metodo publico de la clase.
     * @return valor de la última lectura
     */
    public double getValor_ultima_lectura() {
        return valorUltimaLectura;
    }

    /**
     * Metodo publico de la clase.
     * @return valor de la última lectura
     */
    public double getValor_Ul() {
        return valorUltimaLectura;
    }

    /**
     * Metodo publico de la clase.
     * @return fecha de instalación del sensor
     */
    public LocalDate getFecha_instalacion() {
        return fechaInstalacion;
    }

    /**
     * Metodo publico de la clase.
     * @return tipo del sensor
     */
    public TipoSensor getTipo() {
        return tipo;
    }

    /**
     * Metodo publico de la clase.
     * @return lista de lecturas realizadas por el sensor
     */
    public List<Double> getLecturas() {
        return Collections.unmodifiableList(lecturas);
    }

    /** 
     * Metodo publico de la clase.
     * @param valorMedido valor medido por el sensor
     */
    public void lectura(double valorMedido) {
        double valorReal = valorMedido - this.offset;
        LocalDateTime ahora = LocalDateTime.now();

        if (ahora.isAfter(fechaUltimaCalibracion.plusDays(diasValidezCalibracion))) {
            this.correctamenteCalibrado = false;
        }
        if (valorReal < getMin() || valorReal > getMax()) {
            this.correctamenteCalibrado = false;
        }

        this.valorUltimaLectura = valorReal;
        this.fechaUltimaLectura = ahora;
        this.lecturas.add(valorReal);
    }

    /** 
     * Simula una lectura del sensor.
     */
    public void simularLectura() {
        if (estrategia == null) {
            throw new IllegalStateException("No hay estrategia configurada para simular lecturas");
        }
        double valorSimulado = estrategia.generarValor(this);
        this.lectura(valorSimulado);
    }

    /** 
     * Establece la estrategia de procesamiento del sensor.
     * @param estrategia estrategia de procesamiento
     */
    public void setEstrategia(Estrategia estrategia) {
        if (estrategia == null) {
            throw new IllegalArgumentException("La estrategia no puede ser null");
        }
        this.estrategia = estrategia;
    }

    /**
     * Metodo publico de la clase.
     * @return estrategia de procesamiento del sensor
     */
    public Estrategia getEstrategia() {
        return estrategia;
    }

    /** 
     * Calibra el sensor con un nuevo offset y días de validez.
     * @param nuevoOffset nuevo offset del sensor
     * @param nuevosDiasValidez nuevos días de validez de la calibración
     */
    public void calibrar(double nuevoOffset, int nuevosDiasValidez) {
        if (nuevosDiasValidez <= 0) {
            throw new IllegalArgumentException("Los dias de validez deben ser > 0");
        }

        this.offset = nuevoOffset;
        this.diasValidezCalibracion = nuevosDiasValidez;
        this.fechaUltimaCalibracion = LocalDateTime.now();
        this.correctamenteCalibrado = true;
    }

    /** 
     * Retorna si el sensor está correctamente calibrado.
     * @return true si el sensor está correctamente calibrado, false en caso contrario
     */
    public boolean isCorrectamenteCalibrado() {
        if (correctamenteCalibrado &&
            LocalDateTime.now().isAfter(fechaUltimaCalibracion.plusDays(diasValidezCalibracion))) {
            correctamenteCalibrado = false;
        }
        return correctamenteCalibrado;
    }

    /** 
     * Retorna la fecha de la última calibración.
     * @return fecha de la última calibración
     */
    public LocalDateTime getFechaUltimaCalibracion() {
        return fechaUltimaCalibracion;
    }

    /**
     * Metodo publico de la clase.
     * @return días de validez de la calibración
     */
    public int getDiasValidezCalibracion() {
        return diasValidezCalibracion;
    }

    /** 
     * Retorna la fecha de caducidad de la calibración.
     * @return fecha de caducidad de la calibración
     */
    public LocalDateTime getFechaCaducidadCalibracion() {
        return fechaUltimaCalibracion.plusDays(diasValidezCalibracion);
    }

    /** 
     * Retorna si la calibración del sensor está caducada.
     * @return true si la calibración está caducada, false en caso contrario
     */
    public boolean isCalibracionCaducada() {
        return LocalDateTime.now().isAfter(getFechaCaducidadCalibracion());
    }

    /** 
     * Retorna el valor mínimo que puede medir el sensor.
     * @return valor mínimo del sensor
     */
    public abstract double getMin();

    /** 
     * Retorna el valor máximo que puede medir el sensor.
     * @return valor máximo del sensor
     */
    public abstract double getMax();

    /** 
     * Retorna la unidad de medida del sensor.
     * @return unidad de medida del sensor
     */
    public abstract String getUnidad();

    @Override
    /** 
     * Retorna una representación en cadena del sensor.
     * @return representación en cadena del sensor
     */
    public String toString() {
        String tipoTexto;
        switch (tipo) {
            case TEMP:
                tipoTexto = "Temperatura";
                break;
            case HUM:
                tipoTexto = "Humedad";
                break;
            case PRES:
                tipoTexto = "Presión";
                break;
            default:
                tipoTexto = "Desconocido";
        }

        String lecturaTexto;
        if (fechaUltimaLectura == null) {
            lecturaTexto = "sin lecturas todavía";
        } else {
            lecturaTexto = valorUltimaLectura + getUnidad();
        }

        String fechaLecturaTexto;
        if (fechaUltimaLectura == null) {
            fechaLecturaTexto = "-";
        } else {
            fechaLecturaTexto = fechaUltimaLectura.toString();
        }

        return id + " (desde: " + fechaInstalacion + "): Sensor "
                + tipoTexto
                + " (" + lecturaTexto + ")"
                + " última lectura: " + fechaLecturaTexto;
    }
}
