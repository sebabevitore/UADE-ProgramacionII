package org.uade.algorithm.linkedlist;

import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.implementation.dynamic.DynamicLinkedListADT;

public class repasoparcialej4 {
    //Dadas dos listas, implementa un método que devuelva una nueva lista intercalando
    //los elementos de ambas. Si una lista es más larga que la otra, los elementos
    //restantes deben añadirse al final.

    private static LinkedListADT intercalarListas(LinkedListADT lista1, LinkedListADT lista2){
        int sizes = lista1.size() + lista2.size();
        LinkedListADT aux = new DynamicLinkedListADT();

        for(int i = 0; i < sizes; i++){
            if(i<lista1.size()){
                aux.add(lista1.get(i));
            }
            if(i<lista2.size()){
                aux.add(lista2.get(i));
            }
        }
        return aux;


    }

    public static void main(String[] args) {
        LinkedListADT lista1 = new DynamicLinkedListADT();
        LinkedListADT lista2 = new DynamicLinkedListADT();

        lista1.add(1);
        lista1.add(3);
        lista1.add(5);

        lista2.add(2);
        lista2.add(4);
        lista2.add(6);


        LinkedListADT resultado = intercalarListas(lista1, lista2);
        System.out.println(resultado);
    }



}
