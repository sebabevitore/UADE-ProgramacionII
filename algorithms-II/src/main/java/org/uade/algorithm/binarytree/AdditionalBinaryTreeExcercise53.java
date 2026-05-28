package org.uade.algorithm.binarytree;

import org.uade.structure.definition.BinaryTreeADT;
import org.uade.structure.implementation.dynamic.DynamicBinaryTreeADT;

public class AdditionalBinaryTreeExcercise53 {
    //53. Dado dos arboles se busca saber si el primero es prefijo del segundo.
    private static boolean esPrefijo(BinaryTreeADT treeA, BinaryTreeADT treeB) {
        if (treeA.isEmpty()) return true;

        if (treeB.isEmpty()) return false;

        if (treeA.getRoot() != treeB.getRoot()) return false;

        if(treeA.getRoot() != treeB.getRoot()) {
            return false;
        }

        return esPrefijo(treeA.getLeft(), treeB.getLeft()) &&
                esPrefijo(treeA.getRight(), treeB.getRight());
    }


    public static void main(String[] args) {
        BinaryTreeADT treeB = new DynamicBinaryTreeADT();
        treeB.add(20);
        treeB.add(10);
        treeB.add(25);
        treeB.add(5);

        BinaryTreeADT treeA = new DynamicBinaryTreeADT();
        treeA.add(20);
        treeA.add(10);

        System.out.println(esPrefijo(treeA, treeB));





    }
}
