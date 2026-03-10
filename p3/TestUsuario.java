public class TestUsuario {

    public static void main(String[] args) {

        System.out.println("===== CREACIÓN DE USUARIOS =====");

        Usuario ana = new Usuario("ana", 10);
        Usuario luis = new Usuario("luis", 5);
        Usuario carmen = new Usuario("carmen"); // capacidad por defecto
        Usuario pedro = new Usuario("pedro", 3);

        System.out.println(ana);
        System.out.println(luis);
        System.out.println(carmen);
        System.out.println(pedro);


        System.out.println("\n===== AÑADIR ENLACES CON OBJETO =====");

        Enlace e1 = new Enlace(ana, luis, 20);
        Enlace e2 = new Enlace(ana, carmen, 15);

        System.out.println("Añadir e1: " + ana.addEnlace(e1));
        System.out.println("Añadir e2: " + ana.addEnlace(e2));

        System.out.println(ana);


        System.out.println("\n===== AÑADIR ENLACE CON SOBRECARGA =====");

        System.out.println("Añadir enlace a pedro:");
        System.out.println(ana.addEnlace(pedro, 30));

        System.out.println(ana);


        System.out.println("\n===== EVITAR DUPLICADOS =====");

        System.out.println("Intentar añadir otro enlace a luis:");
        System.out.println(ana.addEnlace(luis, 50)); // debería ser false

        System.out.println(ana);


        System.out.println("\n===== EVITAR AUTORREFERENCIA =====");

        System.out.println("Intentar enlace de ana a ana:");
        System.out.println(ana.addEnlace(ana, 10)); // false


        System.out.println("\n===== NÚMERO DE ENLACES =====");

        System.out.println("Número de enlaces de ana:");
        System.out.println(ana.getNumEnlaces());


        System.out.println("\n===== ACCESO POR POSICIÓN =====");

        System.out.println("Enlace 0:");
        System.out.println(ana.getEnlace(0));

        System.out.println("Enlace 1:");
        System.out.println(ana.getEnlace(1));

        System.out.println("Enlace 2:");
        System.out.println(ana.getEnlace(2));


        System.out.println("\n===== ACCESO POR DESTINO =====");

        System.out.println("Buscar enlace a luis:");
        System.out.println(ana.getEnlace(luis));

        System.out.println("Buscar enlace a carmen:");
        System.out.println(ana.getEnlace(carmen));

        System.out.println("Buscar enlace inexistente:");
        System.out.println(ana.getEnlace(new Usuario("fantasma")));


        System.out.println("\n===== ORDEN DE CREACIÓN =====");

        for (int i = 0; i < ana.getNumEnlaces(); i++) {
            System.out.println("Enlace " + i + ": " + ana.getEnlace(i));
        }


        System.out.println("\n===== ESTADO FINAL =====");

        System.out.println(ana);

    }
}