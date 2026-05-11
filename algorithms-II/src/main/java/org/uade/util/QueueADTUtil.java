package org.uade.util;

import org.uade.structure.definition.QueueADT;
import org.uade.structure.implementation.dynamic.DynamicQueueADT;
import org.uade.structure.implementation.fixed.StaticQueueADT;

public class QueueADTUtil {


    public static <T> QueueADT<T> copy (QueueADT<T> queue){
        QueueADT<T> copia = getNewQueue(queue);
        QueueADT<T> aux = getNewQueue(queue);

        while(!queue.isEmpty()){
            copia.add(queue.getElement());
            aux.add(queue.getElement());
            queue.remove();
        }

        while(!aux.isEmpty()){
            queue.add(aux.getElement());
            aux.remove();
        }
        return copia;
    }

    private static <T> QueueADT<T> getNewQueue(QueueADT<T> queue){
        if(queue instanceof StaticQueueADT){
            return new StaticQueueADT<>();
        }
        else{
            return new DynamicQueueADT<>();
        }
    }

    public static <T> void print(QueueADT<T> queue) {
        QueueADT<T> copy = copy(queue);
        while (!copy.isEmpty()) {
            System.out.println(copy.getElement());
            copy.remove();
        }
    }



}
