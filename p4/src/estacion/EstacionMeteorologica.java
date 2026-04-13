package estacion;

/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: EstacionMeteorologica.java
 */

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.*;

import conversores.Conversor;
import conversores.ConversorIdentidad;
import excepciones.CambioBruscoException;
import excepciones.ConversorIncompatibleException;
import excepciones.LecturaFueraRangoException;
import excepciones.SensorDuplicadoException;
import excepciones.SensorSinCalibrarException;
import excepciones.UnidadNoPermitidaException;
import sensores.Sensor;
import sensores.TipoSensor;

public class EstacionMeteorologica {
    private String nombre;
    private double latitud;
    private double longitud;
    private List<Sensor> sensores = new ArrayList<>();
    private Map<Sensor, ProcesadorDatos> procesadores = new HashMap<>();
    private List<Alerta> historialAlertas = new ArrayList<>();
    private double porcentajeCambioBrusco = 50.0;

    /** 
     * Constructor de la clase.
     * @param nombre nombre de la estación
     * @param latitud latitud de la estación
     * @param longitud longitud de la estación
     */
    public EstacionMeteorologica(String nombre, double latitud, double longitud) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    /** 
     * Obtiene el nombre de la estación.
     * @return nombre de la estación
     */
    public String getNombre() {
        return nombre;
    }

    /** 
     * Obtiene la latitud de la estación.
     * @return latitud de la estación
     */
    public double getLatitud() {
        return latitud;
    }

    /** 
     * Obtiene la longitud de la estación.
     * @return longitud de la estación
     */
    public double getLongitud() {
        return longitud;
    }

    /** 
     * Añade un sensor a la estación.
     * @param sensor sensor a añadir
     * @throws SensorDuplicadoException si el sensor ya está añadido
     * @throws ConversorIncompatibleException si el conversor es incompatible
     */
    public void addSensor(Sensor sensor) throws SensorDuplicadoException, ConversorIncompatibleException {
        validarSensorNoDuplicado(sensor);
        this.sensores.add(sensor);

        Conversor c_identidad = new ConversorIdentidad(sensor.getUnidad());
        ProcesadorDatos pd = new ProcesadorDatos(sensor, c_identidad);
        this.procesadores.put(sensor, pd);
    }

    /** 
     * Añade un sensor a la estación con un conversor específico.
     * @param sensor sensor a añadir
     * @param conversor conversor a utilizar
     * @throws SensorDuplicadoException si el sensor ya está añadido
     * @throws ConversorIncompatibleException si el conversor es incompatible
     * @throws UnidadNoPermitidaException si la unidad de destino no es permitida
     */
    public void addSensor(Sensor sensor, Conversor conversor)
            throws SensorDuplicadoException, ConversorIncompatibleException, UnidadNoPermitidaException {
        validarSensorNoDuplicado(sensor);
        validarUnidadDestinoPermitida(sensor, conversor);
        this.sensores.add(sensor);

        ProcesadorDatos pd = new ProcesadorDatos(sensor, conversor);
        this.procesadores.put(sensor, pd);
    }

    /** 
     * Valida que un sensor no esté duplicado.
     * @param sensor sensor a validar
     * @throws SensorDuplicadoException si el sensor ya está añadido
     */
    private void validarSensorNoDuplicado(Sensor sensor) throws SensorDuplicadoException {
        if (sensor == null) {
            throw new IllegalArgumentException("El sensor no puede ser null");
        }
        for (Sensor s : sensores) {
            if (s.getId().equals(sensor.getId())) {
                throw new SensorDuplicadoException(sensor);
            }
        }
    }

    /** 
     * Valida que la unidad de destino sea permitida.
     * @param sensor sensor a validar
     * @param conversor conversor a validar
     * @throws UnidadNoPermitidaException si la unidad de destino no es permitida
     */
    private void validarUnidadDestinoPermitida(Sensor sensor, Conversor conversor) throws UnidadNoPermitidaException {
        if (conversor == null) {
            throw new IllegalArgumentException("El conversor no puede ser null");
        }

        String unidadDestino = conversor.getUnidadDestino();
        TipoSensor tipo = sensor.getTipo();

        boolean permitida;
        switch (tipo) {
            case TEMP:
                permitida = unidadDestino.equals("°C")
                        || unidadDestino.equals("°K")
                        || unidadDestino.equals("°F");
                break;
            case PRES:
                permitida = unidadDestino.equals("hPa")
                        || unidadDestino.equals("Pa")
                        || unidadDestino.equals("mbar");
                break;
            case HUM:
                permitida = unidadDestino.equals("%");
                break;
            default:
                permitida = false;
        }

        if (!permitida) {
            throw new UnidadNoPermitidaException(
                    "Unidad destino no permitida para " + tipo + ": " + unidadDestino);
        }
    }

    /** 
     * Obtiene la lista de sensores de la estación.
     * @return lista de sensores de la estación
     */
    public List<Sensor> getSensores() {
        return Collections.unmodifiableList(sensores);
    }

    /** 
     * Obtiene un sensor por su ID.
     * @param id ID del sensor
     * @return sensor con el ID especificado, o null si no se encuentra
     */
    public Sensor getSensorId(String id) {
        for(Sensor s: sensores) {
            if(s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    /** 
     * Obtiene un sensor por su ID.
     * @param id ID del sensor
     * @return sensor con el ID especificado, o null si no se encuentra
     */
    public Sensor getSensorPorId(String id) {
        return getSensorId(id);
    }

    /** 
     * Obtiene la lista de sensores de un tipo específico.
     * @param tipo tipo de sensor
     * @return lista de sensores del tipo especificado
     */
    public List<Sensor> getSensoresTipo(TipoSensor tipo) {
        List<Sensor> sensoresTipo = new ArrayList<>();
        for(Sensor s: sensores) {
            if(s.getTipo() == tipo) {
                sensoresTipo.add(s);
            }
        }
        return Collections.unmodifiableList(sensoresTipo);
    }

    /** 
     * Realiza una lectura de todos los sensores.
     * @param valor_simulado valor simulado para la lectura
     */
    public void lecturaSensor(double valor_simulado) {
        for (Sensor s : sensores) {
            try {
                medirYProcesar(s, valor_simulado, false);
            } catch (CambioBruscoException | SensorSinCalibrarException | LecturaFueraRangoException e) {
                registrarAlerta(s, e);
            }
        }
    }

    /** 
     * Realiza una lectura puntual de todos los sensores.
     * @param valorSimulado valor simulado para la lectura
     */
    public void lecturaPuntual(double valorSimulado) {
        lecturaSensor(valorSimulado);
    }

    /** 
     * Realiza una lectura periódica de todos los sensores.
     * @param valorSimulado valor simulado para la lectura
     * @param periodo periodo entre lecturas
     * @param maxLecturas número máximo de lecturas
     * @throws IllegalArgumentException si el periodo es null, negativo o cero, o si el número máximo de lecturas es menor o igual a cero
     */
    public void lecturaPeriodica(double valorSimulado, Duration periodo, int maxLecturas) {
        if (periodo == null || periodo.isNegative() || periodo.isZero()) {
            throw new IllegalArgumentException("El periodo debe ser mayor que 0");
        }
        if (maxLecturas <= 0) {
            throw new IllegalArgumentException("El maximo de lecturas debe ser mayor que 0");
        }

        for (int i = 0; i < maxLecturas; i++) {
            lecturaPuntual(valorSimulado);
        }
    }

    /** 
     * Simula las lecturas de todos los sensores.
     */
    public void simularLecturas() {
        for (Sensor s : sensores) {
            try {
                medirYProcesar(s, null, true);
            } catch (CambioBruscoException | SensorSinCalibrarException | LecturaFueraRangoException e) {
                registrarAlerta(s, e);
            }
        }
    }

    /** 
     * Mide y procesa los datos de un sensor.
     * @param sensor sensor a medir y procesar
     * @param valorSimulado valor simulado para la lectura
     * @param usandoEstrategia indica si se usa una estrategia de simulación
     * @throws SensorSinCalibrarException si el sensor no está correctamente calibrado
     * @throws LecturaFueraRangoException si la lectura está fuera del rango permitido
     * @throws CambioBruscoException si hay un cambio brusco en los datos
     */
    private void medirYProcesar(Sensor sensor, Double valorSimulado, boolean usandoEstrategia)
            throws SensorSinCalibrarException, LecturaFueraRangoException, CambioBruscoException {
        if (!sensor.isCorrectamenteCalibrado()) {
            if (sensor.isCalibracionCaducada()) {
            LocalDateTime caducidad = sensor.getFechaCaducidadCalibracion();
            throw new SensorSinCalibrarException(
                "Sensor " + sensor.getId() + " sin calibrar (calibracion caducada desde " + caducidad + ")");
            }
            throw new SensorSinCalibrarException(
                "Sensor " + sensor.getId() + " sin calibrar (lectura fuera de rango previa)");
        }

        double valorAnterior = sensor.getValor_ultima_lectura();
        boolean tieneAnterior = sensor.getFecha_ultima_lectura() != null;

        if (usandoEstrategia) {
            sensor.simularLectura();
        } else {
            sensor.lectura(valorSimulado);
        }

        double valorActual = sensor.getValor_ultima_lectura();
        if (valorActual < sensor.getMin() || valorActual > sensor.getMax()) {
            throw new LecturaFueraRangoException(
                    "Lectura fuera de rango en "
                            + sensor.getId()
                            + ": "
                            + valorActual
                            + sensor.getUnidad());
        }

        ProcesadorDatos procesador = procesadores.get(sensor);
        if (procesador != null) {
            procesador.procesarDatos();
        }

        if (tieneAnterior && esCambioBrusco(valorAnterior, valorActual)) {
            throw new CambioBruscoException(
                    "Cambio brusco en "
                            + sensor.getId()
                            + ": "
                            + valorActual
                            + sensor.getUnidad()
                            + " (anterior: "
                            + valorAnterior
                            + sensor.getUnidad()
                            + ")");
        }
    }

    /** 
     * Determina si hay un cambio brusco en los datos de un sensor.
     * @param anterior valor anterior
     * @param actual valor actual
     * @return true si hay un cambio brusco, false en caso contrario
     */
    private boolean esCambioBrusco(double anterior, double actual) {
        if (anterior == 0.0) {
            return actual != 0.0;
        }
        double cambioPorcentual = Math.abs((actual - anterior) / anterior) * 100.0;
        return cambioPorcentual > porcentajeCambioBrusco;
    }

    /** 
     * Registra una alerta para un sensor.
     * @param sensor sensor para el que se registra la alerta
     * @param e excepción que genera la alerta
     */
    private void registrarAlerta(Sensor sensor, Exception e) {
        String tipo;
        if (e instanceof CambioBruscoException) {
            tipo = "CAMBIO_BRUSCO";
        } else if (e instanceof LecturaFueraRangoException) {
            tipo = "LECTURA_FUERA_RANGO";
        } else {
            tipo = "SENSOR_SIN_CALIBRAR";
        }

        if (existeAlertaActiva(sensor.getId(), tipo)) {
            return;
        }

        historialAlertas.add(new Alerta(LocalDateTime.now(), sensor.getId(), tipo, e.getMessage()));
    }

    /** 
     * Determina si existe una alerta activa para un sensor y tipo específicos.
     * @param sensorId id del sensor
     * @param tipo tipo de alerta
     * @return true si existe una alerta activa, false en caso contrario
     */
    private boolean existeAlertaActiva(String sensorId, String tipo) {
        for (Alerta alerta : historialAlertas) {
            if (alerta.isActiva()
                    && alerta.getSensorId().equals(sensorId)
                    && alerta.getTipo().equals(tipo)) {
                return true;
            }
        }
        return false;
    }

    /** 
     * Obtiene la lista de alertas activas.
     * @return lista de alertas activas
     */
    public List<Alerta> getAlertasActivas() {
        List<Alerta> alertasActivas = new ArrayList<>();
        for (Alerta alerta : historialAlertas) {
            if (alerta.isActiva()) {
                alertasActivas.add(alerta);
            }
        }
        return Collections.unmodifiableList(alertasActivas);
    }

    /** 
     * Obtiene el historial de alertas.
     * @return historial de alertas
     */
    public List<Alerta> getHistorialAlertas() {
        return Collections.unmodifiableList(historialAlertas);
    }

    /** 
     * Establece el porcentaje de cambio brusco.
     * @param porcentajeCambioBrusco porcentaje de cambio brusco
     * @throws IllegalArgumentException si el porcentaje es menor o igual a cero
     */
    public void setPorcentajeCambioBrusco(double porcentajeCambioBrusco) {
        if (porcentajeCambioBrusco <= 0) {
            throw new IllegalArgumentException("El porcentaje de cambio brusco debe ser mayor que 0");
        }
        this.porcentajeCambioBrusco = porcentajeCambioBrusco;
    }

    /** 
     * Calibra un sensor.
     * @param idSensor id del sensor a calibrar
     * @param nuevoOffset nuevo offset para el sensor
     * @param nuevosDiasValidez nuevos días de validez para el sensor
     * @throws IllegalArgumentException si no existe un sensor con el id proporcionado
     */
    public void calibrarSensor(String idSensor, double nuevoOffset, int nuevosDiasValidez) {
        Sensor sensor = getSensorPorId(idSensor);
        if (sensor == null) {
            throw new IllegalArgumentException("No existe sensor con id: " + idSensor);
        }

        sensor.calibrar(nuevoOffset, nuevosDiasValidez);

        for (Alerta alerta : historialAlertas) {
            if (alerta.isActiva() && alerta.getSensorId().equals(idSensor)) {
                alerta.desactivar();
            }
        }
    }

    /**
     * Obtiene la fecha de la última lectura global.
     * @return fecha de la última lectura global
     */
    public LocalDateTime getUltimaLecturaGlobal() {
        LocalDateTime ultima = null;
        for (Sensor s : sensores) {
            if (s.getFecha_ultima_lectura() != null) {
                if (ultima == null || s.getFecha_ultima_lectura().isAfter(ultima)) {
                    ultima = s.getFecha_ultima_lectura();
                }
            }
        }
        return ultima;
    }

    /** 
     * Obtiene el resumen de los procesadores de datos.
     * @return lista con el resumen de los procesadores
     */
    public List<String> getResumenProcesadores() {
        List<String> lineas = new ArrayList<>();
        for (Sensor s : sensores) {
            ProcesadorDatos procesador = procesadores.get(s);
            if (procesador != null) {
                lineas.add(procesador.toString());
            }
        }
        return Collections.unmodifiableList(lineas);
    }

    /**
     * Obtiene el resumen de las alertas activas.
     * @return lista con el resumen de las alertas activas
     */
    public List<String> getResumenAlertasActivas() {
        List<String> lineas = new ArrayList<>();
        for (Alerta alerta : getAlertasActivas()) {
            lineas.add(alerta.toString());
        }
        return Collections.unmodifiableList(lineas);
    }

    @Override
    /** 
     * Retorna una representación en cadena de la estación meteorológica.
     * @return representación en cadena de la estación meteorológica
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Estación meteorológica: ").append(nombre).append("\n");
        sb.append("Ubicación: ").append(latitud).append(", ").append(longitud).append("\n");
        sb.append("----------------------------------------------\n");

        // Número de sensores
        sb.append("Sensores instalados: ").append(sensores.size()).append("\n");

        // Última lectura global
        LocalDateTime ultima = getUltimaLecturaGlobal();

        if (ultima == null) {
            sb.append("Última lectura: -\n");
        } else {
            sb.append("Última lectura: ").append(ultima).append("\n");
        }

        // Procesadores
        for (Sensor s : sensores) {
            sb.append(procesadores.get(s)).append("\n");
        }

        sb.append("\nAlertas activas: ").append(getAlertasActivas().size()).append("\n");
        for (Alerta alerta : getAlertasActivas()) {
            sb.append("- ").append(alerta).append("\n");
        }

        return sb.toString();
    }
}
