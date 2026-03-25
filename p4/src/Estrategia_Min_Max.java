import java.util.*;

public class Estrategia_Min_Max implements Estrategia {
    private Random rand = new Random();
    private double fueraRango;
    
    public Estrategia_Min_Max(double fueraRango) {
        this.fueraRango = fueraRango;
    }

    @Override
    public double generarValor(Sensor sensor) {
        double valor_min = sensor.getMin();
        double valor_max = sensor.getMax();

        if(rand.nextDouble() < fueraRango) { // probabilidad de generar un valor fuera de rango
            if(rand.nextBoolean()) {
                return valor_min - rand.nextDouble() * 10; // Valor por debajo del mínimo
            } else {
                return valor_max + rand.nextDouble() * 10; // Valor por encima del máximo
            }
        } 
        else {
            return valor_min + rand.nextDouble() * (valor_max - valor_min); // Valor dentro del rango
        }   
    }
    
}
