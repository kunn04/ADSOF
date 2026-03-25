public class Conversor_Pa_mbar implements Conversor {
    @Override
    public double convertir(double valor) {
        return valor / 100; // Conversión de Pa a mbar
    }

    @Override
    public String getUnidadOrigen() {
        return "Pa";
    }

    @Override
    public String getUnidadDestino() {
        return "mbar";
    }
    
}