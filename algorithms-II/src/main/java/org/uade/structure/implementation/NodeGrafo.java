package org.uade.structure.implementation;

public class NodeGrafo<V, P> {
    private V vertice;                 // Ahora guarda cualquier objeto (ej: Terminal)
    private NodeArista<V, P> arista;   // Sublista de aristas genérica
    private NodeGrafo<V, P> sigNodo;   // Siguiente vértice de la lista de inventario

    public NodeGrafo(V vertice) {
        this.vertice = vertice;
        this.arista = null;
        this.sigNodo = null;
    }

    // Getters y Setters parametrizados
    public V getVertice() { return vertice; }
    public void setVertice(V vertice) { this.vertice = vertice; }

    public NodeArista<V, P> getArista() { return arista; }
    public void setArista(NodeArista<V, P> arista) { this.arista = arista; }

    public NodeGrafo<V, P> getSigNodo() { return sigNodo; }
    public void setSigNodo(NodeGrafo<V, P> sigNodo) { this.sigNodo = sigNodo; }
}
