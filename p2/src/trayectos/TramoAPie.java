package trayectos;

public class TramoAPie extends TramoTrayecto{
    private Ritmo ritmo;
    private double numKm;

    public TramoAPie(String origen, String destino, double numKm, Ritmo ritmo){
        super(origen, destino);
        this.numKm = numKm;
        this.ritmo = ritmo;
    }

    public TramoAPie(String origen, String destino, double numKm){
        super(origen, destino);
        this.ritmo = ritmo.MODERADO;
        this.numKm = numKm;
    }

    @Override
    public double tiempo(){
        return this.numKm * this.ritmo.getTimeKm();
    }

    @Override
    public String toString(){
        return "A pie "+super.toString()+this.ritmo;
    }

}