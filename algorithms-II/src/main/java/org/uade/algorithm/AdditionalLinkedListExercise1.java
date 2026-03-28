package org.uade.algorithm;

import java.util.LinkedList;

public class AdditionalLinkedListExercise1 {
    //  1. Crea un método que reciba una lista y devuelva una nueva lista con los mismos
    //  elementos pero en orden inverso.

    public static LinkedList invertir(LinkedList lista){
        LinkedList invertida = new LinkedList();
        for (int i = lista.size()-1; i >= 0; i--) {
            invertida.add(lista.get(i));
        }
        return invertida;
    }


    public static void main(String[] args) {
        LinkedList lista = new LinkedList();

        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);


        System.out.println(invertir(lista));

    }
}
