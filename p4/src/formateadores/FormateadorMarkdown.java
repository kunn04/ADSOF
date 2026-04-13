package formateadores;

/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: FormateadorMarkdown.java
 */

import documentos.BloqueListaDocumento;
import documentos.IDocumento;

public class FormateadorMarkdown implements FormateadorDocumento {
    @Override
    /** 
     * Metodo publico de la clase.
     * @param documento documento a formatear
     * @return cadena con el documento formateado en Markdown
     */
    public String formatear(IDocumento documento) {
        StringBuilder sb = new StringBuilder();

        sb.append("# ").append(documento.getTituloDocumento()).append("\n\n");
        sb.append("## ").append(documento.getTituloSeccionPrincipal()).append("\n\n");

        for (String parrafo : documento.getParrafosSeccionPrincipal()) {
            sb.append(parrafo).append("\n\n");
        }

        for (BloqueListaDocumento bloque : documento.getBloquesListas()) {
            sb.append("### ").append(bloque.getTitulo()).append("\n");
            for (String elemento : bloque.getElementos()) {
                sb.append("- ").append(elemento).append("\n");
            }
            sb.append("\n");
        }

        return sb.toString().trim();
    }
}
