package src;

/**
 * Esta clase representa los diferentes niveles de exposición que un mensaje puede tener en una red social, desde OCULTA hasta VIRAL. Permite subir o bajar el nivel de exposición según las interacciones del mensaje.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Version: 1.0
 * Nombre del fichero: Exposicion.java
 */
public enum Exposicion {
    OCULTA, 
    BAJA, 
    MEDIA, 
    ALTA, 
    VIRAL;

    /**
     * Sube el nivel de exposición.
     * @return El nuevo nivel de exposición.
     */
    public Exposicion subir() {
        switch (this) {
            case OCULTA: return BAJA;
            case BAJA:   return MEDIA;
            case MEDIA:  return ALTA;
            case ALTA:   return VIRAL;
            case VIRAL:  return VIRAL; 
            default:     return this;
        }
    }

    /**
     * Baja el nivel de exposición.
     * @return El nuevo nivel de exposición.
     */
    public Exposicion bajar() {
        switch (this) {
            case VIRAL:  return ALTA;
            case ALTA:   return MEDIA;
            case MEDIA:  return BAJA;
            case BAJA:   return OCULTA;
            case OCULTA: return OCULTA; 
            default:     return this;
        }
    }
}