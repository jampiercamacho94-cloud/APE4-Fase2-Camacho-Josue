package fase2;

import java.util.Locale;

public class GrafoSocial {
    private Vertice cabeza;

    public boolean registrarPersona(String id, String nombre) {
        if (id == null || id.isBlank()
                || nombre == null || nombre.isBlank()) {
            return false;
        }

        for (int i = 0; i < id.length(); i++) {
            if (Character.isWhitespace(id.charAt(i)) || id.charAt(i) == ',') {
                return false;
            }
        }

        if (buscar(id) != null) {
            return false;
        }

        Usuario usuario = new Usuario(
                id.toLowerCase(Locale.ROOT), nombre.trim());

        Vertice nuevo = new Vertice(usuario);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
        return true;
    }

    private Vertice buscar(String id) {
        if (id == null) {
            return null;
        }

        String clave = id.trim().toLowerCase(Locale.ROOT);

        for (Vertice actual = cabeza;
                actual != null; actual = actual.siguiente) {
            if (actual.usuario.getId().equals(clave)) {
                return actual;
            }
        }

        return null;
    }

    public boolean agregarAmistad(String a, String b) {
        Vertice origen = buscar(a);
        Vertice destino = buscar(b);

        if (origen == null || destino == null || origen == destino) {
            return false;
        }

        if (existe(origen, destino, TipoRelacion.AMISTAD)) {
            return false;
        }

        insertar(origen, destino, TipoRelacion.AMISTAD);
        insertar(destino, origen, TipoRelacion.AMISTAD);
        return true;
    }

    private boolean existe(Vertice origen, Vertice destino,
                           TipoRelacion tipo) {
        for (Arista actual = origen.aristas;
                actual != null; actual = actual.siguiente) {
            if (actual.destino == destino && actual.tipo == tipo) {
                return true;
            }
        }

        return false;
    }

    private void insertar(Vertice origen, Vertice destino,
                          TipoRelacion tipo) {
        Arista nueva = new Arista(destino, tipo);
        nueva.siguiente = origen.aristas;
        origen.aristas = nueva;
    }

    public int cantidadUsuarios() {
        int cantidad = 0;

        for (Vertice actual = cabeza;
                actual != null; actual = actual.siguiente) {
            cantidad++;
        }

        return cantidad;
    }

    public int cantidadAmistades() {
        int aristas = 0;

        for (Vertice usuario = cabeza;
                usuario != null; usuario = usuario.siguiente) {
            for (Arista relacion = usuario.aristas;
                    relacion != null; relacion = relacion.siguiente) {
                if (relacion.tipo == TipoRelacion.AMISTAD) {
                    aristas++;
                }
            }
        }

        return aristas / 2;
    }

    public int contarAmigos(String id) {
        Vertice usuario = buscar(id);

        if (usuario == null) {
            return -1;
        }

        int cantidad = 0;

        for (Arista actual = usuario.aristas;
                actual != null; actual = actual.siguiente) {
            if (actual.tipo == TipoRelacion.AMISTAD) {
                cantidad++;
            }
        }

        return cantidad;
    }

    public String mostrarRed() {
        StringBuilder texto = new StringBuilder();

        for (Vertice usuario = cabeza;
                usuario != null; usuario = usuario.siguiente) {
            texto.append(usuario.usuario).append(" -> [");

            for (Arista relacion = usuario.aristas;
                    relacion != null; relacion = relacion.siguiente) {
                texto.append(relacion.tipo)
                        .append(" ")
                        .append(relacion.destino.usuario.getId());

                if (relacion.siguiente != null) {
                    texto.append(", ");
                }
            }

            texto.append("]\n");
        }

        return texto.toString();
    }
}