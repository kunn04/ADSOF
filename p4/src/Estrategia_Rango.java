import java.util.*;

public class Estrategia_Rango implements Estrategia {
    private Random rand = new Random();
    private double porcentajeRango;

    public Estrategia_Rango(double porcentajeRango) {
        this.porcentajeRango = porcentajeRango;
    }

    @Override
    public double generarValor(Sensor sensor) {
        List<Double> lecturas = sensor.getLecturas();
        
        if(lecturas.isEmpty()) {
            return new Estrategia_Min_Max(0.1).generarValor(sensor);
        }

        double anteriorValor = lecturas.get(lecturas.size() - 1);
        double rango = anteriorValor * porcentajeRango / 100.0;
        return anteriorValor + (rand.nextDouble() * 2 - 1)  * rango;
    }
    
}
