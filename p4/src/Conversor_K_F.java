public class Conversor_K_F implements Conversor {
    @Override
    public double convertir(double valor) {
        return (valor - 273.15) * 9/5 + 32; // Conversión de Kelvin a Fahrenheit
    }

    @Override
    public String getUnidadOrigen() {
        return "°K";
    }

    @Override
    public String getUnidadDestino() {
        return "°F";
    }
    
}
