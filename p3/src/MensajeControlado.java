package src;

/**
 * Esta clase representa un mensaje controlado en una red social, que tiene un nivel de rigidez que determina su difusión y aceptación. El mensaje no puede difundirse por enlaces señuelo y requiere un nivel de exposición específico para ser aceptado por los usuarios.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Version: 1.0
 * Nombre del fichero: MensajeControlado.java
 */
public class MensajeControlado extends Mensaje {

    private int rigidez;

    /**
     * Crea un mensaje controlado con un texto, alcance, usuario actual y nivel de rigidez.
     * @param texto El texto del mensaje.
     * @param alcance El alcance del mensaje.
     * @param actual El usuario que envía el mensaje.
     * @param rigidez El nivel de rigidez del mensaje.
     */
    public MensajeControlado(String texto, int alcance, Usuario actual, int rigidez) {
        super(texto, alcance, actual);
        this.rigidez = rigidez;
    }   

    /**
     * Determina si el mensaje puede difundirse por un enlace.
     * @param e El enlace por el que se intenta difundir.
     * @return true si el mensaje puede difundirse por el enlace, false en caso contrario.
     */
    @Override
    public boolean puedeDifundirPor(Enlace e) {
        if (e instanceof EnlaceSenuelo) {
            return false;
        }
        return super.puedeDifundirPor(e);
    }

    /**
     * Determina si el mensaje es aceptado por un usuario.
     * @param u El usuario que evalúa el mensaje.
     * @return true si el mensaje es aceptado por el usuario, false en caso contrario.
     */
    @Override
    public boolean aceptadoPor(Usuario u) {
        Exposicion expRequerida;
        
        if (this.rigidez >= 50) {
            expRequerida = Exposicion.VIRAL;
        } else if (this.rigidez >= 20) {
            expRequerida = Exposicion.ALTA;
        } else if (this.rigidez >= 10) {
            expRequerida = Exposicion.MEDIA;
        } else if (this.rigidez >= 5) {
            expRequerida = Exposicion.BAJA;
        } else {
            expRequerida = Exposicion.OCULTA; 
        }
        
        return u.getExposicion().ordinal() >= expRequerida.ordinal();
    }

    /**
     * Devuelve una representación en cadena del mensaje controlado.
     * @return La cadena que representa el mensaje.
     */
    @Override
    public String toString() {
        return super.toString() + " [Controlado: rigidez=" + this.rigidez + "]";
    }
}