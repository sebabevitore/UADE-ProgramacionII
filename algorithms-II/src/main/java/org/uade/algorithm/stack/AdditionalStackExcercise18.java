package org.uade.algorithm.stack;

import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;

public class AdditionalStackExcercise18 {
    //Mostrar los elementos de una pila pero sin utilizar un while.
    private static void sinWhile(StackADT stack) {
        if (stack.isEmpty()){return;}
        int value = stack.getElement();
        stack.remove();
        System.out.println(value + " ");
        sinWhile(stack);

        stack.add(value);

    }
    public static void main(String[] args) {
        StackADT pila = new DynamicStackADT();
        pila.add(3);
        pila.add(2);
        pila.add(4);

        sinWhile(pila);

    }


}
