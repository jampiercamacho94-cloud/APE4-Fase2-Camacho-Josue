public class Usuario {

    private final String id;
    private final String nombre;

    public Usuario(String id, String nombre) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El ID del usuario no puede estar vacío.");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del usuario " + id + " no puede estar vacío.");
        }
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return id + " (" + nombre + ")";
    }
}