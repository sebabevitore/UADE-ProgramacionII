package org.uade.util;

import org.uade.structure.definition.QueueADT;
import org.uade.structure.implementation.dynamic.DynamicQueueADT;
import org.uade.structure.implementation.fixed.StaticQueueADT;

public class QueueADTUtil {


    public static QueueADT copy (QueueADT queue){
        QueueADT copia = getNewQueue(queue);
        QueueADT aux = getNewQueue(queue);

        while(!queue.isEmpty()){
            copia.add(queue.getElement());
            aux.add(queue.getElement());
            queue.remove();
        } // hasta aca vacie queue, y llene copia y aux.

        while(!aux.isEmpty()){
            queue.add(aux.getElement());
            aux.remove();
        }
        return copia;
    }

    private static QueueADT getNewQueue(QueueADT queue){
        if(queue instanceof StaticQueueADT){
            return new StaticQueueADT();
        }
        else{
            return new DynamicQueueADT();
        }
    }

}
