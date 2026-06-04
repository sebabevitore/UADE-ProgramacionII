package org.uade.structure.implementation;

public class NodeArista<V, P> {
    private P peso;
    private NodeGrafo<V, P> destino;
    private NodeArista<V, P> sigArista;

    public NodeArista(P peso, NodeGrafo<V, P> destino) {
        this.peso = peso;
        this.destino = destino;
        this.sigArista = null;
    }

    public P getPeso() {
        return peso;
    }

    public void setPeso(P peso) {
        this.peso = peso;
    }

    public NodeGrafo<V, P> getDestino() {
        return destino;
    }

    public void setDestino(NodeGrafo<V, P> destino) {
        this.destino = destino;
    }

    public NodeArista<V, P> getSigArista() {
        return sigArista;
    }

    public void setSigArista(NodeArista<V, P> sigArista) {
        this.sigArista = sigArista;
    }
}
