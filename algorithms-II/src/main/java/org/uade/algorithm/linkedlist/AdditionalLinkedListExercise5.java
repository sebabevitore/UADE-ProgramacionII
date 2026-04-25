package org.uade.algorithm.linkedlist;


import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.implementation.dynamic.DynamicLinkedListADT;

public class AdditionalLinkedListExercise5 {
    //  5. Dada una lista de números y un valor x, encuentra el elemento de la lista que esté
    //  más cerca de x.

    private static int mas_cercano(int x, LinkedListADT lista ){
        if(lista.isEmpty()){
            System.out.println("Lista is empty");
            return 0;
        }
        int candidato = lista.get(0);
        for (int i = 0; i < lista.size(); i++){
            if ((Math.abs(lista.get(i)-x))<(Math.abs(candidato-x))){
                candidato = lista.get(i);
            }
        }
        return candidato;
    }


    public static void main(String[] args) {
        LinkedListADT adt = new DynamicLinkedListADT();

        adt.add(2);
        adt.add(3);
        adt.add(4);
        adt.add(5);

        int x = 6;

        System.out.println("mas cercano: "+mas_cercano(x, adt));



    }

}
