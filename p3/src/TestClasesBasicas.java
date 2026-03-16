package src;

/**
 * Esta clase representa una serie de pruebas para las clases Enlace, Usuario y Mensaje. Se crean diferentes escenarios para verificar el correcto funcionamiento de los métodos implementados en cada clase.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Version: 1.0
 * Nombre del fichero: TestClasesBasicas.java
 */
public class TestClasesBasicas {
    public static void main(String[] args) {
        System.out.println("=== 1. PRUEBAS DE ENLACE ===");
        Usuario u1 = new Usuario("u1");
        Usuario u2 = new Usuario("u2");
        Usuario u3 = new Usuario("u3");
        
        Enlace e1 = new Enlace(u1, u2, -5); 
        System.out.println("Enlace coste negativo (esperado 1): " + e1.getCoste());
        
        Enlace e2 = new Enlace(u1, u3);
        System.out.println("Enlace sin coste (esperado 1): " + e2.getCoste());
        
        System.out.println("Suma total de costes (esperado 2): " + Enlace.getSumaCostes());
        
        e1.cambiarDestino(u3, 10);
        System.out.println("Nueva suma tras cambiarDestino a 10 (esperado 11): " + Enlace.getSumaCostes());


        System.out.println("\n=== 2. PRUEBAS DE USUARIO ===");
        Usuario a = new Usuario("origen", 5);
        Usuario b = new Usuario("destino1");
        
        System.out.println("Añadir enlace válido: " + a.addEnlace(b, 5)); // true
        
        System.out.println("Añadir enlace a sí mismo (esperado false): " + a.addEnlace(a, 3)); 
        
        System.out.println("Añadir destino duplicado (esperado false): " + a.addEnlace(b, 8)); 
        
        Enlace enlaceTrampa = new Enlace(b, a, 2);
        System.out.println("Añadir enlace de otro origen (esperado false): " + a.addEnlace(enlaceTrampa));
        
        System.out.println("Número total de enlaces de 'origen' (esperado 1): " + a.getNumEnlaces());
        System.out.println("Primer enlace de la lista (esperado destino1): " + a.getEnlace(0).getDestino().getNombre());


        System.out.println("\n=== 3. PRUEBAS DE MENSAJE ===");
        Usuario emisor = new Usuario("emisor", 2);
        Usuario puente = new Usuario("puente", 1); 
        Usuario aislado = new Usuario("aislado");
        Usuario receptor = new Usuario("receptor", 5); // ¡Línea añadida!
        
        emisor.addEnlace(puente, 10);
        puente.addEnlace(receptor, 20);
        emisor.addEnlace(receptor, 50); 
        
        Mensaje m1 = new Mensaje("Pobre", 5, emisor);
        System.out.println("Difundir sin alcance suficiente (esperado false): " + m1.difunde(emisor.getEnlace(puente)));
        System.out.println("Estado M1: " + m1); 

        Mensaje m2 = new Mensaje("Saltarín", 15, emisor);
        boolean resultadoVarargs = m2.difunde(aislado, puente, receptor);
        System.out.println("\nDifusión varargs con fallos (esperado false): " + resultadoVarargs);
        System.out.println("Estado final M2 (esperado en @puente con alcance 6): " + m2);

        Mensaje m3 = new Mensaje("Perfecto", 50, emisor);
        boolean viajePerfecto = m3.difunde(puente, receptor); 
        System.out.println("\nDifusión varargs sin fallos (esperado true): " + viajePerfecto);
        System.out.println("Estado final M3 (esperado en @receptor con alcance 26): " + m3);

        Mensaje m4 = new Mensaje("AlLímite", 10, emisor); // ¡Línea añadida!
        System.out.println("\nDifusión con alcance exacto al coste (esperado true): " + m4.difunde(emisor.getEnlace(puente)));
        System.out.println("Estado final M4 (esperado en @puente con alcance 1): " + m4);
    }
}