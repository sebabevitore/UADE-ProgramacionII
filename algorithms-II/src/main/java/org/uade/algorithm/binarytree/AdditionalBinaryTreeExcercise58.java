package org.uade.algorithm.binarytree;

import org.uade.structure.definition.BinaryTreeADT;
import org.uade.structure.implementation.dynamic.DynamicBinaryTreeADT;

public class AdditionalBinaryTreeExcercise58 {
    //58. Dado un árbol binario de elementos, crear un metodo que retorne el numero de
    //nodos que son hojas y son hijos izquierdos al mismo tiempo


    private static int hojasIzquierda(BinaryTreeADT arbol){
        if(arbol.isEmpty()){
            return 0;
        }

        return recursiva(arbol.getLeft(),1) + recursiva(arbol.getRight(),0);
    }

    private static int recursiva(BinaryTreeADT arbol,int lado){
        if(arbol.isEmpty()){
            return 0;
        }
        boolean esHoja = arbol.getLeft().isEmpty() && arbol.getRight().isEmpty();
        //1:izq
        //0:der
        if(lado == 1 && esHoja){
            return 1;
        }

        return recursiva(arbol.getLeft(),1) + recursiva(arbol.getRight(),0);

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

        System.out.println("resultado esperado: 2");
        System.out.println("resultado: " + hojasIzquierda(arbol));



    }
}
