package org.uade.algorithm.graph;

import org.uade.structure.definition.BinaryTreeADT;
import org.uade.structure.definition.GraphADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.dynamic.DynamicBinaryTreeADT;
import org.uade.structure.implementation.dynamic.DynamicSetADT;

import static org.uade.algorithm.graph.AdditionalGraphExcercise73.conversion;
import static org.uade.util.SetADTUtil.print;

public class AdditionalGraphExcercise74 {

    // Paso 1: El Wrapper
    public static boolean tieneEstructuraDeArbol(GraphADT grafo, int nodoRaiz) {
        SetADT visitados = new DynamicSetADT();
        return recursivaDFS(grafo, nodoRaiz, visitados);
    }

    private static boolean recursivaDFS(GraphADT grafo, int nodo, SetADT visitados) {
        // Paso 2: El Freno (El detector de ciclos)
        if (visitados.exist(nodo)) {
            return false; // ¡Encontramos un ciclo o un nodo con dos padres!
        }

        // Paso 3: Lógica del nodo (Dejar la miga de pan)
        visitados.add(nodo);

        // Paso 4: Delegación (Buscando vecinos "a mano")
        SetADT todosLosNodos = grafo.getVertxs();

        while (!todosLosNodos.isEmpty()) {
            int posibleVecino = todosLosNodos.choose();
            todosLosNodos.remove(posibleVecino);

            // Verificamos si realmente hay una flecha que va desde nuestro nodo hacia el posible vecino
            if (grafo.existsEdge(nodo, posibleVecino)) {

                // Es un vecino real, así que disparamos la recursión hacia él
                if (!recursivaDFS(grafo, posibleVecino, visitados)) {
                    return false; // Si esa rama falla, falla todo
                }
            }
        }

        // Si recorrimos todos los caminos y nunca nos chocamos con un nodo visitado, es un árbol.
        return true;
    }

    public static void main(String[] args) {
        BinaryTreeADT arbol = new DynamicBinaryTreeADT();

        arbol.add(10);
        arbol.add(20);
        arbol.add(3);
        arbol.add(2);
        arbol.add(6);
        arbol.add(15);
        arbol.add(25);

        GraphADT graph = conversion(arbol);
        print(graph.getVertxs());

        System.out.println(tieneEstructuraDeArbol(graph, 2));

    }
}