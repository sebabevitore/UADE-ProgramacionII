package org.uade.algorithm.binarytree;

import org.uade.structure.definition.BinaryTreeADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.dynamic.DynamicBinaryTreeADT;
import org.uade.structure.implementation.dynamic.DynamicSetADT;

public class AdditionalBinaryTreeExcercise55 {
    //55. Queremos una funcion que devuelva true si existen dos naturales en el ARBOL tales
    //que sumados son equivalentes a n

    private static boolean equivalentes(BinaryTreeADT arbol, int n) {
        if(arbol.isEmpty() || n <= 0 ){
            return false;
        }

        if(arbol.getRoot() >= n){
            return false;
        }

        SetADT posibles = new DynamicSetADT();

        return recursiva(arbol,n,posibles);
    }

    private static boolean recursiva(BinaryTreeADT arbol, int n, SetADT posibles) {
        if(arbol.isEmpty()){
            return false;
        }

        int numNecesario = n - arbol.getRoot();

        if(posibles.exist(numNecesario)){
            return true;
        }

        posibles.add(arbol.getRoot());

        return recursiva(arbol.getLeft(), n, posibles) || recursiva(arbol.getRight(), n, posibles);
    }

    public static void main(String[] args) {
        BinaryTreeADT arbol = new DynamicBinaryTreeADT();
        arbol.add(3);
        arbol.add(2);
        arbol.add(4);
        arbol.add(5);
        arbol.add(6);


        System.out.println(equivalentes(arbol, 10));

    }



}
