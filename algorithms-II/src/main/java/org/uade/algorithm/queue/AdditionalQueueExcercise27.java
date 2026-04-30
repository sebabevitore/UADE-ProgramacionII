package org.uade.algorithm.queue;

import org.uade.structure.definition.QueueADT;
import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicQueueADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;

import static org.uade.util.QueueADTUtil.copy;
import static org.uade.util.StackADTUtil.copy;


public class AdditionalQueueExcercise27 {
    //Dada una Pila y una Cola, implementa un método que determine si contienen
    //exactamente los mismos elementos, aunque en diferente orden.

    private static boolean mismosElementos(StackADT pila, QueueADT cola){
        boolean resultado = true;
        QueueADT copiaCola = copy(cola);

        if(!mismaCantElementos(pila,cola)){
            return false;
        }

        while(!copiaCola.isEmpty()){
            StackADT copiaPila =copy(pila);
            int elementoCola = copiaCola.getElement();

            //cant de ocurrencias del elemento en su propia cola
            int ocurrenciasCola = ocurrenciasElemento(cola,elementoCola);
            //cant de ocurrencias del elementoCola en la PILA.
            int ocurrenciasPila = ocurrenciasElemento(pila, elementoCola);
            //si no son iguales, entonces terminó
            if(ocurrenciasPila != ocurrenciasCola){
                return false;
            }
            copiaCola.remove();
        }
        return resultado;
    }

    private static int ocurrenciasElemento(StackADT pila, int numero) {
        int ocurrencias = 0;
        StackADT copia = copy(pila);
        while(!copia.isEmpty()){
            if(copia.getElement() == numero){
                ocurrencias++;
            }
            copia.remove();
        }
        return ocurrencias;
    }

    private static int ocurrenciasElemento(QueueADT cola, int numero) {
        int ocurrencias = 0;
        QueueADT copia = copy(cola);
        while(!copia.isEmpty()){
            if(copia.getElement() == numero){
                ocurrencias++;
            }
            copia.remove();
        }
        return ocurrencias;
    }

    private static boolean mismaCantElementos(StackADT pila, QueueADT cola){
        StackADT copiaPila = copy(pila);
        QueueADT copiaCola = copy(cola);

        int countPila = 0;
        int countCola = 0;
        while(!copiaPila.isEmpty()){
            copiaPila.remove();
            countPila++;
        }
        while(!copiaCola.isEmpty()){
            copiaCola.remove();
            countCola++;
        }

        return countPila == countCola;
    }


    public static void main(String[] args) {
        QueueADT cola = new DynamicQueueADT();
        StackADT pila = new DynamicStackADT();

        cola.add(1);
        cola.add(2);
        cola.add(3);

        pila.add(1);
        pila.add(2);
        pila.add(2);

        if(mismosElementos(pila, cola)){
            System.out.println("Mismos elementos");
        }
        else{
            System.out.println("distintos elementos");
        }

    }
}
