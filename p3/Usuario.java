import java.util.*;

public class Usuario {
    private String nombre;
    private int cap_amplificacion;
    private List<Enlace> enlaces;

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.cap_amplificacion = 2;
        this.enlaces = new ArrayList<>();
    }

    public Usuario(String nombre, int cap_amplificacion) {
        this.nombre = nombre;
        this.cap_amplificacion = cap_amplificacion;
        this.enlaces = new ArrayList<>();
    }

    public boolean addEnlace(Enlace e) {
        if(!e.getOrigen().equals(this) || e.getDestino().equals(this)) {
            return false;
        }

        for(Enlace enlace: enlaces) {
            if(enlace.getDestino().equals(e.getDestino())) {
                return false;
            }
        }

        enlaces.add(e);

        return true;
    }

    public boolean addEnlace(Usuario destino, int coste) {
        Enlace e = new Enlace(this, destino, coste);

        return addEnlace(e);
    }

    public String getNombre() {
        return nombre;
    }

    public int getCap_Amplificacion() {
        return cap_amplificacion;
    }

    public Enlace getEnlace(int i) {
        return enlaces.get(i);
    }

    public int getNumEnlaces() {
        return enlaces.size();
    }

    public Enlace getEnlace(Usuario destino) {
        for(Enlace e: enlaces) {
            if(e.getDestino().equals(destino)) {
                return e;
            }
        }

        return null;
    }

        @Override
    public String toString() {
        return "@" + this.nombre + "(" + this.cap_amplificacion + ") " + this.enlaces;
    }
}
