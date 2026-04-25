package org.uade.algorithm.linkedlist;

import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.implementation.dynamic.DynamicLinkedListADT;

public class AdditionalLinkedListExcercise9 {
    //Dadas dos listas, implementa un método que determine si ambas tienen los mismos
    //elementos sin importar el orden.

    private static boolean sonIguales(LinkedListADT lista1, LinkedListADT lista2) {
        if (lista1.size() != lista2.size()) {
            return false;
        }
        for (int i = 0; i < lista1.size(); i++) {
            int contador_lista1 = contarOcurrencias(lista1,lista1.get(i));
            int contador_lista2 = contarOcurrencias(lista2,lista2.get(i));
            if(contador_lista1 != contador_lista2){
                return false;
            }

        }
        return true;
    }

    private static int contarOcurrencias(LinkedListADT lista, int value){
        int count = 0;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i) == value) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        LinkedListADT lista1 = new DynamicLinkedListADT();

        lista1.add(3);
        lista1.add(2);
        lista1.add(4);
        lista1.add(5);

        LinkedListADT lista2 = new DynamicLinkedListADT();
        lista2.add(5);
        lista2.add(2);
        lista2.add(3);
        lista2.add(4);

        System.out.println("Son iguales: "+sonIguales(lista1,lista2));

    }

}
