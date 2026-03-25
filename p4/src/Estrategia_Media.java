import java.util.*;

public class Estrategia_Media implements Estrategia {
    private Random rand = new Random();
    private double porcentajeRango;

    public Estrategia_Media(double porcentajeRango) {
        this.porcentajeRango = porcentajeRango;
    }

    @Override
    public double generarValor(Sensor sensor) {
        List<Double> lecturas = sensor.getLecturas();

        if(lecturas.isEmpty()) {
            return new Estrategia_Min_Max(0.1).generarValor(sensor);
        }

        double media = 0;
        for(double lectura : lecturas) {
            media += lectura;
        }
        media /= lecturas.size();   
        double rango = media * porcentajeRango / 100.0;
        return media + (rand.nextDouble() * 2 - 1)  * rango;
    }

}
