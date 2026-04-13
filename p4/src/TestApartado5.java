/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: TestApartado5.java
 */
import java.time.LocalDate;

import conversores.Conversor;
import conversores.Conversor_C_K;
import documentos.DocumentoEstacionMeteorologica;
import documentos.IDocumento;
import estacion.EstacionMeteorologica;
import excepciones.ConversorIncompatibleException;
import excepciones.SensorDuplicadoException;
import excepciones.UnidadNoPermitidaException;
import formateadores.FormateadorDocumento;
import formateadores.FormateadorHTML;
import formateadores.FormateadorMarkdown;
import sensores.SensorTemperatura;

public class TestApartado5 {
    /**
     * Metodo publico de la clase.
     */
    public static void main(String[] args) {
        EstacionMeteorologica estacion = new EstacionMeteorologica("Madrid Centro", 40.4168, -3.7038);

        SensorTemperatura temp1 = new SensorTemperatura(0.5, LocalDate.of(2023, 1, 1), null);
        SensorTemperatura temp2 = new SensorTemperatura(0.5, LocalDate.of(2023, 1, 1), null);

        Conversor celsiusKelvin = new Conversor_C_K();

        try {
            estacion.addSensor(temp1, celsiusKelvin);
            estacion.addSensor(temp2);
        } catch (SensorDuplicadoException | ConversorIncompatibleException | UnidadNoPermitidaException e) {
            System.out.println("Error en alta de sensores: " + e.getMessage());
            return;
        }

        estacion.lecturaSensor(20.0);
        for (int i = 0; i < 3; i++) {
            estacion.simularLecturas();
        }

        IDocumento documento = new DocumentoEstacionMeteorologica(estacion);

        FormateadorDocumento formateadorHtml = new FormateadorHTML();
        FormateadorDocumento formateadorMarkdown = new FormateadorMarkdown();

        System.out.println("===== HTML =====");
        System.out.println(formateadorHtml.formatear(documento));

        System.out.println("\n===== MARKDOWN =====");
        System.out.println(formateadorMarkdown.formatear(documento));
    }
}
