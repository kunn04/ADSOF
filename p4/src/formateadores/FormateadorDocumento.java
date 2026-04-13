package formateadores;

/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: FormateadorDocumento.java
 */

import documentos.IDocumento;

public interface FormateadorDocumento {
    /**
     * Formatea un documento en una representacion textual concreta.
     * @param documento documento a formatear
     * @return texto formateado
     */
    String formatear(IDocumento documento);
}
