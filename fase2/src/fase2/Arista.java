package fase2;

final class Arista {
    final Vertice destino;
    final TipoRelacion tipo;
    Arista siguiente;

    Arista(Vertice destino, TipoRelacion tipo) {
        this.destino = destino;
        this.tipo = tipo;
    }
}