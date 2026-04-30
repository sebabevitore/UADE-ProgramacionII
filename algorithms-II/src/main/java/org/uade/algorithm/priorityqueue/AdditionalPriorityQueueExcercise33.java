package org.uade.algorithm.priorityqueue;

import org.uade.structure.definition.PriorityQueueADT;
import org.uade.structure.implementation.dynamic.DynamicPriorityQueueADT;

import static org.uade.util.PriorityQueueADTUtil.copy;

public class AdditionalPriorityQueueExcercise33 {
    //Dada una cola con prioridad, implementa un método que elimine los elementos
    //duplicados que tienen la misma prioridad, dejando solo el primero que apareció.

    private static void eliminarDuplicados(PriorityQueueADT cola){
        PriorityQueueADT aux = new DynamicPriorityQueueADT();

        while(!cola.isEmpty()){
            int prioridad = cola.getPriority();
            int valor = cola.getElement();
            boolean guardado = estaEnCola(aux, prioridad, valor);
            if(!guardado){
                aux.add(valor, prioridad);
            }
            cola.remove();
        }

        while(!aux.isEmpty()){
            cola.add(aux.getElement(),aux.getPriority());
            aux.remove();
        }
    }


    private static boolean estaEnCola(PriorityQueueADT cola, int prioridad, int valor){
        PriorityQueueADT copia = copy(cola);
        while(!copia.isEmpty()){
            if(copia.getPriority() == prioridad && copia.getElement() == valor){
                return true;
            }
            copia.remove();
        }
        return false;
    }


    public static void main(String[] args) {
        PriorityQueueADT cola = new DynamicPriorityQueueADT();
        cola.add(2,3);
        cola.add(3,4);
        cola.add(4,5);
        cola.add(1,4);
        cola.add(2,2);
        cola.add(3,4);
        cola.add(1,5);
        cola.add(2,2);
        cola.add(2,3);
        System.out.println("cola original: "+cola);
        eliminarDuplicados(cola);
        System.out.println("cola resultado: "+cola);


    }



}
