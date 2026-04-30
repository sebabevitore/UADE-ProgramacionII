package org.uade.algorithm.priorityqueue;

import org.uade.structure.definition.PriorityQueueADT;
import org.uade.structure.implementation.dynamic.DynamicPriorityQueueADT;

public class AdditionalPriorityQueueExcercise31 {
    //Eliminar de una cola con prioridad un elemento con una prioridad especifica.

    private static void eliminarElemento(int prioridad, PriorityQueueADT cola){
        PriorityQueueADT auxiliar = new DynamicPriorityQueueADT();
        boolean encontrado = false;
        while(!cola.isEmpty()){
            if (cola.getPriority() == prioridad && !encontrado){
                encontrado = true;
            }
            else{
                auxiliar.add(cola.getElement(), cola.getPriority());
            }
            cola.remove();
        }

        while(!auxiliar.isEmpty()){
            cola.add(auxiliar.getElement(), auxiliar.getPriority());
            auxiliar.remove();
        }

    }


    public static void main(String[] args) {
        PriorityQueueADT cola = new DynamicPriorityQueueADT();
        cola.add(2,3);
        cola.add(3,4);
        cola.add(4,5);
        cola.add(1,4);

        System.out.println("Original: "+cola);

        eliminarElemento(4,cola);
        System.out.println("Eliminado: "+ cola);
    }


}
