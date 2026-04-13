package documentos;

/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: IDocumento.java
 */

import java.util.List;

public interface IDocumento {
    /**
     * Obtiene el titulo principal del documento.
     * @return titulo del documento
     */
    String getTituloDocumento();

    /**
     * Obtiene el titulo de la seccion principal.
     * @return titulo de la seccion principal
     */
    String getTituloSeccionPrincipal();

    /**
     * Obtiene los parrafos de la seccion principal.
     * @return lista de parrafos
     */
    List<String> getParrafosSeccionPrincipal();

    /**
     * Obtiene los bloques de listas del documento.
     * @return bloques de listas con su titulo y elementos
     */
    List<BloqueListaDocumento> getBloquesListas();
}
