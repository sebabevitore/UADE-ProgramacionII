package org.uade.algorithm.binarytree;

import org.uade.structure.definition.BinaryTreeADT;
import org.uade.structure.implementation.dynamic.DynamicBinaryTreeADT;

public class AdditionalBinaryTreeExcercise53 {
    // 53. Dado dos arboles se busca saber si el primero es prefijo del segundo.


    private static boolean esPrefijo(BinaryTreeADT arbol1, BinaryTreeADT arbol2) {
        if (arbol1.isEmpty()) {
            return true;
        }
        if (arbol2.isEmpty()) {
            return false;
        }

        if(arbol1.getRoot() != arbol2.getRoot()) {
            return false;
        }

        return esPrefijo(arbol1.getLeft(),arbol2.getLeft()) && esPrefijo(arbol1.getRight(),arbol2.getRight());
    }

    public static void main(String[] args) {
        BinaryTreeADT arbol1 = new DynamicBinaryTreeADT();
        BinaryTreeADT arbol2 = new DynamicBinaryTreeADT();

        arbol1.add(2);
        arbol1.add(3);
        arbol1.add(4);

        arbol2.add(2);
        arbol2.add(3);
        arbol2.add(4);
        arbol2.add(5);
        arbol2.add(6);

        System.out.println(esPrefijo(arbol1,arbol2));

    }
}
