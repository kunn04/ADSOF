package formateadores;

/**
 * Esta clase forma parte de la practica ADSOF (P4).
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Grupo: 2262
 * Version: 1.0
 * Nombre del fichero: FormateadorHTML.java
 */

import documentos.BloqueListaDocumento;
import documentos.IDocumento;

public class FormateadorHTML implements FormateadorDocumento {
    @Override
    /** 
     * Metodo publico de la clase.
     * @param documento documento a formatear
     * @return cadena con el documento formateado en HTML
     */
    public String formatear(IDocumento documento) {
        StringBuilder sb = new StringBuilder();

        sb.append("<!DOCTYPE html>\n");
        sb.append("<html lang=\"es\">\n");
        sb.append("<head>\n");
        sb.append("    <title>").append(escapeHtml(documento.getTituloSeccionPrincipal())).append("</title>\n");
        sb.append("</head>\n");
        sb.append("<body>\n");
        sb.append("    <h1>").append(escapeHtml(documento.getTituloDocumento())).append("</h1>\n");
        sb.append("    <h2>").append(escapeHtml(documento.getTituloSeccionPrincipal())).append("</h2>\n");

        for (String parrafo : documento.getParrafosSeccionPrincipal()) {
            sb.append("    <p>").append(escapeHtml(parrafo)).append("</p>\n");
        }

        for (BloqueListaDocumento bloque : documento.getBloquesListas()) {
            sb.append("    <p>").append(escapeHtml(bloque.getTitulo())).append("</p>\n");
            sb.append("    <ul>\n");
            for (String elemento : bloque.getElementos()) {
                sb.append("        <li>").append(escapeHtml(elemento)).append("</li>\n");
            }
            sb.append("    </ul>\n");
        }

        sb.append("</body>\n");
        sb.append("</html>");

        return sb.toString();
    }

    /** 
     * Retorna una versión escapada de un texto para su inclusión en HTML.
     * @param texto texto a escapar
     * @return texto escapado
     */
    private String escapeHtml(String texto) {
        return texto
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }
}
