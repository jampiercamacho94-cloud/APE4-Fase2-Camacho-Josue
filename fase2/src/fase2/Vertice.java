package fase2;

final class Vertice {
    final Usuario usuario;
    Arista aristas;
    Vertice siguiente;

    Vertice(Usuario usuario) {
        this.usuario = usuario;
    }
}