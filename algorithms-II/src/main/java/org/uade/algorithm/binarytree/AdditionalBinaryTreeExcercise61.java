package org.uade.algorithm.binarytree;

import org.uade.structure.definition.BinaryTreeADT;
import org.uade.structure.implementation.dynamic.DynamicBinaryTreeADT;

public class AdditionalBinaryTreeExcercise61 {
    //61. Dado un árbol binario y un entero k, verifica si el árbol está completo hasta el nivel k.
    //Un árbol completo tiene todos los nodos llenos en todos sus niveles.

    private static boolean esCompleto(BinaryTreeADT arbol,int k) {
        if(arbol.isEmpty() && k==0){
            return true;
        }
        else if(arbol.isEmpty()){
            return false;
        }

        return recursiva(arbol,1,k);
    }

    private static boolean recursiva(BinaryTreeADT arbol, int nivel, int k) {
        if(arbol.isEmpty()){
            return false;
        }
        if(nivel==k){
            return true;
        }
        return recursiva(arbol.getLeft(),nivel+1,k) && recursiva(arbol.getRight(),nivel+1,k);
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

        System.out.println("RE: true");
        System.out.println("resultado: " + esCompleto(arbol, 3));

        System.out.println("resultado nivel 4 (deberia ser false): " + esCompleto(arbol, 4));
    }

}
