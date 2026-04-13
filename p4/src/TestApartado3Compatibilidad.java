/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: TestApartado3Compatibilidad.java
 */
import java.time.LocalDate;

import conversores.Conversor;
import conversores.ConversorConcatenado;
import conversores.Conversor_C_K;
import conversores.Conversor_K_F;
import conversores.Conversor_hPa_Pa;
import estacion.EstacionMeteorologica;
import excepciones.ConversorIncompatibleException;
import excepciones.SensorDuplicadoException;
import excepciones.UnidadNoPermitidaException;
import sensores.SensorHumedad;
import sensores.SensorPresion;
import sensores.SensorTemperatura;

public class TestApartado3Compatibilidad {
    /**
     * Metodo publico de la clase.
     */
    public static void main(String[] args) {
        EstacionMeteorologica estacion = new EstacionMeteorologica("Madrid Centro", 40.4168, -3.7038);

        SensorTemperatura temp = new SensorTemperatura(0.5, LocalDate.of(2023, 1, 1), null);
        SensorPresion pres = new SensorPresion(2.0, LocalDate.of(2023, 3, 1), null);
        SensorHumedad hum = new SensorHumedad(1.0, LocalDate.of(2023, 2, 1), null);

        try {
            Conversor celsiusKelvin = new Conversor_C_K();
            Conversor kelvinFahrenheit = new Conversor_K_F();
            Conversor celsiusFahrenheit = new ConversorConcatenado(celsiusKelvin, kelvinFahrenheit);
            estacion.addSensor(temp, celsiusFahrenheit);
            System.out.println("OK: conversor concatenado C -> F asociado a temperatura");
        } catch (SensorDuplicadoException | ConversorIncompatibleException | UnidadNoPermitidaException e) {
            System.out.println("ERROR inesperado: " + e.getMessage());
        }

        try {
            estacion.addSensor(hum, new Conversor_hPa_Pa());
            System.out.println("ERROR: deberia haber fallado por unidades incompatibles");
        } catch (SensorDuplicadoException e) {
            System.out.println("ERROR inesperado de duplicado: " + e.getMessage());
        } catch (ConversorIncompatibleException e) {
            System.out.println("OK incompatibilidad origen/destino: " + e.getMessage());
        } catch (UnidadNoPermitidaException e) {
            System.out.println("OK unidad no permitida: " + e.getMessage());
        }

        try {
            estacion.addSensor(pres, new Conversor_C_K());
            System.out.println("ERROR: deberia haber fallado por unidad destino no permitida");
        } catch (SensorDuplicadoException e) {
            System.out.println("ERROR inesperado de duplicado: " + e.getMessage());
        } catch (ConversorIncompatibleException e) {
            System.out.println("OK incompatibilidad origen/destino: " + e.getMessage());
        } catch (UnidadNoPermitidaException e) {
            System.out.println("OK unidad no permitida: " + e.getMessage());
        }
    }
}
