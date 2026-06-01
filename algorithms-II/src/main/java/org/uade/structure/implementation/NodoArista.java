package org.uade.structure.implementation;

public class NodoArista {
    int etiqueta;
    NodoGrafo nodoDestino;
    NodoArista sigArista;


    public NodoArista(int etiqueta, NodoGrafo nodoDestino) {
        this.etiqueta = etiqueta;
        this.nodoDestino = nodoDestino;
        this.sigArista = null;
    }

    public int getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(int etiqueta) {
        this.etiqueta = etiqueta;
    }

    public NodoGrafo getNodoDestino() {
        return nodoDestino;
    }

    public void setNodoDestino(NodoGrafo nodoDestino) {
        this.nodoDestino = nodoDestino;
    }

    public NodoArista getSigArista() {
        return sigArista;
    }

    public void setSigArista(NodoArista sigArista) {
        this.sigArista = sigArista;
    }
}

