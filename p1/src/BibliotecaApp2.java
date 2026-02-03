import java.util.*;

/**
 * Esta clase muestra prueba la funcionalidad de la clase Libro y Biblioteca
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Version: 1.0
 * Nombre del fichero: BibliotecaApp.java
 */
public class BibliotecaApp2 {

    /**
     * Punto de entrada de la aplicación
     * El programa muestra prueba la funcionalidad de la clase Libro y Biblioteca.
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        List<Libro> libros = new ArrayList<> (List.of(
            new Libro("1", "El Quijote", "Miguel de Cervantes", 5, "Aventura", 1800), 
            new Libro("2", "El murciélago", "Jo Nesbo", 1, "Aventura", 2000),
            new Libro("3", "Learn Java", "David Hoffman", 6, "Educativo", 2000)));
        
        Biblioteca biblioteca = new Biblioteca("Biblioteca EPS", libros);    
        System.out.println(biblioteca);

        List<Libro> l_gen = biblioteca.librosPorGenero("Aventura");
        System.out.println(l_gen);

        List<Libro> l_aPost = biblioteca.librosPosterioresA(1900);
        System.out.println(l_aPost);
    }
}
