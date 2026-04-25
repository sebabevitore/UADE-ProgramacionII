package org.uade.algorithm.linkedlist;

import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.implementation.dynamic.DynamicLinkedListADT;

public class AdditionalLinkedListExercise6 {
    //Dada una lista, implementa un método que elimine alternativamente los elementos
    //de la lista hasta que quede un único elemento

    public static void main(String[] args) {
        LinkedListADT lista = new DynamicLinkedListADT();

        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);

        LinkedListADT lista2 = eliminar(lista);

        System.out.println(lista2);

    }

    //supongo q alternar es uno si uno no...
    private static LinkedListADT eliminar(LinkedListADT lista) {
        try{
            while(lista.size()!=1){
                for(int i=0;i<lista.size();i=i+2){
                    lista.remove(i);
                }
            }
            return lista;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }


}
