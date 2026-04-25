package org.uade.algorithm.linkedlist;

import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.implementation.dynamic.DynamicLinkedListADT;

public class AdditionalLinkedListExercise7 {
    //Dado un arreglo de listas, implementa un método que las concatene en una sola
    //lista, manteniendo el orden de cada lista

    private static LinkedListADT concatenarListas(LinkedListADT[] lista){
        LinkedListADT aux = new DynamicLinkedListADT();
        for(LinkedListADT item : lista){
            for(int i = 0; i < lista.length; i++){
                aux.add(item.get(i));
            }
        }
        return aux;
    }

    public static void main(String[] args) {
        LinkedListADT lista1 = new DynamicLinkedListADT();
        lista1.add(3);
        lista1.add(2);
        lista1.add(4);
        LinkedListADT lista2 = new DynamicLinkedListADT();
        lista2.add(3);
        lista2.add(2);
        lista2.add(4);


        LinkedListADT[] array = {lista1,lista2};
        System.out.println("Arreglada: " + concatenarListas(array));


    }



}
