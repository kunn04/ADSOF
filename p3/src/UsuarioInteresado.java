package src;

/**
 * Esta clase representa un usuario interesado en una red social, que solo se interesa por los enlaces que apuntan a usuarios con exposición ALTA o VIRAL. Si no encuentra un enlace que cumpla esta condición, se comporta como un usuario normal.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Version: 1.0
 * Nombre del fichero: UsuarioInteresado.java
 */
public class UsuarioInteresado extends Usuario {

    /**
     * Constructor de la clase UsuarioInteresado.
     * @param nombre El nombre del usuario.
     */
    public UsuarioInteresado(String nombre) {
        super(nombre);
    }

    /**
     * Constructor de la clase UsuarioInteresado.
     * @param nombre El nombre del usuario.
     * @param cap_amplificacion La capacidad de amplificación del usuario.
     */
    public UsuarioInteresado(String nombre, int cap_amplificacion) {
        super(nombre, cap_amplificacion);
    }

    /**
     * Constructor de la clase UsuarioInteresado.
     * @param nombre El nombre del usuario.
     * @param cap_amplificacion La capacidad de amplificación del usuario.
     * @param exposicion La exposición del usuario.
     */
    public UsuarioInteresado(String nombre, int cap_amplificacion, Exposicion exposicion) {
        super(nombre, cap_amplificacion, exposicion);
    }

    /**
     * Devuelve el enlace al usuario especificado, solo si apunta a un usuario con exposición ALTA o VIRAL.
     * @param destino El usuario al que se enlaza.
     * @return El enlace al usuario especificado, o null si no existe o no cumple la condición.
     */
    @Override
    public Enlace getEnlace(Usuario destino) {
        
        for (int i = 0; i < this.getNumEnlaces(); i++) {
            Enlace e = this.getEnlace(i); // Usamos el getter por posición del padre
            Exposicion expDestino = e.getDestino().getExposicion();
            
            if (expDestino == Exposicion.ALTA || expDestino == Exposicion.VIRAL) {
                return e; 
            }
        }

        return super.getEnlace(destino);
    }

    /**
     * Devuelve una representación en cadena del usuario interesado.
     * @return Una representación en cadena del usuario interesado.
     */
    @Override
    public String toString() {
        return super.toString() + " [Interesado]";
    }
}