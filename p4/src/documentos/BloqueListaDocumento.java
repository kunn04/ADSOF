package documentos;

/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: BloqueListaDocumento.java
 */

import java.util.Collections;
import java.util.List;

public class BloqueListaDocumento {
    private final String titulo;
    private final List<String> elementos;

    /** 
     * Constructor de la clase.
     * @param titulo titulo del bloque
     * @param elementos lista de elementos del bloque
     */
    public BloqueListaDocumento(String titulo, List<String> elementos) {
        this.titulo = titulo;
        this.elementos = elementos;
    }

    /**
     * Obtiene el titulo del bloque.
     * @return titulo del bloque
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Obtiene la lista de elementos del bloque.
     * @return lista de elementos del bloque
     */
    public List<String> getElementos() {
        return Collections.unmodifiableList(elementos);
    }
}
