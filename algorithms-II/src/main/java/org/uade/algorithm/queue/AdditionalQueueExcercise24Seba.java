package org.uade.algorithm.queue;


import org.uade.structure.definition.QueueADT;
import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicQueueADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;

import static org.uade.util.QueueADTUtil.copy;

public class AdditionalQueueExcercise24Seba {
    //Cree e inicialice la cola ORIGEN y la pila DESTINO. Cargue la cola ORIGEN y pase
    //los elementos de la cola ORIGEN a la pila DESTINO

    //suponiendo que pasarlos a la cola implica mantener el orden de salida de la cola (FIFO)
    private static StackADT cargaDePila(QueueADT cola, StackADT pila) {
        QueueADT copiaCola = copy(cola);
        StackADT pilaAux = new DynamicStackADT();
        //primer pasaje de cola a pila (se invierte la cola)
        while(!copiaCola.isEmpty()){
            pilaAux.add(copiaCola.getElement());
            copiaCola.remove();
        }
        //segundo pasaje, de pila a pila final, queda el orden original de la cola.
        while(!pilaAux.isEmpty()){
            pila.add(pilaAux.getElement());
            pilaAux.remove();
        }

        return pila;
    }

    public static void main(String[] args) {
        QueueADT origen = new DynamicQueueADT();
        origen.add(1);
        origen.add(2);
        origen.add(3);
        origen.add(2);

        StackADT destino = new DynamicStackADT();
        cargaDePila(origen,destino);
        System.out.println("Origen: " + origen);
        System.out.println("Destino: " + destino);

    }
}
