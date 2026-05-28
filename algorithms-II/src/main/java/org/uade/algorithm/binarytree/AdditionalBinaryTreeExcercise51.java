package org.uade.algorithm.binarytree;
//Dado un arbol se pide saber la cantidad de nodos internos que posee. Un nodo
//interno es aquel que no es una hoja y se debe excluir la raiz como nodo interno.

import org.uade.structure.definition.BinaryTreeADT;
import org.uade.structure.implementation.dynamic.DynamicBinaryTreeADT;

public class AdditionalBinaryTreeExcercise51 {
    private static int cantNodosInternos(BinaryTreeADT arbol) {
        // Si el árbol está vacío o solo tiene la raíz (sin hijos), el resultado es 0
        if (arbol.isEmpty() || (arbol.getLeft().isEmpty() && arbol.getRight().isEmpty())) {
            return 0;
        }


        return contarInternosRecursivo(arbol.getLeft()) + contarInternosRecursivo(arbol.getRight());
    }

    private static int contarInternosRecursivo(BinaryTreeADT arbol) {
        // Caso base 1: Subárbol vacío
        if (arbol.isEmpty()) {
            return 0;
        }
        // Caso base 2: Es una hoja (no tiene hijos) -> No se cuenta
        if (arbol.getLeft().isEmpty() && arbol.getRight().isEmpty()) {
            return 0;
        }
        // Caso recursivo: Es un nodo interno (porque pasó los filtros anteriores)
        // Sumamos 1 (por el nodo actual) + lo que devuelvan sus hijos
        return 1 + contarInternosRecursivo(arbol.getLeft()) + contarInternosRecursivo(arbol.getRight());

    }


    public static void main(String[] args) {
        BinaryTreeADT binaryTree = new DynamicBinaryTreeADT();
        binaryTree.add(3);
        binaryTree.add(5);
        binaryTree.add(7);
        binaryTree.add(2);
        binaryTree.add(1);

        System.out.println(cantNodosInternos(binaryTree));
    }

}



