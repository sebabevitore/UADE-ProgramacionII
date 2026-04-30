package org.uade.algorithm.queue;

import org.uade.structure.definition.QueueADT;
import org.uade.structure.implementation.dynamic.DynamicQueueADT;

import static org.uade.util.QueueADTUtil.copy;

public class AdditionalQueueExcercise22 {
    // Dado dos colas, escribi un programa que las intercale.
    // Por ejemplo, si tenes la cola A = [1, 3, 5] y la cola B = [2, 4, 6],
    // el resultado debería ser una nueva cola C = [1, 2, 3, 4, 5, 6]

    private static QueueADT intercalarColas(QueueADT colaA, QueueADT colaB){
        if(colaA.isEmpty() ||  colaB.isEmpty()){
            return null;
        }
        QueueADT copiaA = copy(colaA);
        QueueADT copiaB = copy(colaB);

        QueueADT resultado = new DynamicQueueADT();
        // A = [1, 3, 5]
        // B = [2, 4, 6]

        while(!copiaA.isEmpty()){
            int elementoA = copiaA.getElement();
            resultado.add(elementoA);
            while(!copiaB.isEmpty()){
                int elementoB = copiaB.getElement();
                copiaB.remove();
                resultado.add(elementoB);
                break;
            }
            copiaA.remove();
        }
        return resultado;

    }

    public static void main(String[] args) {
        QueueADT colaA = new DynamicQueueADT();
        QueueADT colaB = new DynamicQueueADT();

        colaA.add(1);
        colaA.add(3);
        colaA.add(5);
        colaB.add(2);
        colaB.add(4);
        colaB.add(6);

        System.out.println(intercalarColas(colaA, colaB));


    }

}
