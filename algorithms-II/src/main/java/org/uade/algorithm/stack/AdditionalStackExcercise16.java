package org.uade.algorithm.stack;

import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;

import static org.uade.util.StackADTUtil.*;


public class AdditionalStackExcercise16 {
    //Cree, inicialice y la pila DADA. Elimine los elementos repetidos de la misma dejando
    //solamente un ejemplar de cada valor.

    private static void ejercicio16(StackADT dada) {
        if (dada.isEmpty()) return;

        StackADT temp = new DynamicStackADT();

        while (!dada.isEmpty()) {
            int elemento = dada.getElement();
            dada.remove();

            if (!estaEnPila(elemento, temp)) {
                temp.add(elemento);
            }
        }

        while (!temp.isEmpty()) {
            dada.add(temp.getElement());
            temp.remove();
        }

        System.out.println("Ejercicio aplicado... " + dada);

    }


    public static void main(String[] args) {
        StackADT stack = new DynamicStackADT();
        stack.add(8);
        stack.add(4);
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);
        stack.add(1);
        stack.add(2);
        stack.add(7);
        System.out.println("Stack original: " +stack);

        ejercicio16(stack);
    }

}
