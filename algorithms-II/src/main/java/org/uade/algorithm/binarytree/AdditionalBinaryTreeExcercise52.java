package org.uade.algorithm.binarytree;

import org.uade.structure.definition.BinaryTreeADT;
import org.uade.structure.implementation.dynamic.DynamicBinaryTreeADT;

public class AdditionalBinaryTreeExcercise52 {

//    Crear un metodo que me permita saber si el arbol equiponderado. Es equiponderado
//    si, para cada nodo del árbol, se cumple que el valor almacenado en ese nodo es
//    igual a la suma de los valores de sus hijos izquierdo y derecho

    private static boolean esEquiponderado(BinaryTreeADT tree) {
        if (tree.isEmpty()) {
            return true;
        }
        if(tree.getLeft() == null && tree.getRight() == null) {
            return true;
        }

        int valorIzq = 0;
        if (!tree.getLeft().isEmpty()) {
            valorIzq = tree.getLeft().getRoot();
        }

        int valorDer = 0;
        if (!tree.getRight().isEmpty()) {
            valorDer = tree.getRight().getRoot();
        }

        if (tree.getRoot() == (valorIzq + valorDer)) {
            return esEquiponderado(tree.getLeft()) && esEquiponderado(tree.getRight());
        }
        return false;

    }


    public static void main(String[] args) {


        BinaryTreeADT tree = new DynamicBinaryTreeADT();
        tree.add(10);
        tree.add(4);
        tree.add(6);
        boolean resultado = esEquiponderado(tree);

        System.out.println("es equiponderado: "+resultado);
        System.out.println("es imposible que un arbol sea equiponderado con el metodo add q tengo...");


    }
}
