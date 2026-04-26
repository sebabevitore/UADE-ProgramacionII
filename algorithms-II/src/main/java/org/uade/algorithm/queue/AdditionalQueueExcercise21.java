package org.uade.algorithm.queue;

import org.uade.structure.definition.QueueADT;
import org.uade.structure.implementation.dynamic.DynamicQueueADT;
import org.uade.util.QueueADTUtil;

import static org.uade.util.QueueADTUtil.copy;


public class AdditionalQueueExcercise21 {
    // Cree e inicialice la cola DADA. Pase el primer elemento de la cola DADA a su última
    // posición, dejando los restantes elementos en el orden original.

    /**
     * 1) Copio dada y creo auxiliar
     * 2) Guardo el primer elemento en variable "primerElemento" y lo remuevo de copiadada
     * 3) recorro los restantes de copiadada y los guardo en cola auxiliar.
     * 4) agrego el "primerElemento" en la cola auxiliar.
     */
    public static QueueADT invertirPrimerElemento(QueueADT dada){
        if(dada.isEmpty()) return null;
        // 1
        QueueADT copia = copy(dada);
        QueueADT aux = new DynamicQueueADT();
        // 2)
        int primerElemento = copia.getElement();
        copia.remove();
        // 3)
        while(!copia.isEmpty()){
            aux.add(copia.getElement());
            copia.remove();
        }
        // 4)
        aux.add(primerElemento);

        return aux;
    }


    public static void main(String[] args) {
        QueueADT dada = new DynamicQueueADT();

        dada.add(2);
        dada.add(3);
        dada.add(4);
        dada.add(5);
        // original: {2,3,4,5}
        System.out.println("Original: " + dada);
        // resultado: {3,4,5,2}
        System.out.println("Resultado: " + invertirPrimerElemento(dada));



    }
}
