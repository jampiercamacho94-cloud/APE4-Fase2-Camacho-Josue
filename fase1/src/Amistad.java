public class Amistad {

    private final Usuario usuarioA;
    private final Usuario usuarioB;

    public Amistad(Usuario usuarioA, Usuario usuarioB) {
        if (usuarioA == null || usuarioB == null) {
            throw new IllegalArgumentException("Una amistad necesita dos usuarios.");
        }
        this.usuarioA = usuarioA;
        this.usuarioB = usuarioB;
    }

    public Usuario getUsuarioA() {
        return usuarioA;
    }

    public Usuario getUsuarioB() {
        return usuarioB;
    }
}