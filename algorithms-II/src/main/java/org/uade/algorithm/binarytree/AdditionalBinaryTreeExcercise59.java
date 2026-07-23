package org.uade.algorithm.binarytree;

import org.uade.structure.definition.BinaryTreeADT;
import org.uade.structure.implementation.dynamic.DynamicBinaryTreeADT;

public class AdditionalBinaryTreeExcercise59 {
    //  59. Dado un arbol binario, retornar la suma los valores de los niveles pares y resta los
    //  valores de los impares. La raíz se encuentra en el nivel 1.

    private static int ejercicio(BinaryTreeADT arbol) {
        if(arbol.isEmpty()){
            return 0;
        }

        return recursiva(arbol,1);
    }

    private static int recursiva(BinaryTreeADT arbol, int nivel){
        if(arbol.isEmpty()){
            return 0;
        }
        int saldo = 0;
        if(nivel%2!=0){
            saldo = arbol.getRoot() * (-1);
        }
        else{
            saldo = arbol.getRoot();
        }
        return saldo + recursiva(arbol.getLeft(),nivel+1) + recursiva(arbol.getRight(),nivel+1);
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

        System.out.println("resultado esperado: -35");
        System.out.println("resultado: " + ejercicio(arbol));

    }
}
