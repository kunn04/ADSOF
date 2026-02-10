package trayectos;

public class TramoAPie extends TramoTrayecto{
    private Ritmo ritmo;
    private double numKm;

    public TramoAPie(Ritmo ritmo, double numKm){
        super(origen, destino);
        this.ritmo = ritmo;
        this.numKm = numKm;
    }

    public TramoAPie(double numKm){
        super(origen, destino);
        this.ritmo = MODERADO;
        this.numKm = numKm;
    }

    

}