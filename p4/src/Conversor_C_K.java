public class Conversor_C_K implements Conversor {
    @Override
    public double convertir(double valor) {
        return valor + 273.15; // Conversión de Celsius a Kelvin
    }

    @Override
    public String getUnidadOrigen() {
        return "°C";
    }

    @Override
    public String getUnidadDestino() {
        return "°K";
    }
    
}
