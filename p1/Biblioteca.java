import java.util.*;

public class Biblioteca {
    private String nombre;
    private List<Libro> libros; 
    private Map<String, List<Libro>> librosGenero;

    public Biblioteca(String nombre, List<Libro> l) {
        this.nombre = nombre;
        this.libros = new ArrayList<>();
        if (l != null) {
            this.libros.addAll(l);
        }
        this.librosGenero = new HashMap<>();
    } 

    public List<Libro> librosPorGenero(String genero) {
        if(!librosGenero.containsKey(genero)) {
            List<Libro> l_gen = new ArrayList<>();

            for (Libro l: libros) {
                if(l.getGenero() != null && l.getGenero().equals(genero)) {
                    l_gen.add(l);
                }
            }

            librosGenero.put(genero, l_gen);
        }

        return librosGenero.get(genero);
    }

    public List<Libro> librosPosterioresA(int anyoPublicacion) {
        List<Libro> l_aPost = new ArrayList<>();

        for (Libro l: libros) {
            if(l.getAnyoPublicacion() != 0 && l.getAnyoPublicacion() > anyoPublicacion) {
                l_aPost.add(l);
            }
        }

        return l_aPost;
    }

    @Override
    public String toString(){
        return "Biblioteca: "+this.nombre+ "\nLibros de la Biblioteca: "+this.libros;
    }
}
