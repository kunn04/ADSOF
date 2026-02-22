package trayectos;

public enum Linea{
    C1("azul claro", 5),
    C4("azul oscuro", 10),
    C5("amarilla", 30);

    private String color;
    private int stopTime;

    private Linea(String color, int stopTime){
        this.color = color;
        this.stopTime = stopTime;
    }

    public int getStopTime(){
        return this.stopTime;
    }

    public String toString(){
        return this.name()+" ("+this.color+")";
    }
}