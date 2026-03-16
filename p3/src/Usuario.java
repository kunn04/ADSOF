package src;
import java.util.*;

/**
 * Esta clase representa un usuario en una red social, con un nombre, una capacidad de amplificación y una lista de enlaces a otros usuarios. Permite añadir enlaces a otros usuarios, obtener información sobre los enlaces y representar el usuario como una cadena.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Version: 1.0
 * Nombre del fichero: Usuario.java
 */
public class Usuario {
    private String nombre;
    private int cap_amplificacion;
    private List<Enlace> enlaces;
    private Exposicion exposicion;
    private List<Mensaje> historialMensajes;

    /**
     * Constructor de la clase Usuario.
     * @param nombre El nombre del usuario.
     */
    public Usuario(String nombre) {
        this(nombre, 2);
    }

    /**
     * Constructor de la clase Usuario.
     * @param nombre El nombre del usuario.
     * @param cap_amplificacion La capacidad de amplificación del usuario.
     */
    public Usuario(String nombre, int cap_amplificacion) {
        this.nombre = nombre;
        this.cap_amplificacion = cap_amplificacion;
        this.enlaces = new ArrayList<>();
        this.exposicion = Exposicion.ALTA;
        this.historialMensajes = new ArrayList<>();
    }

    /**
     * Constructor de la clase Usuario.
     * @param nombre El nombre del usuario.
     * @param cap_amplificacion La capacidad de amplificación del usuario.
     * @param exposicion La exposición del usuario.
     */
    public Usuario(String nombre, int cap_amplificacion, Exposicion exposicion) {
        this(nombre, cap_amplificacion); // Llama al constructor de arriba
        this.exposicion = exposicion;
    }

    /**
     * Añade un enlace al usuario.
     * @param e El enlace a añadir.
     * @return true si el enlace se añade correctamente, false en caso contrario.
     */
    public boolean addEnlace(Enlace e) {
        if(!e.getOrigen().equals(this) || e.getDestino().equals(this)) {
            return false;
        }

        if (this.getEnlace(e.getDestino()) != null) {
            return false; 
        }

        enlaces.add(e);

        return true;
    }

    /**
     * Añade un enlace al usuario.
     * @param destino El usuario al que se enlaza.
     * @param coste El coste del enlace.
     * @return true si el enlace se añade correctamente, false en caso contrario.
     */
    public boolean addEnlace(Usuario destino, int coste) {
        
        if (destino.equals(this) || this.getEnlace(destino) != null) {
            return false;
        }

        Enlace e = new Enlace(this, destino, coste);

        return addEnlace(e);
    }

    /**
     * Devuelve el nombre del usuario.
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve la capacidad de amplificación del usuario.
     * @return La capacidad de amplificación del usuario.
     */
    public int getCap_Amplificacion() {
        return cap_amplificacion;
    }

    /**
     * Devuelve el enlace en la posición i.
     * @param i La posición del enlace a devolver.
     * @return El enlace en la posición i.
     */
    public Enlace getEnlace(int i) {
        return enlaces.get(i);
    }

    /**
     * Devuelve el número de enlaces del usuario.
     * @return El número de enlaces del usuario.
     */
    public int getNumEnlaces() {
        return enlaces.size();
    }

    /**
     * Devuelve el enlace al usuario especificado.
     * @param destino El usuario al que se enlaza.
     * @return El enlace al usuario especificado, o null si no existe.
     */
    public Enlace getEnlace(Usuario destino) {
        for(Enlace e: enlaces) {
            if(e.getDestino().equals(destino)) {
                return e;
            }
        }

        return null;
    }

    /**
     * Devuelve la exposición del usuario.
     * @return La exposición del usuario.
     */
    public Exposicion getExposicion() {
        return exposicion;
    }

    /**
     * Cambia la exposición del usuario.
     * @param e La nueva exposición del usuario.
     */
    public void cambiarExposicion(Exposicion e) {
        this.exposicion = e;
    }

    /**
     * Recibe un mensaje y actualiza la exposición del usuario en función del alcance del mensaje y el promedio de los alcances de los mensajes anteriores.
     * @param m El mensaje recibido.
     */
    public void recibirMensaje(Mensaje m) {
        double promedio = 0.0;
        
        if (!historialMensajes.isEmpty()) {
            double suma = 0;
            for (Mensaje hist : historialMensajes) {
                suma += hist.getAlcance();
            }
            promedio = suma / historialMensajes.size();
        }

        if (m.getAlcance() > promedio) {
            this.exposicion = this.exposicion.subir();
        } else {
            this.exposicion = this.exposicion.bajar();
        }

        historialMensajes.add(m);
    }

    /**
     * Devuelve una representación en cadena del usuario.
     * @return Una representación en cadena del usuario.
     */
    @Override
    public String toString() {
        return "@" + this.nombre + " (" + this.cap_amplificacion + ") " + this.enlaces;
    }
}
