public class Mensaje {
    private String texto;
    private int alcance;
    private Usuario actual;

    public Mensaje(String texto, int alcance, Usuario actual) {
        this.texto = texto;
        this.alcance = alcance;
        this.actual = actual;
    }

    public String getTexto() {
        return texto;
    }

    public int getAlcance() {
        return alcance;
    }

    public Usuario getUsuarioActual() {
        return actual;
    }

    public boolean puedeDifundirPor(Enlace e) {
        if(alcance>=e.costeReal()) {
            return true;
        }

        return false;
    }

    public boolean aceptadoPor(Usuario u) {
        return true;
    }

    public boolean difunde(Enlace e) {
        if(e==null || !e.getOrigen().equals(this.actual) || !puedeDifundirPor(e) || !aceptadoPor(e.getDestino())) {
            return false;
        }

        this.actual = e.getDestino();
        alcance -= e.costeReal();
        alcance += this.actual.getCap_Amplificacion();

        return true;
    }

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

    @Override
    public String toString() {
        return "Mensaje(" + this.texto+ ":" + this.alcance + ") " + "en @" + this.actual.getNombre();
    }
}
