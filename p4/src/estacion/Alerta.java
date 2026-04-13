package estacion;

/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: Alerta.java
 */

import java.time.LocalDateTime;

public class Alerta {
    private final LocalDateTime fecha;
    private final String sensorId;
    private final String tipo;
    private final String mensaje;
    private boolean activa;

    /** 
     * Constructor de la clase.
     * @param fecha fecha de la alerta
     * @param sensorId ID del sensor
     * @param tipo tipo de alerta
     * @param mensaje mensaje de la alerta
     */
    public Alerta(LocalDateTime fecha, String sensorId, String tipo, String mensaje) {
        this.fecha = fecha;
        this.sensorId = sensorId;
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.activa = true;
    }

    /** 
     * Obtiene la fecha de la alerta.
     * @return fecha de la alerta
     */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /** 
     * Obtiene el ID del sensor.
     * @return ID del sensor
     */
    public String getSensorId() {
        return sensorId;
    }

    /** 
     * Obtiene el tipo de alerta.
     * @return tipo de alerta
     */
    public String getTipo() {
        return tipo;
    }

    /** 
     * Obtiene el mensaje de la alerta.
     * @return mensaje de la alerta
     */
    public String getMensaje() {
        return mensaje;
    }

    /** 
     * Obtiene si la alerta está activa.
     * @return true si la alerta está activa, false en caso contrario
     */
    public boolean isActiva() {
        return activa;
    }

    /** 
     * Desactiva la alerta.
     */
    public void desactivar() {
        this.activa = false;
    }

    @Override
    /** 
     * Obtiene la representación en cadena de la alerta.
     * @return representación en cadena de la alerta
     */
    public String toString() {
        return "[" + fecha + "] " + mensaje;
    }
}
