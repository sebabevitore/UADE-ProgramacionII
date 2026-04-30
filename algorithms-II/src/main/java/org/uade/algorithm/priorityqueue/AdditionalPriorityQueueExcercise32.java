package org.uade.algorithm.priorityqueue;

import org.uade.structure.definition.PriorityQueueADT;
import org.uade.structure.implementation.dynamic.DynamicPriorityQueueADT;

import java.util.PriorityQueue;

import static org.uade.util.PriorityQueueADTUtil.copy;

public class AdditionalPriorityQueueExcercise32 {
    //Dada una cola con prioridad, implementa un método que distribuya los elementos en
    //varias colas separadas, una por cada nivel de prioridad

    // primero determinar la cantidad de colas para saber cuantas crear

    private static PriorityQueueADT[] separarColas(PriorityQueueADT colaPrioridad){
        int cantidad = cantidadPrioridades(colaPrioridad);

        PriorityQueueADT[] colas = new PriorityQueueADT[cantidad];
        PriorityQueueADT copia = copy(colaPrioridad);
        System.out.println("cant prioridades: " + cantidad);

        for (int i = 0; i < cantidad; i++) {
            int prioridadActual = copia.getPriority();
            PriorityQueueADT aux = new DynamicPriorityQueueADT();
            while(!copia.isEmpty()){
                if(prioridadActual == copia.getPriority()){
                    aux.add(copia.getElement(),copia.getPriority());
                    copia.remove();
                }
                else{
                    break;
                }
            }
            colas[i] = aux;
        }

        return colas;
    }

    private static int cantidadPrioridades(PriorityQueueADT colaPrioridad) {
        PriorityQueueADT copia = copy(colaPrioridad);
        int prioridad= copia.getPriority();//inicializo con primer prioridad
        int cant= 1;//cont de prioridades
        while(!copia.isEmpty()){
            if(copia.getPriority()<prioridad){
                prioridad= copia.getPriority();
                cant++;
            }
            copia.remove();
        }
        return cant;
    }


    public static void main(String[] args) {
        PriorityQueueADT cola = new DynamicPriorityQueueADT();
        cola.add(2,3);
        cola.add(3,4);
        cola.add(4,5);
        cola.add(1,4);

        PriorityQueueADT[] colas= separarColas(cola);
        System.out.println("Original: "+cola);
        for(int i=0;i<colas.length;i++){
            System.out.println(colas[i]);
        }

    }
}
