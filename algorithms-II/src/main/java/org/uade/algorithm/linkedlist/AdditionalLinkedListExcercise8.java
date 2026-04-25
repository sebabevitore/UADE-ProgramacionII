package org.uade.algorithm.linkedlist;

import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.implementation.dynamic.DynamicLinkedListADT;

public class AdditionalLinkedListExcercise8 {
    //Dada una lista y un número n, implementa un método que elimine los últimos n
    //elementos de la lista.
    private static LinkedListADT eliminar(LinkedListADT lista, int n){
        if (n > lista.size()) {
            n = lista.size();
        }

        while (n > 0) {
            lista.remove(lista.size()-1);
            n--;
        }
        return lista;
    }

    public static void main(String[] args) {
        LinkedListADT lista1 = new DynamicLinkedListADT();
        lista1.add(3);
        lista1.add(2);
        lista1.add(4);
        lista1.add(5);
        lista1.add(6);

        System.out.println("Lista original: " + lista1);

        System.out.println("Lista after removing: " + eliminar(lista1,2));

    }
}
