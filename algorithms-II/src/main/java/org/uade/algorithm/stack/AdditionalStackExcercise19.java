package org.uade.algorithm.stack;

import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;

public class AdditionalStackExcercise19 {
    //19. Cree, inicialice y cargue la pila DADA, multiplique todos los elementos por un
    //numero en particular.

    private static void multiplicarStack (StackADT pila, int x) {
        if (pila.isEmpty()) return;

        StackADT aux = new DynamicStackADT();
        while (!pila.isEmpty()) {
            int elemento = pila.getElement();
            pila.remove();
            int producto = elemento * x;
            aux.add(producto);
        }
        while (!aux.isEmpty()) {
            pila.add(aux.getElement());
            aux.remove();
        }
        System.out.println("Ejercicio aplicado... " + pila);
    }

    public static void main(String[] args) {
        int count = 2;
        StackADT pila = new DynamicStackADT();
        pila.add(2);
        pila.add(3);
        pila.add(4);

        multiplicarStack(pila,count);
    }
}
