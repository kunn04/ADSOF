package documentos;

/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: DocumentoEstacionMeteorologica.java
 */

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import estacion.EstacionMeteorologica;

public class DocumentoEstacionMeteorologica implements IDocumento {
    private final EstacionMeteorologica estacion;

    /** 
     * Constructor de la clase.
     * @param estacion estacion meteorológica
     * @throws IllegalArgumentException si la estacion es null
     */
    public DocumentoEstacionMeteorologica(EstacionMeteorologica estacion) {
        if (estacion == null) {
            throw new IllegalArgumentException("La estacion no puede ser null");
        }
        this.estacion = estacion;
    }

    @Override
    /** 
     * Obtiene el título del documento.
     * @return título del documento
     */
    public String getTituloDocumento() {
        return "Estación Meteorológica: " + estacion.getNombre();
    }

    @Override
    /** 
     * Obtiene el título de la sección principal.
     * @return título de la sección principal
     */
    public String getTituloSeccionPrincipal() {
        return estacion.getNombre();
    }

    @Override
    /** 
     * Obtiene los párrafos de la sección principal.
     * @return lista de párrafos de la sección principal
     */
    public List<String> getParrafosSeccionPrincipal() {
        List<String> parrafos = new ArrayList<>();
        parrafos.add("Ubicación: " + estacion.getLatitud() + ", " + estacion.getLongitud());
        parrafos.add("Sensores instalados: " + estacion.getSensores().size());

        LocalDateTime ultima = estacion.getUltimaLecturaGlobal();
        if (ultima == null) {
            parrafos.add("Última lectura: -");
        } else {
            parrafos.add("Última lectura: " + ultima);
        }

        return parrafos;
    }

    @Override
    /**
     * Obtiene los bloques de listas del documento.
     * @return lista de bloques de listas del documento
     */
    public List<BloqueListaDocumento> getBloquesListas() {
        List<BloqueListaDocumento> bloques = new ArrayList<>();
        bloques.add(new BloqueListaDocumento("Sensores activos", estacion.getResumenProcesadores()));
        bloques.add(new BloqueListaDocumento(
                "Alertas activas: " + estacion.getAlertasActivas().size(),
                estacion.getResumenAlertasActivas()));
        return bloques;
    }
}
