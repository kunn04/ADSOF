import java.util.*;

public class BibliotecaApp2 {
    public static void main(String[] args) {
        List<Libro> libros = new ArrayList<> (List.of(
            new Libro("1", "El Quijote", "Miguel de Cervantes", 5, "Avntura", 1800), 
            new Libro("2", "El murciélago", "Jo Nesbo", 1, "Aventura", 2000),
            new Libro("3", "Learn Java", "David Hoffman", 6, "Educativo", 2000)));


        for (Libro l: libros)
            System.out.println(l);
        
        Biblioteca biblioteca = new Biblioteca("Biblioteca EPS", libros);    
        System.out.println(biblioteca);

        List<Libro> l_gen = biblioteca.librosPorGenero("Aventura");
        System.out.println(l_gen);

        List<Libro> l_aPost = biblioteca.librosPosterioresA(1900);
        System.out.println(l_aPost);
    }
}
