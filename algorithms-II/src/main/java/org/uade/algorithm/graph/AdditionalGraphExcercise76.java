package org.uade.algorithm.graph;

import org.uade.structure.definition.BinaryTreeADT;
import org.uade.structure.definition.GraphADT;
import org.uade.structure.definition.MultipleDictionaryADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.dynamic.DynamicMultipleDictionaryADT;
import org.uade.structure.implementation.dynamic.DynamicSetADT;

public class AdditionalGraphExcercise76 {
    //76. Implementar un metodo que reciba un arbol y un grafo para determinar:
    //  1. Si tienen la misma cantidad de nodos.
    //  2. Si todos los nodos del árbol están presentes en el grafo.
    //  3. Si el grafo contiene las mismas conexiones padre-hijo que el árbol.

    /*
        1. Si tienen la misma cantidad de nodos.
    */
    private static boolean mismaCantNodos(GraphADT grafo, BinaryTreeADT arbol) {
        if(arbol.isEmpty() && grafo.isEmpty()) return true;
        else if(arbol.isEmpty() || grafo.isEmpty()) return false;

        int nodosGrafo = contadorNodosGrafo(grafo);
        int nodosArbol = contadorNodosArbol(arbol);

        return nodosGrafo == nodosArbol;
    }

    private static int contadorNodosGrafo(GraphADT grafo) {
        if(grafo.isEmpty()) return 0;
        SetADT vertices = grafo.getVertxs();
        int contador = 0;
        while(!vertices.isEmpty()){
            int elemento = vertices.choose();
            vertices.remove(elemento);
            contador++;
        }
        return contador;
    }

    private static int contadorNodosArbol(BinaryTreeADT arbol) {
        if(arbol.isEmpty()) return 0;

        return 1 + contadorNodosArbol(arbol.getLeft()) + contadorNodosArbol(arbol.getRight());
    }

    /*
        2. Si todos los nodos del árbol están presentes en el grafo.
    */
    private static boolean arbolEnGrafo(GraphADT grafo, BinaryTreeADT arbol) {
        if(arbol.isEmpty() && grafo.isEmpty() || arbol.isEmpty()) return true;
        else if(grafo.isEmpty()) return false;

        SetADT vertices = grafo.getVertxs();

        return existeVertice(arbol, vertices);
    }

    private static boolean existeVertice(BinaryTreeADT arbol, SetADT vertices) {
        if (arbol.isEmpty()) return true;

        if(!vertices.exist(arbol.getRoot())) return false;

        return existeVertice(arbol.getLeft(),vertices) && existeVertice(arbol.getRight(),vertices);
    }

    /*
        3. Si el grafo contiene las mismas conexiones padre-hijo que el árbol.
    */
    private static boolean mismasConexiones(GraphADT grafo, BinaryTreeADT arbol) {
        if(arbol.isEmpty() && grafo.isEmpty()) return true;
        else if(arbol.isEmpty() || grafo.isEmpty()) return false;

        MultipleDictionaryADT conexionesArbol = conexionesArbol(arbol);

        return buscarArista(grafo, conexionesArbol);
    }

    private static MultipleDictionaryADT conexionesArbol(BinaryTreeADT arbol) {
        if(arbol.isEmpty()) return new DynamicMultipleDictionaryADT();

        MultipleDictionaryADT conexionesArbol = new DynamicMultipleDictionaryADT();
        cargarArbol(arbol, conexionesArbol);

        return conexionesArbol;
    }

    private static void cargarArbol(BinaryTreeADT arbol, MultipleDictionaryADT conexiones) {
        if(arbol.isEmpty()) return;

        if(arbol.getLeft().isEmpty() && arbol.getRight().isEmpty()) return;

        if(!arbol.getLeft().isEmpty()){
            conexiones.add(arbol.getRoot(),arbol.getLeft().getRoot());
        }
        if(!arbol.getRight().isEmpty()){
            conexiones.add(arbol.getRoot(),arbol.getRight().getRoot());
        }

        cargarArbol(arbol.getLeft(), conexiones);
        cargarArbol(arbol.getRight(), conexiones);
    }

    private static boolean buscarArista(GraphADT grafo, MultipleDictionaryADT conexiones) {
        if(grafo.isEmpty()) return false;
        if(conexiones.isEmpty()) return true;

        SetADT vertices = grafo.getVertxs();
        SetADT keys = conexiones.getKeys();

        while (!vertices.isEmpty()){
            int vertice = vertices.choose();
            vertices.remove(vertice);

            if(keys.exist(vertice)){
                for (int i = 0 ; i<conexiones.get(vertice).length; i++){
                    if(!grafo.existsEdge(vertice, conexiones.get(vertice)[i])){
                        return false;
                    }
                }
            }
        }
        return true;
    }


}

