package fase2;

public class Main {
    public static void main(String[] args) {
        GrafoSocial red = new GrafoSocial();

        red.registrarPersona("U1", "Irene");
        red.registrarPersona("U2", "Nicolás");
        red.registrarPersona("U3", "Paula");
        red.registrarPersona("U4", "Sergio");
        red.registrarPersona("U5", "Valentina");
        red.registrarPersona("U6", "Emilia");

        red.agregarAmistad("U1", "U2");
        red.agregarAmistad("U2", "U3");
        red.agregarAmistad("U3", "U4");
        red.agregarAmistad("U4", "U1");
        red.agregarAmistad("U1", "U5");

        System.out.println("APE 4 - Fase 2");
        System.out.println("Camacho Monta Josue Jampier");
        System.out.println("Usuarios: " + red.cantidadUsuarios());
        System.out.println("Amistades: " + red.cantidadAmistades());
        System.out.println();
        System.out.print(red.mostrarRed());

        System.out.println("\nComprobaciones");
        System.out.println("Amigos de Irene: " + red.contarAmigos("U1"));
        System.out.println("Amigos de Emilia: " + red.contarAmigos("U6"));
        System.out.println("Usuario inexistente: " + red.contarAmigos("U9"));

        System.out.println("Registrar duplicado: "
                + red.registrarPersona("u1", "Otra persona"));
        System.out.println("Registrar usuario con espacios: "
                + red.registrarPersona("U 7", "Ana"));
        System.out.println("Amistad duplicada: "
                + red.agregarAmistad("U2", "U1"));
        System.out.println("Amistad consigo mismo: "
                + red.agregarAmistad("U3", "U3"));
        System.out.println("Amistad con inexistente: "
                + red.agregarAmistad("U1", "U9"));
    }
}