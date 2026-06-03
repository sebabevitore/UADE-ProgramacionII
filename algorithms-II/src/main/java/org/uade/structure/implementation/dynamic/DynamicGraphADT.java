package org.uade.structure.implementation.dynamic;

import org.uade.Exception.EmptyADTException;
import org.uade.Exception.GenericADTException;
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
        SetADT conjunto = new DynamicSetADT();
        NodoGrafo aux = this.inicio;
        while (aux != null) {
            conjunto.add(aux.getNodo());
            aux = aux.getSigNodo();
        }
        return conjunto;
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
        if (isEmpty()) return;

        // PASO 1: limpieza de aristas que tengan destino en este vertice
        NodoGrafo auxLimpieza = this.inicio;
        while (auxLimpieza != null) {
            removeEdge(auxLimpieza.getNodo(), vertex);
            auxLimpieza = auxLimpieza.getSigNodo();
        }

        // PASO 2: eliminar el vertice

        // Caso A: El nodo a borrar es la cabeza
        if (this.inicio.getNodo() == vertex) {
            this.inicio = this.inicio.getSigNodo();
            return;
        }

        // Caso B: esta en el medio o al final
        NodoGrafo auxVertice = this.inicio;
        while (auxVertice.getSigNodo() != null) {
            if (auxVertice.getSigNodo().getNodo() == vertex) {
                auxVertice.setSigNodo(auxVertice.getSigNodo().getSigNodo());
                return;
            }
            auxVertice = auxVertice.getSigNodo();
        }
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
        NodoGrafo origen = buscarNodo(vertxOne);
        if (origen == null || origen.getArista() == null) return;

        // Caso A: La arista a borrar es la primera de la lista
        if (origen.getArista().getNodoDestino().getNodo() == vertxTwo) {
            origen.setArista(origen.getArista().getSigArista());
            return;
        }

        // Caso B: Está más adelante en la lista de aristas
        NodoArista auxArista = origen.getArista();
        while (auxArista.getSigArista() != null) {
            if (auxArista.getSigArista().getNodoDestino().getNodo() == vertxTwo) {
                auxArista.setSigArista(auxArista.getSigArista().getSigArista());
                return;
            }
            auxArista = auxArista.getSigArista();
        }
    }

    @Override
    public boolean existsEdge(int vertxOne, int vertxTwo) {
        if (isEmpty()){
            throw new EmptyADTException("Graph is empty");
        }
        NodoGrafo origen = buscarNodo(vertxOne);
        if (origen != null) {
            NodoArista aux = origen.getArista();
            while (aux != null) {
                if(aux.getNodoDestino().getNodo() == vertxTwo){
                    return true;
                }
                aux = aux.getSigArista();
            }
        }
        return false;
    }

    //TODO: preguntarle al profe sobre:
    // si cuando NO existe una arista entre esos dos vertices,
    // se tiene que devolver 0 o lanzar la excepcion.
    @Override
    public int edgeWeight(int vertxOne, int vertxTwo) {
        if (isEmpty()) throw new EmptyADTException("Graph is empty");

        NodoGrafo origen = buscarNodo(vertxOne);
        if (origen != null) {
            NodoArista auxArista = origen.getArista();
            while (auxArista != null) {
                if (auxArista.getNodoDestino().getNodo() == vertxTwo) {
                    return auxArista.getEtiqueta();
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
