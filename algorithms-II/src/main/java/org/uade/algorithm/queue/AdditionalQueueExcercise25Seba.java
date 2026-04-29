package org.uade.algorithm.queue;

import org.uade.structure.definition.QueueADT;
import org.uade.structure.implementation.dynamic.DynamicQueueADT;

import static org.uade.util.QueueADTUtil.copy;

public class AdditionalQueueExcercise25Seba {
    //Eliminar de una cola un elemento específico con recursión.

    private static void eliminarElemento(int numero, QueueADT cola) {
        if (cola.isEmpty()) {
            return;
        }
        int actual = cola.getElement();
        cola.remove();

        eliminarElemento(numero, cola);

        if(actual != numero) {
            cola.add(actual);
        }
    }

    public static void main(String[] args) {
        QueueADT colaOriginal = new DynamicQueueADT();
        colaOriginal.add(1);
        colaOriginal.add(2);
        colaOriginal.add(3);
        colaOriginal.add(4);
        System.out.println("Original: " + colaOriginal);
        System.out.println("Eliminando el 2...");
        eliminarElemento(2, colaOriginal);
        System.out.println("Resultado: " + colaOriginal);
    }

}
