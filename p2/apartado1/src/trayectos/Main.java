package trayectos;

/**
 * Esta clase representa el main que muestra la funcionalidad de las clases TramoAPie y TramoTren.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Version: 1.0
 * Nombre del fichero: Biblioteca.java
 */

public class Main{

    /**
     * Metodo main que muestra la funcionalidad de las clases TramoAPie y TramoTren
      * @param args los argumentos de la linea de comandos
     */
    public static void main(String[] args){
        TramoTrayecto[] trayecto = {
            new TramoAPie("Hotel Puerta del Sol", "Sol Renfe", 1),
            new TramoTren("Sol Renfe", "Cantoblanco Renfe", Linea.C4, 4),
            new TramoAPie("Cantoblanco Renfe", "EPS", 2.6, Ritmo.RAPIDO),

        };

        for (TramoTrayecto tramo: trayecto)
            System.out.println(tramo);
    }
}
