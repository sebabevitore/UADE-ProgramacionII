package org.uade.algorithm.linkedlist;


import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.implementation.dynamic.DynamicLinkedListADT;

public class AdditionalLinkedListExcercise2 {
    //Implementa un método que reciba una lista y elimine los elementos duplicados,
    //dejando únicamente la primera aparición de cada elemento.

    private static LinkedListADT borrarDuplicados(LinkedListADT lista){
        int numero;
        for (int i = 0; i < lista.size(); i++) {
            numero = lista.get(i);
            for  (int j = i + 1; j < lista.size(); j++) {
                if (numero==(lista.get(j))) {
                    lista.remove(j);
                }
            }
        }
        return lista;
    }

    public static void main(String[] args) {
        LinkedListADT lista = new DynamicLinkedListADT();

        lista.add(2);
        lista.add(3);
        lista.add(4);
        lista.add(2);

        System.out.print("Lista con duplicados: "+ lista + "\n");

        LinkedListADT sinDuplicados = borrarDuplicados(lista);
        System.out.print("Lista sin duplicados: "+ sinDuplicados);


    }
}
