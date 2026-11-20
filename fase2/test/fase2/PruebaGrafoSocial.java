package fase2;

public class PruebaGrafoSocial {
    private static int comprobaciones;

    public static void main(String[] args) {
        probarRegistro();
        probarSecuencia();
        probarTiposDeRelacion();
        probarEliminacion();

        System.out.println("\nTodas las pruebas pasaron.");
        System.out.println("Comprobaciones: " + comprobaciones);
    }

    private static void comprobar(boolean condicion, String mensaje) {
        if (!condicion) {
            throw new AssertionError(mensaje);
        }

        comprobaciones++;
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

    private static void probarRegistro() {
        GrafoSocial red = new GrafoSocial();

        comprobar(red.cantidadUsuarios() == 0, "Red inicialmente vacia");
        comprobar(!red.registrarPersona(null, "Ana"), "Identificador null");
        comprobar(!red.registrarPersona("", "Ana"), "Identificador vacio");
        comprobar(!red.registrarPersona("u 1", "Ana"), "Espacios");
        comprobar(!red.registrarPersona("u,1", "Ana"), "Comas");
        comprobar(!red.registrarPersona("u1", null), "Nombre null");
        comprobar(!red.registrarPersona("u1", " "), "Nombre vacio");
        comprobar(red.cantidadUsuarios() == 0, "Rechazos conservan la red");

        comprobar(red.registrarPersona("U1", "Irene"), "Registro valido");
        comprobar(!red.registrarPersona("u1", "Otra"), "Duplicado normalizado");
        comprobar(red.contarAmigos("u1") == 0, "Busqueda en minusculas");
        comprobar(red.contarAmigos("U1") == 0, "Busqueda en mayusculas");
        comprobar(red.listarAmigos("u9") == null, "Amigos de inexistente");
        comprobar(red.listarSeguidos("u9") == null, "Seguidos de inexistente");
        comprobar(red.listarSeguidores("u9") == null, "Seguidores de inexistente");
        comprobar(red.contarAmigos("u9") == -1, "Conteo de inexistente");

        System.out.println("OK - Registro, validaciones y normalizacion");
    }

    private static void probarSecuencia() {
        GrafoSocial red = crearRed();

        comprobar(red.cantidadUsuarios() == 6, "Seis usuarios");
        comprobar(red.cantidadAmistades() == 5, "Cinco amistades");
        comprobar(red.contarAmigos("u1") == 3, "Tres amigos de Irene");
        comprobar(red.contarAmigos("u6") == 0, "Emilia aislada");

        comprobar(red.seguir("u2", "u1"), "Nicolas sigue a Irene");
        comprobar(red.seguir("u6", "u1"), "Emilia sigue a Irene");
        comprobar(red.listarSeguidores("u1").contains("u2 (Nicolás)"),
                "Nicolas figura como seguidor");
        comprobar(red.listarSeguidores("u1").contains("u6 (Emilia)"),
                "Emilia figura como seguidora");

        comprobar(red.eliminarAmistad("u1", "u5"), "Eliminar amistad");
        comprobar(red.contarAmigos("u5") == 0, "Retirar amistad reciproca");
        comprobar(red.eliminarPersona("u1"), "Eliminar a Irene");
        comprobar(red.cantidadUsuarios() == 5, "Cinco usuarios restantes");
        comprobar(red.cantidadAmistades() == 2, "Dos amistades restantes");
        comprobar(!red.mostrarRed().contains("u1"), "Sin referencias a Irene");
        comprobar(!red.registrarPersona("U2", "Otra"), "Usuario repetido");
        comprobar(!red.agregarAmistad("u3", "u3"), "Lazo rechazado");

        System.out.println("OK - Secuencia de comprobacion de la fase 2");
    }

    private static void probarTiposDeRelacion() {
        GrafoSocial red = crearRed();

        comprobar(red.seguir("u2", "u1"), "Amistad y seguimiento coexisten");
        comprobar(!red.seguir("u2", "u1"), "Seguimiento duplicado");
        comprobar(!red.seguir("u1", "u1"), "Seguimiento a si mismo");
        comprobar(!red.seguir("u1", "u9"), "Destino inexistente");
        comprobar(!red.agregarAmistad("u2", "u1"), "Amistad inversa duplicada");

        comprobar(red.listarSeguidos("u1").equals("[]"),
                "Seguir tiene una sola direccion");
        comprobar(red.dejarDeSeguir("u2", "u1"), "Dejar de seguir");
        comprobar(red.contarAmigos("u1") == 3, "La amistad sigue vigente");
        comprobar(!red.dejarDeSeguir("u2", "u1"), "Seguimiento ya eliminado");

        comprobar(red.seguir("u2", "u1"), "Volver a seguir");
        comprobar(red.eliminarAmistad("u2", "u1"), "Eliminar amistad");
        comprobar(red.listarSeguidos("u2").contains("u1 (Irene)"),
                "El seguimiento sigue vigente");
        comprobar(!red.listarAmigos("u1").contains("u2 (Nicolás)"),
                "Se retira la amistad inversa");
        comprobar(!red.eliminarAmistad("u2", "u1"), "Amistad ya eliminada");

        System.out.println("OK - Direccion, duplicados y tipos independientes");
    }

    private static void probarEliminacion() {
        GrafoSocial red = crearRed();

        comprobar(red.seguir("u2", "u6"), "Relacion hacia la cabeza");
        comprobar(red.eliminarPersona("u6"), "Eliminar primer vertice");
        comprobar(red.listarSeguidos("u2").equals("[]"),
                "Limpiar referencia al primer vertice");

        comprobar(red.eliminarPersona("u3"), "Eliminar vertice intermedio");
        comprobar(!red.mostrarRed().contains("u3"), "Limpiar referencias intermedias");
        comprobar(red.eliminarPersona("u1"), "Eliminar ultimo vertice");
        comprobar(!red.eliminarPersona("u9"), "Eliminar inexistente");

        comprobar(red.eliminarPersona("u2"), "Eliminar Nicolas");
        comprobar(red.eliminarPersona("u4"), "Eliminar Sergio");
        comprobar(red.eliminarPersona("u5"), "Eliminar Valentina");
        comprobar(red.cantidadUsuarios() == 0, "Red vacia");
        comprobar(red.cantidadAmistades() == 0, "Sin amistades");
        comprobar(red.mostrarRed().isEmpty(), "Lista de vertices vacia");
        comprobar(red.registrarPersona("nuevo", "Ana"), "Registrar tras vaciar");

        System.out.println("OK - Eliminacion de vertices y referencias entrantes");
    }
}