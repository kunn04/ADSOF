package src;

/**
 * Esta clase representa una serie de pruebas para el apartado 6 del proyecto, que incluye tests para la exposición de usuarios, enlaces señuelo, usuarios interesados y mensajes controlados. 
 * Autor: Adrián Gómez y Javier Agüero (adrian.gomezretamal@estudiante.uam.es y javier.aguero@estudiante.uam.es)
 * Version: 1.0
 * Nombre del fichero: TestApartado6.java
 */
public class TestApartado6 {
    public static void main(String[] args) {
        
        System.out.println("=== 1. TEST DE EXPOSICIÓN Y MEMORIA ===");
        Usuario influencer = new Usuario("influencer", 5, Exposicion.MEDIA);
        System.out.println("Exposición inicial (esperado MEDIA): " + influencer.getExposicion());
        
        Mensaje mTop = new Mensaje("Viral", 100, new Usuario("anonimo"));
        influencer.recibirMensaje(mTop);
        System.out.println("Tras recibir mensaje Top (esperado ALTA): " + influencer.getExposicion());
        
        Mensaje mPobre = new Mensaje("Flojo", 5, new Usuario("anonimo"));
        influencer.recibirMensaje(mPobre);
        System.out.println("Tras recibir mensaje pobre (esperado MEDIA): " + influencer.getExposicion());


        System.out.println("\n=== 2. TEST DE ENLACE SEÑUELO ===");
        Usuario victima = new Usuario("victima", 2);
        Usuario estafador = new Usuario("estafador", 1);
        
        EnlaceSenuelo trampa = new EnlaceSenuelo(victima, estafador, 10, 3, 1.0);
        System.out.println(trampa);
        System.out.println("Coste real (10 base + 30 especial = 40): " + trampa.costeReal());
        System.out.println("Destino real por 100% de retorno (esperado victima): " + trampa.getDestino().getNombre());


        System.out.println("\n=== 3. TEST DE USUARIO INTERESADO ===");
        UsuarioInteresado trepa = new UsuarioInteresado("trepa", 3);
        Usuario normal = new Usuario("normal", 2, Exposicion.BAJA);
        Usuario famoso = new Usuario("famoso", 5, Exposicion.VIRAL);
        
        trepa.addEnlace(normal, 5);
        trepa.addEnlace(famoso, 10);
        
        System.out.println("Enlace elegido hacia 'normal' (esperado famoso): " + trepa.getEnlace(normal).getDestino().getNombre());


        System.out.println("\n=== 4. TEST DE MENSAJE CONTROLADO ===");
        Usuario emisor = new Usuario("gobierno", 5);
        Usuario ciudadanoBajo = new Usuario("ciudadanoBaja", 2, Exposicion.BAJA);
        Usuario ciudadanoAlto = new Usuario("ciudadanoAlta", 2, Exposicion.ALTA);
        
        emisor.addEnlace(ciudadanoBajo, 5);
        emisor.addEnlace(ciudadanoAlto, 5);
        
        MensajeControlado mEstricto = new MensajeControlado("Secreto", 100, emisor, 20);
        
        System.out.println(mEstricto);
        System.out.println("Intento difusión a Exposición BAJA (esperado false): " + mEstricto.difunde(emisor.getEnlace(ciudadanoBajo)));
        System.out.println("Intento difusión a Exposición ALTA (esperado true): " + mEstricto.difunde(emisor.getEnlace(ciudadanoAlto)));
        
        EnlaceSenuelo enlaceFalso = new EnlaceSenuelo(ciudadanoAlto, emisor, 5, 1, 0.0);
        System.out.println("Intento difusión por Enlace Señuelo (esperado false): " + mEstricto.difunde(enlaceFalso));
    }
}