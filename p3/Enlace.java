public class Enlace {
    private Usuario origen;
    private Usuario destino;
    private int coste;

    private static int suma_costes = 0;

    public Enlace (Usuario origen, Usuario destino, int coste) {
        this.origen = origen;
        this.destino = destino;
        if(coste <= 0) {
            this.coste = 1;
        }
        else {
            this.coste = coste;
        }
        suma_costes += this.coste;
    }

    public Enlace (Usuario origen, Usuario destino) {
        this.origen = origen;
        this.destino = destino;
        this.coste = 1;
        suma_costes += this.coste;
    }

    public Usuario getOrigen() {
        return origen;
    }

    public Usuario getDestino() {
        return destino;
    }

    public int getCoste() {
        return coste;
    }

    public void cambiarDestino(Usuario new_destino, int new_coste) {
        this.destino = new_destino;

        if(new_coste <= 0){
            this.coste = 1;
        }
        else {
            this.coste = new_coste;
        }
    }

    public static int getSumaCostes() {
        return suma_costes;
    }

    public int costeEspecial() {
        return 0;
    }

    public int costeReal() {
        return coste + costeEspecial();
    }

    @Override
    public String toString() {
        return "(@" + this.origen.getNombre() + "--" + this.coste + "-->" + "@" + this.destino.getNombre() + ")";
    }
}