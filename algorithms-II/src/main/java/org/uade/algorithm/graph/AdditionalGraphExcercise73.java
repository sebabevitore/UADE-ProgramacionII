package org.uade.algorithm.graph;

import org.uade.structure.definition.BinaryTreeADT;
import org.uade.structure.definition.GraphADT;
import org.uade.structure.implementation.dynamic.DynamicBinaryTreeADT;
import org.uade.structure.implementation.dynamic.DynamicGraphADT;

import static org.uade.util.SetADTUtil.print;

public class AdditionalGraphExcercise73 {
    //73. Implementar un metodo que reciba un árbol binario y lo convierta en un grafo
    //dirigido.

    public static GraphADT conversion(BinaryTreeADT arbol){
        if(arbol.isEmpty()){
            return new DynamicGraphADT();
        }
        GraphADT graph = new DynamicGraphADT();


        cargarVertices(arbol,graph);
        cargarAristas(arbol,graph);


        return graph;


    }

    private static void cargarVertices(BinaryTreeADT arbol, GraphADT graph){
        if(arbol.isEmpty()){
            return;
        }

        graph.addVertx(arbol.getRoot());
        cargarVertices(arbol.getLeft(),graph);
        cargarVertices(arbol.getRight(),graph);

    }

    private static void cargarAristas(BinaryTreeADT arbol, GraphADT graph){
        if(arbol.isEmpty() || (arbol.getLeft().isEmpty() && arbol.getRight().isEmpty())){
            return;
        }

        // si tiene hijo izquierdo, creo la arista desde este nodo al hijo izq.
        if(!arbol.getLeft().isEmpty()){
            graph.addEdge(arbol.getRoot(),arbol.getLeft().getRoot(),1);
        }
        // si tiene hijo derecho, creo la arista desde este nodo al hijo der.
        if(!arbol.getRight().isEmpty()){
            graph.addEdge(arbol.getRoot(),arbol.getRight().getRoot(),1);
        }

        cargarAristas(arbol.getLeft(), graph);
        cargarAristas(arbol.getRight(), graph);

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

        System.out.println("existe entre:");
        System.out.println("10 y 20 (RE: TRUE): "+(graph.existsEdge(10,20)));
        System.out.println("20 y 25 (RE: TRUE): "+(graph.existsEdge(20,25)));
        System.out.println("6 y 3 (RE: FALSE): "+(graph.existsEdge(6,3)));

    }
}
