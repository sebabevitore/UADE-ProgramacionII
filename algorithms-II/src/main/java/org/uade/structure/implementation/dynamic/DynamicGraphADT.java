package org.uade.structure.implementation.dynamic;

import org.uade.structure.definition.GraphADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.NodoArista;
import org.uade.structure.implementation.NodoGrafo;

public class DynamicGraphADT implements GraphADT {
    private NodoGrafo inicio;

    public DynamicGraphADT() {
        this.inicio = null;
    }

    private NodoGrafo buscarNodo(int valor) {
        NodoGrafo aux = this.inicio;
        while (aux != null) {
            if (aux.getNodo() == valor) {
                return aux;
            }
            aux = aux.getSigNodo();
        }
        return null;
    }

    @Override
    public SetADT getVertxs() {
        return null;
    }

    @Override
    public void addVertx(int vertex) {
        if (buscarNodo(vertex) != null) {
            return;
        }
        NodoGrafo nuevo = new NodoGrafo(vertex);
        nuevo.setSigNodo(this.inicio);
        this.inicio = nuevo;
    }

    @Override
    public void removeVertx(int vertex) {

    }

    @Override
    public void addEdge(int vertxOne, int vertxTwo, int weight) {
        NodoGrafo origen = buscarNodo(vertxOne);
        NodoGrafo destino = buscarNodo(vertxTwo);
        // ambos nodos tienen que existir
        if (origen != null && destino != null) {
            NodoArista nuevaArista = new NodoArista(weight,destino);
            // La insertamos al principio de la lista de aristas del nodo origen
            nuevaArista.setSigArista(origen.getArista());
            origen.setArista(nuevaArista);
        }
    }

    @Override
    public void removeEdge(int vertxOne, int vertxTwo) {

    }

    @Override
    public boolean existsEdge(int vertxOne, int vertxTwo) {
        return false;
    }

    @Override
    public int edgeWeight(int vertxOne, int vertxTwo) {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return this.inicio == null;
    }
}
