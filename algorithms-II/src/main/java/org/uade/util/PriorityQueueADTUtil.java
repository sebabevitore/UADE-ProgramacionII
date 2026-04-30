package org.uade.util;

import org.uade.structure.definition.PriorityQueueADT;
import org.uade.structure.implementation.dynamic.DynamicPriorityQueueADT;
import org.uade.structure.implementation.fixed.StaticPriorityQueueADT;

public class PriorityQueueADTUtil {

    public static PriorityQueueADT copy(PriorityQueueADT priorityQueue){
        PriorityQueueADT aux = getNewPriorityQueue(priorityQueue);
        PriorityQueueADT copia = getNewPriorityQueue(priorityQueue);

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

    private static PriorityQueueADT getNewPriorityQueue(PriorityQueueADT priorityQueue) {
        if (priorityQueue instanceof DynamicPriorityQueueADT) {
            return new DynamicPriorityQueueADT();
        } else {
            return new StaticPriorityQueueADT();
        }
    }

}
