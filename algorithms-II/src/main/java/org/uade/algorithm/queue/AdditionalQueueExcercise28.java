package org.uade.algorithm.queue;

import org.uade.structure.definition.QueueADT;
import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicQueueADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;

import static org.uade.util.QueueADTUtil.copy;
import static org.uade.util.StackADTUtil.copy;


public class AdditionalQueueExcercise28 {
    // Dada una Pila y una Cola, crea un método que genere una nueva Cola con los
    // elementos combinados de ambas, ordenados de menor a mayor

    private static QueueADT combinar(QueueADT cola, StackADT pila) {
        QueueADT c1 = copy(cola);
        StackADT c2 = copy(pila);
        QueueADT colaAux = new DynamicQueueADT();

        while(!c1.isEmpty()){
            colaAux.add(c1.getElement());
            c1.remove();
        }
        while(!c2.isEmpty()){
            colaAux.add(c2.getElement());
            c2.remove();
        }
        System.out.println("Cola parcial: " + colaAux);

        QueueADT colaResultado = new DynamicQueueADT();

        while(!colaAux.isEmpty()){
            int num = numeroMenor(colaAux);
            colaResultado.add(num);
        }

        return colaResultado;

    }

    private static int numeroMenor(QueueADT cola) {
        QueueADT aux = new DynamicQueueADT();
        int resultado = cola.getElement();

        while(!cola.isEmpty()){
            if(resultado > cola.getElement() ){
                resultado = cola.getElement();
            }
            aux.add(cola.getElement());
            cola.remove();
        }
        boolean eliminado = false;
        while(!aux.isEmpty()){
            if(resultado == aux.getElement() && !eliminado){
                eliminado = true;
            }
            else{
                cola.add(aux.getElement());
            }
            aux.remove();
        }
        return resultado;
    }

    public static void main(String[] args) {
        StackADT pila = new DynamicStackADT();
        QueueADT cola = new DynamicQueueADT();

        pila.add(2);
        pila.add(3);
        pila.add(10);
        pila.add(4);

        cola.add(1);
        cola.add(6);
        cola.add(7);
        cola.add(5);
        cola.add(10);
        System.out.println("Pila: " + pila);
        System.out.println("Cola: " + cola);
        System.out.println("Cola final: "+combinar(cola,pila));

    }
}
