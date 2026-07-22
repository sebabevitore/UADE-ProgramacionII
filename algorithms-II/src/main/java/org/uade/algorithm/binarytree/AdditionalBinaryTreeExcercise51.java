package org.uade.algorithm.binarytree;

import org.uade.structure.definition.BinaryTreeADT;
import org.uade.structure.implementation.dynamic.DynamicBinaryTreeADT;

import static org.uade.util.BinaryTreeADTUtil.*;

public class AdditionalBinaryTreeExcercise51 {
    //  51. Dado un arbol se pide saber la cantidad de nodos internos que posee.
    //  Un nodo interno es aquel que no es una hoja
    //  y se debe excluir la raiz como nodo interno.


    private static int cantNodosInternos(BinaryTreeADT arbol){
        // 1. ¿Qué pasa si el árbol está completamente vacío?
        // 2. ¿Qué pasa si el árbol SOLO tiene la raíz (es decir, no tiene hijos)?
        if (arbol.isEmpty() || (arbol.getLeft().isEmpty() && arbol.getRight().isEmpty())){
            return 0;
        }

        return recursivaCant(arbol.getLeft()) + recursivaCant(arbol.getRight());
    }

    private static int recursivaCant(BinaryTreeADT arbol){
        if(arbol.isEmpty() || (arbol.getLeft().isEmpty() && arbol.getRight().isEmpty())){
            return 0;
        }
        return 1 + recursivaCant(arbol.getLeft()) + recursivaCant(arbol.getRight());
    }

    public static void main(String[] args) {
        BinaryTreeADT arbol = new DynamicBinaryTreeADT();
        arbol.add(3);
        arbol.add(5);
        arbol.add(7);
        arbol.add(2);
        arbol.add(9);
        arbol.add(11);

        printPostOrder(arbol);

        System.out.println("Cant nodos internos : " + cantNodosInternos(arbol));


    }



}
