public class ConversorIdentidad implements Conversor {
    private String unidad;

    public ConversorIdentidad(String unidad) {
        this.unidad = unidad;
    }

    @Override
    public double convertir(double valor) {
        return valor; // No se realiza ninguna conversión, se devuelve el mismo valor
    }

    @Override
    public String getUnidadOrigen() {
        return unidad;
    }

    @Override
    public String getUnidadDestino() {
        return unidad;
    }
    
}
