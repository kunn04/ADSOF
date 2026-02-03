import java.util.*;

/**
 * Esta clase muestra prueba la funcionalidad de la clase Libro
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Version: 1.0
 * Nombre del fichero: BibliotecaApp.java
 */
public class BibliotecaApp {

    /**
     * Punto de entrada de la aplicación
     * El programa muestra prueba la funcionalidad de la clase Libro.
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        List<Libro> libros = new ArrayList<> (List.of(
            new Libro("1", "El Quijote", "Miguel de Cervantes", 5), // ISBN, titulo, autor, #ejemplares
            new Libro("2", "El murciélago", "Jo Nesbo", 1),
            new Libro("3", "Learn Java", "David Hoffman", 6)));

        libros.get(1).prestar();
        for (Libro l: libros)
            System.out.println(l);

        libros.get(1).devolver();
        System.out.println(libros);

        libros.add(new Libro("4", "Con viento solano", "Ignacio Aldecoa", 1));
        System.out.println(libros);
    }
}
