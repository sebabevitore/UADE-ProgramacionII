package org.uade.algorithm.priorityqueue;

import org.uade.structure.definition.PriorityQueueADT;
import org.uade.structure.implementation.dynamic.DynamicPriorityQueueADT;

public class probandoImplementacion {

    public static void main(String[] args) {
        PriorityQueueADT adt = new DynamicPriorityQueueADT();

        adt.add(10,10);
        adt.add(2,2);
        adt.add(3,3);
        adt.add(5,5);
        adt.add(8,5);

        System.out.println(adt);

    }


}
