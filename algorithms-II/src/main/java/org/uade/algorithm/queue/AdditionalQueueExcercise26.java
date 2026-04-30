package org.uade.algorithm.queue;

import org.uade.structure.definition.QueueADT;
import org.uade.structure.implementation.dynamic.DynamicQueueADT;

import static org.uade.util.QueueADTUtil.copy;

public class AdditionalQueueExcercise26 {
    //Dada una cola, rota sus elementos N posiciones a la izquierda.

    private static QueueADT rotarIzquierda(QueueADT cola, int posiciones) {
        if(posiciones == 0 || cola.isEmpty()){
            return null;
        }
        QueueADT copia = copy(cola);

        for(int i = 0; i < posiciones; i++){
            int elemento = copia.getElement();
            copia.remove();
            copia.add(elemento);
            posiciones--;
        }

        return copia;
    }

    public static void main(String[] args) {
        QueueADT colaOriginal = new DynamicQueueADT();
        colaOriginal.add(1);
        colaOriginal.add(2);
        colaOriginal.add(3);
        colaOriginal.add(4);

        System.out.println("Original: " + colaOriginal);
        int cantPosiciones = 3;
        System.out.println("Rotar "+ cantPosiciones +" posiciones "+ rotarIzquierda(colaOriginal, cantPosiciones));

    }
}
