public class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private int ejemplaresDisponibles;
    private String genero;
    private int anyoPublicacion;

        public Libro(String isbn, String titulo, String autor, int ejemplaresDisponobles) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.ejemplaresDisponibles = ejemplaresDisponobles;
    }

    public Libro(String isbn, String titulo, String autor, int ejemplaresDisponobles, String genero, int anyoPublicacion) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.ejemplaresDisponibles = ejemplaresDisponobles;
        this.genero = genero;
        this.anyoPublicacion = anyoPublicacion;
    }

    // Método para verificar si el libro está disponible
    public boolean estaDisponible() {
        return this.ejemplaresDisponibles > 0;
    }

    // Método para prestar el libro
    public boolean prestar() {
        if (estaDisponible()) {
            this.ejemplaresDisponibles--;
            return true;
        }
        return false;
    }

    // Método para devolver el libro
    public void devolver() {
        this.ejemplaresDisponibles++;
    }

    // Método para obtener la descripción del libro
    private String descripcion() {
        String estado = this.estaDisponible() ? "Disponible" : "No disponible";
        return "'"+this.titulo + "' de " + this.autor + " [" + estado + "[";
    }

    public String getGenero() {
        return genero;
    }

    public int getAnyoPublicacion() {
        return anyoPublicacion;
    }

    @Override
    public String toString(){
        String ret = "ISBN: ";
        ret += this.isbn + ". " + this.descripcion() + " ("+this.ejemplaresDisponibles+ " ejemplares disponibles)";
        if (this.genero != null){
            ret += this.genero;
        }
        if (this.anyoPublicacion != 0){
            ret += this.anyoPublicacion;
        }
        return ret;
    }
}
