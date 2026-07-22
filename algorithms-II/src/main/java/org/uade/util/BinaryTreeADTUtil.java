package org.uade.util;

import org.uade.structure.definition.BinaryTreeADT;

public class BinaryTreeADTUtil {

    /**
     * Imprime el árbol en INORDEN (Izquierda -> Raíz -> Derecha)
     * Ideal para ver los elementos ordenados de menor a mayor en un ABB.
     */
    public static void printInOrder(BinaryTreeADT tree) {
        // Caso Base: Si está vacío, frena la recursión y no hace nada
        if (!tree.isEmpty()) {
            printInOrder(tree.getLeft());         // 1. Delegar a la izquierda
            System.out.print(tree.getRoot() + " "); // 2. Lógica del nodo (Imprimir)
            printInOrder(tree.getRight());        // 3. Delegar a la derecha
        }
    }

    /**
     * Imprime el árbol en PREORDEN (Raíz -> Izquierda -> Derecha)
     */
    public static void printPreOrder(BinaryTreeADT tree) {
        if (!tree.isEmpty()) {
            System.out.print(tree.getRoot() + " "); // 1. Lógica del nodo (Imprimir primero)
            printPreOrder(tree.getLeft());        // 2. Delegar a la izquierda
            printPreOrder(tree.getRight());       // 3. Delegar a la derecha
        }
    }

    /**
     * Imprime el árbol en POSTORDEN (Izquierda -> Derecha -> Raíz)
     */
    public static void printPostOrder(BinaryTreeADT tree) {
        if (!tree.isEmpty()) {
            printPostOrder(tree.getLeft());         // 1. Delegar a la izquierda
            printPostOrder(tree.getRight());        // 2. Delegar a la derecha
            System.out.print(tree.getRoot() + " "); // 3. Lógica del nodo (Imprimir al final)
        }
    }
}