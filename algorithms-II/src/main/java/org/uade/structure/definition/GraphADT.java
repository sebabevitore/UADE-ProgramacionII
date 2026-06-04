package org.uade.structure.definition;

// Esta interfaz representa el TDA Grafo.
public interface GraphADT<V, P> {

    /**
     * Descripcion: Retorna el conjunto de vertices. Precondición: No tiene.
     */
    SetADT<V> getVertxs();

    /**
     * Descripcion: Agrega un nuevo vertice al grafo. Precondición: No tiene.
     */
    void addVertx(V vertex);

    /**
     * Descripcion: Eliminar un vertice del grafo. Precondición: No tiene.
     */
    void removeVertx(V vertex);

    /**
     * Descripcion: Agrega una nueva arista al grafo. Precondición: No tiene.
     */
    void addEdge(V vertxOne, V vertxTwo, P weight);

    /**
     * Descripcion: Eliminar una arista del grafo. Precondición: No tiene.
     */
    void removeEdge(V vertxOne, V vertxTwo);

    /**
     * Descripcion: Comprueba si existe o no una arista en el grafo. Precondición: Debe tener elementos el grafo.
     */
    boolean existsEdge(V vertxOne, V vertxTwo);

    /**
     * Descripcion: Devuelve el peso de la arista entre dos vertices. Precondición: Debe tener elementos el grafo.
     */
    P edgeWeight(V vertxOne, V vertxTwo);

    /**
     * Descripcion: Debe comprobar si la estructura tiene o no valores. Precondición: No tiene.
     */
    boolean isEmpty();
}
