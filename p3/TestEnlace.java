public class TestEnlace {

    public static void main(String[] args) {

        System.out.println("===== CREACIÓN DE USUARIOS =====");

        Usuario ana = new Usuario("ana", 10);
        Usuario luis = new Usuario("luis", 5);
        Usuario carmen = new Usuario("carmen");

        System.out.println(ana);
        System.out.println(luis);
        System.out.println(carmen);


        System.out.println("\n===== CONSTRUCTOR CON COSTE =====");

        Enlace e1 = new Enlace(ana, luis, 20);
        System.out.println(e1);

        System.out.println("Origen: " + e1.getOrigen().getNombre());
        System.out.println("Destino: " + e1.getDestino().getNombre());
        System.out.println("Coste: " + e1.getCoste());


        System.out.println("\n===== CONSTRUCTOR SIN COSTE =====");

        Enlace e2 = new Enlace(ana, carmen);
        System.out.println(e2);

        System.out.println("Coste esperado = 1");
        System.out.println("Coste real: " + e2.getCoste());


        System.out.println("\n===== COSTE NEGATIVO O CERO =====");

        Enlace e3 = new Enlace(ana, luis, 0);
        Enlace e4 = new Enlace(ana, luis, -5);

        System.out.println(e3);
        System.out.println(e4);

        System.out.println("Coste e3 (esperado 1): " + e3.getCoste());
        System.out.println("Coste e4 (esperado 1): " + e4.getCoste());


        System.out.println("\n===== CAMBIAR DESTINO =====");

        System.out.println("Antes:");
        System.out.println(e1);

        e1.cambiarDestino(carmen, 30);

        System.out.println("Después:");
        System.out.println(e1);

        System.out.println("Nuevo destino: " + e1.getDestino().getNombre());
        System.out.println("Nuevo coste: " + e1.getCoste());


        System.out.println("\n===== CAMBIAR DESTINO CON COSTE INVÁLIDO =====");

        e1.cambiarDestino(luis, -10);

        System.out.println("Coste esperado = 1");
        System.out.println(e1);


        System.out.println("\n===== COSTE ESPECIAL Y COSTE REAL =====");

        System.out.println("Coste base: " + e1.getCoste());
        System.out.println("Coste especial: " + e1.costeEspecial());
        System.out.println("Coste real: " + e1.costeReal());


        System.out.println("\n===== TEST FINAL DE TOSTRING =====");

        System.out.println(e1);
        System.out.println(e2);
        System.out.println(e3);
        System.out.println(e4);

    }
}
