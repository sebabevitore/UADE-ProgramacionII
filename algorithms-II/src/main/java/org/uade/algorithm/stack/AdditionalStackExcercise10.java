package org.uade.algorithm.stack;

import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;

public class AdditionalStackExcercise10 {
    //Cree e inicialice las pilas POZO, JUG1, JUG2. Reparta los elementos de la pila
    //POZO en las pilas JUG1 y JUG2 en forma alternativa.La pila POZO puede contener:
    //una cantidad par de elementos, una cantidad impar de elementos o ningún
    //elemento.

    private static void alternar(StackADT pozo, StackADT JUG1, StackADT JUG2) {
        while(!pozo.isEmpty()){
            int elemento = pozo.getElement();
            System.out.println("añadiendo elemento: " + elemento + " en JUG1.");

            JUG1.add(elemento);
            pozo.remove();

            if(!pozo.isEmpty()){
                elemento = pozo.getElement();
                System.out.println("añadiendo elemento: " + elemento + " en JUG2.");
                JUG2.add(elemento);
                pozo.remove();
            }
        }
    }

    public static void main(String[] args) {
        StackADT POZO = new DynamicStackADT();
        StackADT JUG1 = new DynamicStackADT();
        StackADT JUG2 = new DynamicStackADT();

        POZO.add(1);
        POZO.add(2);
        POZO.add(3);
        POZO.add(4);
        POZO.add(5);



        System.out.println("Pozo: " + POZO);
        System.out.println("Jug1: " + JUG1);
        System.out.println("Jug2: " + JUG2);

        System.out.println("Reparto...");
        alternar(POZO, JUG1, JUG2);
        System.out.println("Cartas: ");
        System.out.println("JUG1: " + JUG1);
        System.out.println("JUG2: " + JUG2);
        System.out.println("POZO: " + POZO);


    }
}
