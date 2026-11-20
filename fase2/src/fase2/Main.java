package fase2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner entrada = new Scanner(System.in)) {
            boolean salir = false;

            while (!salir) {
                System.out.println("\n=== APE 4 - Fase 2 ===");
                System.out.println("Camacho Monta Josue Jampier");
                System.out.println("1. Registro y red inicial");
                System.out.println("2. Amistades y seguimientos");
                System.out.println("3. Eliminacion de relaciones y personas");
                System.out.println("4. Consultas y conteos");
                System.out.println("0. Salir");
                System.out.print("Opcion: ");

                if (!entrada.hasNextLine()) {
                    break;
                }

                switch (entrada.nextLine().trim()) {
                    case "1" -> mostrarRegistro();
                    case "2" -> mostrarRelaciones();
                    case "3" -> mostrarEliminaciones();
                    case "4" -> mostrarConsultas();
                    case "0" -> salir = true;
                    default -> System.out.println("Opcion invalida.");
                }
            }
        }
    }

    private static GrafoSocial crearRed() {
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

        return red;
    }

    private static void mostrarRegistro() {
        GrafoSocial red = crearRed();

        System.out.println("\nREGISTRO Y RED INICIAL");
        System.out.println("Usuarios registrados: " + red.cantidadUsuarios());
        System.out.println("Amistades: " + red.cantidadAmistades());
        System.out.print(red.mostrarRed());

        System.out.println("Registrar U1 nuevamente: "
                + red.registrarPersona("u1", "Otra persona"));
        System.out.println("Registrar identificador con espacios: "
                + red.registrarPersona("U 7", "Ana"));
        System.out.println("Registrar identificador con coma: "
                + red.registrarPersona("U,7", "Ana"));
    }

    private static void mostrarRelaciones() {
        GrafoSocial red = crearRed();

        System.out.println("\nAMISTADES Y SEGUIMIENTOS");
        System.out.println("Nicolas sigue a Irene: "
                + red.seguir("U2", "U1"));
        System.out.println("Emilia sigue a Irene: "
                + red.seguir("U6", "U1"));
        System.out.println("Repetir seguimiento: "
                + red.seguir("U2", "U1"));
        System.out.println("Seguirse a si mismo: "
                + red.seguir("U3", "U3"));
        System.out.println("Repetir amistad inversa: "
                + red.agregarAmistad("U2", "U1"));

        System.out.print(red.mostrarRed());
        System.out.println("Amistades: " + red.cantidadAmistades());
    }

    private static void mostrarEliminaciones() {
        GrafoSocial red = crearRed();
        red.seguir("U2", "U1");
        red.seguir("U6", "U1");

        System.out.println("\nELIMINACION DE RELACIONES Y PERSONAS");
        System.out.println("Nicolas deja de seguir a Irene: "
                + red.dejarDeSeguir("U2", "U1"));
        System.out.println("Amigos de Irene: " + red.contarAmigos("U1"));

        System.out.println("Eliminar amistad Irene-Valentina: "
                + red.eliminarAmistad("U1", "U5"));
        System.out.println("Amigos de Irene: " + red.contarAmigos("U1"));
        System.out.println("Amigos de Valentina: " + red.contarAmigos("U5"));

        System.out.println("Eliminar a Irene: "
                + red.eliminarPersona("U1"));
        System.out.println("Eliminarla nuevamente: "
                + red.eliminarPersona("U1"));

        System.out.print(red.mostrarRed());
        System.out.println("Usuarios restantes: " + red.cantidadUsuarios());
        System.out.println("Amistades restantes: " + red.cantidadAmistades());
    }

    private static void mostrarConsultas() {
        GrafoSocial red = crearRed();
        red.seguir("U2", "U1");
        red.seguir("U6", "U1");

        System.out.println("\nCONSULTAS Y CONTEOS");
        System.out.println("Amigos de Irene: " + red.listarAmigos("U1"));
        System.out.println("Seguidos de Emilia: " + red.listarSeguidos("U6"));
        System.out.println("Seguidores de Irene: " + red.listarSeguidores("U1"));
        System.out.println("Cantidad de amigos de Irene: "
                + red.contarAmigos("U1"));

        System.out.println("Amigos de Emilia: " + red.listarAmigos("U6"));
        System.out.println("Cantidad de amigos de Emilia: "
                + red.contarAmigos("U6"));
        System.out.println("Consulta de usuario inexistente: "
                + red.listarAmigos("U9"));
        System.out.println("Conteo de usuario inexistente: "
                + red.contarAmigos("U9"));
    }
}