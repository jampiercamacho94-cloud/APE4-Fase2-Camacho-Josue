/**
 * APE 4 - Fase 1: Grafos y vértices.
 * Estructura de Datos - Entrega individual.
 * Autor: Camacho Monta Josue Jampier
 *
 * Carga el ejemplo, ejecuta las pruebas e imprime los resultados.
 * Toda la impresión está aquí; GrafoSocial solo guarda datos y valida reglas.
 */
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GrafoSocial red = cargarRed();
        Scanner entrada = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            if (!entrada.hasNextLine()) {
                break;
            }
            String texto = entrada.nextLine().trim();
            switch (texto) {
                case "1" -> opcionPersonasYAmistades(red);
                case "2" -> opcionGrados(red);
                case "3" -> opcionConsultas(red);
                case "4" -> opcionRechazos(red);
                case "5" -> {
                    System.out.println("Hasta luego.");
                    salir = true;
                }
                default -> System.out.println("Opción inválida: \"" + texto + "\". Escribe un número del 1 al 5.");
            }
        }
        entrada.close();
    }

    private static GrafoSocial cargarRed() {
        GrafoSocial red = new GrafoSocial();
        red.agregarUsuario("U1", "Irene");
        red.agregarUsuario("U2", "Nicolás");
        red.agregarUsuario("U3", "Paula");
        red.agregarUsuario("U4", "Sergio");
        red.agregarUsuario("U5", "Valentina");
        red.agregarUsuario("U6", "Emilia");
        red.agregarAmistad("U1", "U2");
        red.agregarAmistad("U2", "U3");
        red.agregarAmistad("U3", "U4");
        red.agregarAmistad("U4", "U1");
        red.agregarAmistad("U1", "U5");
        return red;
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("=== APE 4 - Fase 1: Grafos y vértices ===");
        System.out.println("Autor: Camacho Monta Josue Jampier");
        System.out.println("1) Mostrar usuarios y amistades");
        System.out.println("2) Mostrar grados, vecinos de Irene y grado de Emilia");
        System.out.println("3) Consultas de amistad y comprobación del ciclo");
        System.out.println("4) Demostrar los tres rechazos");
        System.out.println("5) Salir");
        System.out.print("Elige una opción: ");
    }

    private static void opcionPersonasYAmistades(GrafoSocial red) {
        System.out.println();
        System.out.println("Personas (" + red.cantidadUsuarios() + " vértices)");
        for (Usuario u : red.getUsuarios()) {
            System.out.println("  " + u.getId() + " = " + u.getNombre());
        }
        System.out.println();
        System.out.println("Amistades (" + red.cantidadAmistades() + " aristas)");
        int n = 1;
        for (Amistad a : red.getAmistades()) {
            Usuario x = a.getUsuarioA();
            Usuario y = a.getUsuarioB();
            System.out.println("  " + n + ") " + x.getId() + "-" + y.getId()
                + "  (" + x.getNombre() + " - " + y.getNombre() + ")");
            n++;
        }
    }

    private static void opcionGrados(GrafoSocial red) {
        System.out.println();
        System.out.println("Grados");
        Usuario mayor = null;
        for (Usuario u : red.getUsuarios()) {
            System.out.println("  grado(" + u.getId() + ") = " + red.grado(u.getId()) + "  " + u.getNombre());
            if (mayor == null || red.grado(u.getId()) > red.grado(mayor.getId())) {
                mayor = u;
            }
        }
        System.out.println("  Suma de grados = " + red.sumaGrados()
            + " | 2 x aristas = " + (2 * red.cantidadAmistades())
            + " | coincide: " + (red.sumaGrados() == 2 * red.cantidadAmistades()));
        System.out.println("  Mayor grado: " + mayor + " con " + red.grado(mayor.getId()));
        System.out.println();
        imprimirVecinos(red, "U1");
        System.out.println("  grado(U6) = " + red.grado("U6") + "  (Emilia, aislada)");
        imprimirVecinos(red, "U6");
    }

    private static void opcionConsultas(GrafoSocial red) {
        System.out.println();
        System.out.println("Consultas de amistad");
        System.out.println("  existeRelacion(U1, U2) = " + red.existeRelacion("U1", "U2") + "   (verdadera)");
        System.out.println("  existeRelacion(U2, U4) = " + red.existeRelacion("U2", "U4") + "  (falsa)");
        System.out.println("  existeRelacion(U2, U1) = " + red.existeRelacion("U2", "U1") + "   (recíproca de U1-U2)");
        System.out.println();
        System.out.println("Verificación del ciclo Irene -> Nicolás -> Paula -> Sergio -> Irene");
        System.out.println("  U1-U2 = " + red.existeRelacion("U1", "U2")
            + " | U2-U3 = " + red.existeRelacion("U2", "U3")
            + " | U3-U4 = " + red.existeRelacion("U3", "U4")
            + " | U4-U1 = " + red.existeRelacion("U4", "U1"));
    }

    private static void opcionRechazos(GrafoSocial red) {
        System.out.println();
        System.out.println("Rechazos (el grafo no debe cambiar)");
        intentarAmistad(red, "U3", "U3");
        intentarAmistad(red, "U2", "U1");
        intentarAmistad(red, "U1", "U9");
    }

    private static void imprimirVecinos(GrafoSocial red, String id) {
        StringBuilder sb = new StringBuilder();
        for (String v : red.vecinos(id)) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(red.obtenerUsuario(v));
        }
        System.out.println("  vecinos(" + id + ") = [" + sb + "]");
    }

    private static void intentarAmistad(GrafoSocial red, String a, String b) {
        String antes = red.instantanea();
        System.out.println("  agregarAmistad(" + a + ", " + b + ")");
        try {
            red.agregarAmistad(a, b);
            System.out.println("    ACEPTADA");
        } catch (IllegalArgumentException e) {
            System.out.println("    RECHAZADA: " + e.getMessage());
        }
        boolean intacto = antes.equals(red.instantanea());
        System.out.println("    grafo intacto: " + intacto
            + " | amistades = " + red.cantidadAmistades()
            + " | suma de grados = " + red.sumaGrados());
    }
}