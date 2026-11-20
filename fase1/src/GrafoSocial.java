import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GrafoSocial {

    private final Map<String, Usuario> usuarios = new LinkedHashMap<>();

    private final Map<String, Set<String>> adyacencia = new LinkedHashMap<>();

    private final List<Amistad> amistades = new ArrayList<>();

    // ---------------------------------------------------------------
    // Construcción del grafo
    // ---------------------------------------------------------------

    public void agregarUsuario(String id, String nombre) {
        Usuario nuevo = new Usuario(id, nombre); // valida ID y nombre vacíos
        if (usuarios.containsKey(id)) {
            throw new IllegalArgumentException("El usuario " + id + " ya existe en la red.");
        }
        usuarios.put(id, nuevo);
        adyacencia.put(id, new LinkedHashSet<>());
    }

    public void agregarAmistad(String a, String b) {
        validarUsuario(a);
        validarUsuario(b);

        if (a.equals(b)) {
            throw new IllegalArgumentException(
                "No se permiten lazos: " + a + " no puede ser amigo de sí mismo.");
        }

        if (adyacencia.get(a).contains(b)) {
            throw new IllegalArgumentException(
                "La amistad " + a + "-" + b + " ya está registrada (el orden no importa: "
                + b + "-" + a + " es la misma amistad).");
        }

        adyacencia.get(a).add(b);
        adyacencia.get(b).add(a);
        amistades.add(new Amistad(usuarios.get(a), usuarios.get(b)));
    }

    // ---------------------------------------------------------------
    // Consultas
    // ---------------------------------------------------------------

    public boolean existeRelacion(String origen, String destino) {
        validarUsuario(origen);
        validarUsuario(destino);
        return adyacencia.get(origen).contains(destino);
    }

    public Set<String> vecinos(String id) {
        validarUsuario(id);
        return Collections.unmodifiableSet(adyacencia.get(id)); // solo lectura
    }

    public int grado(String id) {
        validarUsuario(id);
        return adyacencia.get(id).size();
    }

    public int cantidadUsuarios() {
        return usuarios.size();
    }

    public int cantidadAmistades() {
        return amistades.size();
    }

    public int sumaGrados() {
        int suma = 0;
        for (String id : usuarios.keySet()) {
            suma += grado(id);
        }
        return suma;
    }

    public Usuario obtenerUsuario(String id) {
        validarUsuario(id);
        return usuarios.get(id);
    }

    public Collection<Usuario> getUsuarios() {
        return Collections.unmodifiableCollection(usuarios.values());
    }

    public List<Amistad> getAmistades() {
        return Collections.unmodifiableList(amistades);
    }

    public String instantanea() {
        StringBuilder sb = new StringBuilder();
        for (String id : usuarios.keySet()) {
            sb.append(id).append("=").append(adyacencia.get(id)).append(";");
        }
        sb.append("E=").append(amistades.size());
        return sb.toString();
    }

    // ---------------------------------------------------------------
    // Apoyo interno
    // ---------------------------------------------------------------

    private void validarUsuario(String id) {
        if (id == null || !usuarios.containsKey(id)) {
            throw new IllegalArgumentException("El usuario " + id + " no existe en la red.");
        }
    }
}