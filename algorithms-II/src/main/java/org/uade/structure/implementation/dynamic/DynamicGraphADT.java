package org.uade.structure.implementation.dynamic;

import org.uade.Exception.EmptyADTException;
import org.uade.Exception.GenericADTException;
import org.uade.structure.definition.GraphADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.NodeArista;
import org.uade.structure.implementation.NodeGrafo;

public class DynamicGraphADT<V, P> implements GraphADT<V, P> {
    private NodeGrafo<V, P> inicio;

    public DynamicGraphADT() {
        this.inicio = null;
    }

    private NodeGrafo<V, P> buscarNodo(V valor) {
        NodeGrafo<V, P> aux = this.inicio;
        while (aux != null) {
            if (aux.getVertice().equals(valor)) {
                return aux;
            }
            aux = aux.getSigNodo();
        }
        return null;
    }

    @Override
    public SetADT<V> getVertxs() {
        SetADT<V> conjunto = new DynamicSetADT<>();
        NodeGrafo<V, P> aux = this.inicio;
        while (aux != null) {
            conjunto.add(aux.getVertice());
            aux = aux.getSigNodo();
        }
        return conjunto;
    }

    @Override
    public void addVertx(V vertex) {
        if (buscarNodo(vertex) != null) {
            return;
        }
        NodeGrafo<V, P> nuevo = new NodeGrafo<>(vertex);
        nuevo.setSigNodo(this.inicio);
        this.inicio = nuevo;
    }

    @Override
    public void removeVertx(V vertex) {
        if (isEmpty()) return;

        // PASO 1: limpieza de aristas que tengan destino en este vertice
        NodeGrafo<V, P> auxLimpieza = this.inicio;
        while (auxLimpieza != null) {
            removeEdge(auxLimpieza.getVertice(), vertex);
            auxLimpieza = auxLimpieza.getSigNodo();
        }

        // PASO 2: eliminar el vertice

        // Caso A: El nodo a borrar es la cabeza
        if (this.inicio.getVertice().equals(vertex)) {
            this.inicio = this.inicio.getSigNodo();
            return;
        }

        // Caso B: esta en el medio o al final
        NodeGrafo<V, P> auxVertice = this.inicio;
        while (auxVertice.getSigNodo() != null) {
            if (auxVertice.getSigNodo().getVertice().equals(vertex)) {
                auxVertice.setSigNodo(auxVertice.getSigNodo().getSigNodo());
                return;
            }
            auxVertice = auxVertice.getSigNodo();
        }
    }

    @Override
    public void addEdge(V vertxOne, V vertxTwo, P weight) {
        NodeGrafo<V, P> origen = buscarNodo(vertxOne);
        NodeGrafo<V, P> destino = buscarNodo(vertxTwo);
        // ambos nodos tienen que existir
        if (origen != null && destino != null) {
            NodeArista<V, P> nuevaArista = new NodeArista<>(weight, destino);
            // La insertamos al principio de la lista de aristas del nodo origen
            nuevaArista.setSigArista(origen.getArista());
            origen.setArista(nuevaArista);
        }
    }

    @Override
    public void removeEdge(V vertxOne, V vertxTwo) {
        NodeGrafo<V, P> origen = buscarNodo(vertxOne);
        if (origen == null || origen.getArista() == null) return;

        // Caso A: La arista a borrar es la primera de la lista
        if (origen.getArista().getDestino().getVertice().equals(vertxTwo)) {
            origen.setArista(origen.getArista().getSigArista());
            return;
        }

        // Caso B: Está más adelante en la lista de aristas
        NodeArista<V, P> auxArista = origen.getArista();
        while (auxArista.getSigArista() != null) {
            if (auxArista.getSigArista().getDestino().getVertice().equals(vertxTwo)) {
                auxArista.setSigArista(auxArista.getSigArista().getSigArista());
                return;
            }
            auxArista = auxArista.getSigArista();
        }
    }

    @Override
    public boolean existsEdge(V vertxOne, V vertxTwo) {
        if (isEmpty()) {
            throw new EmptyADTException("Graph is empty");
        }
        NodeGrafo<V, P> origen = buscarNodo(vertxOne);
        if (origen != null) {
            NodeArista<V, P> aux = origen.getArista();
            while (aux != null) {
                if (aux.getDestino().getVertice().equals(vertxTwo)) {
                    return true;
                }
                aux = aux.getSigArista();
            }
        }
        return false;
    }

    @Override
    public P edgeWeight(V vertxOne, V vertxTwo) {
        if (isEmpty()) throw new EmptyADTException("Graph is empty");

        NodeGrafo<V, P> origen = buscarNodo(vertxOne);
        if (origen != null) {
            NodeArista<V, P> auxArista = origen.getArista();
            while (auxArista != null) {
                if (auxArista.getDestino().getVertice().equals(vertxTwo)) {
                    return auxArista.getPeso();
                }
                auxArista = auxArista.getSigArista();
            }
        }
        throw new GenericADTException("Edge does not exist");
    }

    @Override
    public boolean isEmpty() {
        return this.inicio == null;
    }
}
