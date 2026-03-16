package src;

/**
 * Esta clase representa un mensaje en una red social, con un texto, un alcance y un usuario actual. Permite difundir el mensaje a través de enlaces y usuarios, y representa el mensaje como una cadena.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Version: 1.0
 * Nombre del fichero: Mensaje.java
 */
public class Mensaje {
    private String texto;
    private int alcance;
    private Usuario actual;

    /**
     * Constructor de la clase Mensaje.
     * @param texto El texto del mensaje.
     * @param alcance El alcance del mensaje.
     * @param actual El usuario actual que difunde el mensaje.
     */
    public Mensaje(String texto, int alcance, Usuario actual) {
        this.texto = texto;
        this.alcance = alcance;
        this.actual = actual;
    }

    /**
     * Obtiene el texto del mensaje.
     * @return El texto del mensaje.
     */
    public String getTexto() {
        return texto;
    }

    /**
     * Obtiene el alcance del mensaje.
     * @return El alcance del mensaje.
     */
    public int getAlcance() {
        return alcance;
    }

    /**
     * Obtiene el usuario actual que difunde el mensaje.
     * @return El usuario actual que difunde el mensaje.
     */
    public Usuario getUsuarioActual() {
        return actual;
    }

    /**
     * Verifica si el mensaje puede ser difundido a través de un enlace.
     * @param e El enlace a verificar.
     * @return true si el mensaje puede ser difundido, false en caso contrario.
     */
    public boolean puedeDifundirPor(Enlace e) {
        if(alcance>=e.costeReal()) {
            return true;
        }

        return false;
    }

    /**
     * Verifica si el mensaje es aceptado por un usuario.
     * @param u El usuario a verificar.
     * @return true si el mensaje es aceptado, false en caso contrario.
     */
    public boolean aceptadoPor(Usuario u) {
        return true;
    }

    /**
     * Difunde el mensaje a través de un enlace.
     * @param e El enlace a través del cual difundir el mensaje.
     * @return true si el mensaje es difundido, false en caso contrario.
     */
    public boolean difunde(Enlace e) {
        if(e==null || !e.getOrigen().equals(this.actual) || !puedeDifundirPor(e) || !aceptadoPor(e.getDestino())) {
            return false;
        }

        this.actual = e.getDestino();
        alcance -= e.costeReal();
        alcance += this.actual.getCap_Amplificacion();

        this.actual.recibirMensaje(this);

        return true;
    }

    /**
     * Difunde el mensaje a través de varios usuarios.
     * @param usuarios Los usuarios a través de los cuales difundir el mensaje.
     * @return true si el mensaje es difundido, false en caso contrario.
     */
    public boolean difunde(Usuario... usuarios) {
        boolean flag = true;

        for(Usuario u: usuarios){
            Enlace enlace = actual.getEnlace(u);

            if(!difunde(enlace)) {
                flag = false;
            }
        }

        return flag;
    }

    /**
     * Representa el mensaje como una cadena.
     * @return La representación del mensaje.
     */
    @Override
    public String toString() {
        return "Mensaje(" + this.texto+ ":" + this.alcance + ") " + "en @" + this.actual.getNombre();
    }
}
