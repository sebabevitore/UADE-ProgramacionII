package org.uade.algorithm.linkedlist;

import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.implementation.dynamic.DynamicLinkedListADT;


public class AdditionalLinkedListExcercise4 {
    //Dadas dos listas, implementa un método que devuelva una nueva lista intercalando
    //los elementos de ambas. Si una lista es más larga que la otra, los elementos
    //restantes deben añadirse al final.

    private static LinkedListADT<Integer> intercalar(LinkedListADT<Integer> lista1, LinkedListADT<Integer> lista2) {
        LinkedListADT<Integer> aux = new DynamicLinkedListADT<Integer>();
        if(!lista1.isEmpty() && !lista2.isEmpty()){
            int indice = 0;
            while(aux.size() < (lista1.size()+lista2.size())){
                if(lista1.size()>indice){
                    aux.add(lista1.get(indice));
                }
                if(lista2.size()>indice){
                    aux.add(lista2.get(indice));
                }
                indice++;
            }
        }else{
            System.out.println("Una lista esta vacia");
        }
        return aux;
    }



    public static void main(String[] args) {
        LinkedListADT lista = new DynamicLinkedListADT();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);

        LinkedListADT lista2 = new DynamicLinkedListADT();
        lista2.add(8);
        lista2.add(9);
        lista2.add(10);
        lista2.add(11);
        lista2.add(12);
        lista2.add(13);



        System.out.println("Primera lista: "+ lista2 + "\n");
        System.out.println("Segunda lista: "+ lista + "\n");

        LinkedListADT lista3 = intercalar(lista2, lista);
        System.out.println("Intercalar lista: "+ lista3 + "\n");
    }


}
