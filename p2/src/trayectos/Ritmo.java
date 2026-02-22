package trayectos;

public enum Ritmo{
    SUAVE(15),
    MODERADO(10),
    RAPIDO(8);

    private int tiempo;

    private Ritmo(int tiempo){
        this.tiempo = tiempo;
    }

    public int getTimeKm(){
        return this.tiempo;
    }

    public String toString(){
        return " (ritmo "+this.name+")";
    }
}