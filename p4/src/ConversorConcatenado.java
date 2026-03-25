public class ConversorConcatenado implements Conversor {
    private Conversor conversor1;
    private Conversor conversor2;

    public ConversorConcatenado(Conversor conversor1, Conversor conversor2) {
        this.conversor1 = conversor1;
        this.conversor2 = conversor2;
    }

    @Override
    public double convertir(double valor) {
        double resultadoIntermedio = conversor1.convertir(valor);
        return conversor2.convertir(resultadoIntermedio);
    }

    @Override
    public String getUnidadOrigen() {
        return conversor1.getUnidadOrigen();
    }

    @Override
    public String getUnidadDestino() {
        return conversor2.getUnidadDestino();
    }
    
}
