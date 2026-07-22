package org.uade.algorithm.dictionary;

import org.uade.Exception.EmptyADTException;
import org.uade.Exception.GenericADTException;
import org.uade.structure.definition.PriorityQueueADT;
import org.uade.structure.definition.QueueADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.SimpleDictionaryADT;
import org.uade.structure.implementation.dynamic.DynamicPriorityQueueADT;
import org.uade.structure.implementation.dynamic.DynamicQueueADT;
import org.uade.structure.implementation.dynamic.DynamicSetADT;
import org.uade.structure.implementation.dynamic.DynamicSimpleDictionaryADT;

import static org.uade.util.QueueADTUtil.copy;
import static org.uade.util.QueueADTUtil.print;

public class AdditionalDictionaryExcercise44 {
    //Crear una aplicacion que dado una serie de elementos, podamos saber la cantidad
    //de ocurrencias del mismo. Se deben usar al menos dos TDA distintos y garantizar el
    //orden de los elementos

    //me faltaria un metodo que devuelva la cantidad de veces en el mismo orden de la cola que inserta, pero me dio fiaca, seria asi:
    // recibe la cola,
    // llama al adddict con esa cola.
    // recorre una copia de la cola, en este while lo que haria es revisar es agarrar cada elemento, y mostrar su value del dict para imprimir las ocurrencias, de tal manera que se imprima en orden. Seria un metodo para imprimir.
    // hacelo como te digo (igual revisa si es logico como lo haria yo, y luego pasamelo asi lo agrego a mi codigo.

    // Método para imprimir las ocurrencias garantizando el orden original
    private static void imprimirOcurrenciasEnOrden(QueueADT cola) {
        // 1. Llamamos a tu método para llenar el diccionario
        SimpleDictionaryADT dict = addDict(cola);

        // 2. Hacemos una copia para recorrerla sin vaciar la original
        QueueADT copiaCola = copy(cola);

        // 3. Usamos un conjunto auxiliar para no imprimir duplicados
        SetADT yaImpresos = new DynamicSetADT();

        System.out.println("Ocurrencias (en orden de aparición):");

        // 4. Recorremos la copia elemento por elemento
        while(!copiaCola.isEmpty()){
            int elemento = copiaCola.getElement();

            // Si el elemento no está en el conjunto de impresos, lo mostramos
            if(!yaImpresos.exist(elemento)){
                System.out.println("El " + elemento + " aparece: " + dict.get(elemento) + " veces");
                yaImpresos.add(elemento); // Lo anotamos para no volver a imprimirlo
            }

            copiaCola.remove();
        }
    }

    private static SimpleDictionaryADT addDict(QueueADT queue){
        SimpleDictionaryADT dict = new DynamicSimpleDictionaryADT();
        QueueADT colaCopia = copy(queue);
        SetADT clavesRegistradas = new DynamicSetADT();

        while(!colaCopia.isEmpty()){
            int elemento = colaCopia.getElement();

            if(clavesRegistradas.exist(elemento)){
                // caso 1: ya esta en dict, suma 1.
                int cantidadActual = dict.get(elemento);
                dict.add(elemento, cantidadActual + 1);
            } else {
                // caso 2: dict vacio/no esta dict -> se agrega con 1.
                dict.add(elemento, 1);
                clavesRegistradas.add(elemento); // Registramos que ya inicializamos esta clave
            }
            colaCopia.remove();
        }

        return dict;
    }




    public static void main(String[] args) {
        QueueADT cola = new DynamicQueueADT();

        cola.add(2);
        cola.add(3);
        cola.add(4);
        cola.add(5);
        cola.add(2);
        cola.add(4);

        System.out.println("Cola original:");
        print(cola);
        System.out.println("-----------------");

        // Llamamos al método que hace todo el trabajo
        imprimirOcurrenciasEnOrden(cola);
    }

}
