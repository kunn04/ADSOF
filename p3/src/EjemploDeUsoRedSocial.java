package src;

/**
 * Esta clase representa es un ejemplo de uso de la clase RedSocial, que carga usuarios, enlaces y mensajes desde archivos de texto. El programa intenta cargar dos mensajes diferentes y maneja posibles errores de archivo.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Version: 1.0
 * Nombre del fichero: EjemploDeUsoRedSocial.java
 */
import java.io.IOException;

public class EjemploDeUsoRedSocial {
    public static void main(String[] args) {
        try {
            RedSocial s;
            s = new RedSocial("txt/USUARIOS.txt", "txt/ENLACES.txt", "txt/MENSAJE.txt");
            s = new RedSocial("txt/USUARIOS.txt", "txt/ENLACES.txt", "txt/MENSAJE2.txt");
        } catch (IOException e) {
            System.out.println("Error en archivos");
        }
    }
}