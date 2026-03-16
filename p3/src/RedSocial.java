package src;

import java.io.*;
import java.util.*;

/**
 * Esta clase representa una red social que contiene usuarios, enlaces entre ellos y un mensaje que se difunde a través de la red. La red se puede cargar desde archivos de texto y simular la difusión del mensaje según una ruta predefinida. También permite añadir usuarios, enlaces y configurar el mensaje, así como guardar el estado de la red en archivos.
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Version: 1.0
 * Nombre del fichero: RedSocial.java
 */
public class RedSocial {
    private Map<String, Usuario> usuarios;
    private List<Enlace> enlaces;
    private Mensaje mensaje;
    private List<Usuario> ruta;

    /**
     * Crea una red social con usuarios, enlaces y un mensaje cargados desde archivos de texto.
     * @param archUsuarios La ruta del archivo que contiene los usuarios.
     * @param archEnlaces La ruta del archivo que contiene los enlaces.
     * @param archMensaje La ruta del archivo que contiene el mensaje.
     * @throws IOException Si ocurre un error al leer los archivos.
     */
    public RedSocial(String archUsuarios, String archEnlaces, String archMensaje) throws IOException {
        this.usuarios = new LinkedHashMap<>(); 
        this.enlaces = new ArrayList<>();
        this.ruta = new ArrayList<>();

        cargarUsuarios(archUsuarios);
        cargarEnlaces(archEnlaces);
        cargarMensajeYRuta(archMensaje);

        simularDifusion();
    }

    /**
     * Crea una red social vacía.
     */
    public RedSocial() {
        this.usuarios = new LinkedHashMap<>();
        this.enlaces = new ArrayList<>();
        this.ruta = new ArrayList<>();
    }

    /**
     * Carga los usuarios desde un archivo de texto.
     * @param rutaFichero La ruta del archivo que contiene los usuarios.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    private void cargarUsuarios(String rutaFichero) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.trim().split("\\s+"); 
                
                if (datos.length >= 2) {
                    String nombre = datos[0];
                    int cap = Integer.parseInt(datos[1]);
                    
                    usuarios.put(nombre, new Usuario(nombre, cap));
                }
            }
        }
    }

    /**
     * Carga los enlaces desde un archivo de texto.
     * @param rutaFichero La ruta del archivo que contiene los enlaces.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    private void cargarEnlaces(String rutaFichero) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.trim().split("\\s+");
                
                if (datos.length >= 3) {
                    String origen = datos[0];
                    String destino = datos[1];
                    int coste = Integer.parseInt(datos[2]);

                    Usuario uOrigen = usuarios.get(origen);
                    Usuario uDestino = usuarios.get(destino);

                    if (uOrigen != null && uDestino != null) {
                        uOrigen.addEnlace(uDestino, coste);
                        this.enlaces.add(uOrigen.getEnlace(uDestino));
                    }
                }
            }
        }
    }

    /**
     * Carga el mensaje y la ruta desde un archivo de texto.
     * @param rutaFichero La ruta del archivo que contiene el mensaje y la ruta.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    private void cargarMensajeYRuta(String rutaFichero) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
            String lineaMensaje = br.readLine();
            if (lineaMensaje != null) {
                String[] datos = lineaMensaje.trim().split("\\s+");
                
                if (datos.length >= 3) {
                    String texto = datos[0].replace("\"", "");
                    int alcance = Integer.parseInt(datos[1]);
                    String nombreOrigen = datos[2];
                    
                    Usuario uOrigen = usuarios.get(nombreOrigen);
                    this.mensaje = new Mensaje(texto, alcance, uOrigen);
                }
            }
            
            String lineaRuta;
            while ((lineaRuta = br.readLine()) != null) {
                String nombreDestino = lineaRuta.trim();
                
                if (!nombreDestino.isEmpty()) {
                    Usuario uDestino = usuarios.get(nombreDestino);
                    if (uDestino != null) {
                        ruta.add(uDestino);
                    }
                }
            }
        }
    }

    /**
     * Simula la difusión del mensaje a través de la red social.
     */
    private void simularDifusion() {
        for (Usuario destino : ruta) {
            Usuario actual = mensaje.getUsuarioActual();
            Enlace e = actual.getEnlace(destino);

            if (mensaje.difunde(e)) {
                System.out.println(mensaje);
            }
        }
    }

    /**
     * Añade un usuario a la red social.
     * @param nombre El nombre del usuario.
     * @param cap La capacidad de amplificación del usuario.
     */
    public void añadirUsuario(String nombre, int cap) {
        usuarios.put(nombre, new Usuario(nombre, cap));
    }

    /**
     * Añade un enlace entre dos usuarios en la red social.
     * @param origen El nombre del usuario que envía el mensaje.
     * @param destino El nombre del usuario que recibe el mensaje.
     * @param coste El coste del enlace.
     */
    public void añadirEnlace(String origen, String destino, int coste) {
        Usuario uOrigen = usuarios.get(origen);
        Usuario uDestino = usuarios.get(destino);
        if (uOrigen != null && uDestino != null) {
            uOrigen.addEnlace(uDestino, coste);
            this.enlaces.add(uOrigen.getEnlace(uDestino));
        }
    }

    /**
     * Configura el mensaje para la difusión.
     * @param texto El texto del mensaje.
     * @param alcance El alcance del mensaje.
     * @param origen El nombre del usuario que envía el mensaje.
     */
    public void configurarMensaje(String texto, int alcance, String origen) {
        Usuario uOrigen = usuarios.get(origen);
        if (uOrigen != null) {
            this.mensaje = new Mensaje(texto, alcance, uOrigen);
        }
    }

    /**
     * Guarda los datos de la red social en archivos de texto.
     * @param archUsuarios La ruta del archivo que contendrá los usuarios.
     * @param archEnlaces La ruta del archivo que contendrá los enlaces.
     * @param archMensaje La ruta del archivo que contendrá el mensaje.
     * @throws IOException Si ocurre un error al escribir los archivos.
     */
    public void guardarEnFicheros(String archUsuarios, String archEnlaces, String archMensaje) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archUsuarios))) {
            for (Usuario u : usuarios.values()) {
                bw.write(u.getNombre() + " " + u.getCap_Amplificacion());
                bw.newLine(); 
            }
        }
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archEnlaces))) {
            for (Enlace e : enlaces) {
                bw.write(e.getOrigen().getNombre() + " " + e.getDestino().getNombre() + " " + e.getCoste());
                bw.newLine();
            }
        }

        if (this.mensaje != null) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archMensaje))) {
                bw.write("\"" + mensaje.getTexto() + "\" " + mensaje.getAlcance() + " " + mensaje.getUsuarioActual().getNombre());
                bw.newLine();
                
                for (Usuario u : ruta) {
                    bw.write(u.getNombre());
                    bw.newLine();
                }
            }
        }
    }
}