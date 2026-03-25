public class Conversor_hPa_Pa implements Conversor {
    @Override
    public double convertir(double valor) {
        return valor * 100; // Conversión de hPa a Pa
    }

    @Override
    public String getUnidadOrigen() {
        return "hPa";
    }

    @Override
    public String getUnidadDestino() {
        return "Pa";
    }
    
}
