package org.uade.util;

import org.uade.structure.definition.PriorityQueueADT;
import org.uade.structure.implementation.dynamic.DynamicPriorityQueueADT;
import org.uade.structure.implementation.fixed.StaticPriorityQueueADT;

public class PriorityQueueADTUtil {

    public static <T> void print(PriorityQueueADT<T> queue) {
        PriorityQueueADT<T> copy = copy(queue);
        while (!copy.isEmpty()) {
            System.out.println("Valor: " + copy.getElement() + " - Prioridad: " + copy.getPriority());
            copy.remove();
        }
    }

    public static <T> PriorityQueueADT<T> copy(PriorityQueueADT<T> priorityQueue){
        PriorityQueueADT<T> aux = getNewPriorityQueue(priorityQueue);
        PriorityQueueADT<T> copia = getNewPriorityQueue(priorityQueue);

        while(!priorityQueue.isEmpty()){
            aux.add(priorityQueue.getElement(), priorityQueue.getPriority());
            priorityQueue.remove();
        }

        while(!aux.isEmpty()){
            copia.add(aux.getElement(), aux.getPriority());
            priorityQueue.add(aux.getElement(), aux.getPriority());
            aux.remove();
        }
        return copia;
    }

    private static <T> PriorityQueueADT<T> getNewPriorityQueue(PriorityQueueADT<T> priorityQueue) {
        if (priorityQueue instanceof DynamicPriorityQueueADT) {
            return new DynamicPriorityQueueADT<>();
        } else {
            return new StaticPriorityQueueADT<>();
        }
    }

}
