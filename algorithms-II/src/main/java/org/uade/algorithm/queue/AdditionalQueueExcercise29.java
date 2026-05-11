package org.uade.algorithm.queue;

import org.uade.structure.definition.QueueADT;
import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicQueueADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;

public class AdditionalQueueExcercise29 {
    //Dada una Pila y una Cola con diferente cantidad de elementos, implementa un
    //método que transfiera elementos de una estructura a la otra hasta que ambas
    //contengan la misma cantidad de elementos
    // como tengo que devolver dos TDA, los necesito como parametros.
    // 1) metodo para contar size
    // 2) calculo la diferencia y divido por 2 para que queden igual
    //    siempre y cuando diferencia sea par, sino nunca van a quedar iguales
    // 3) el resultado del calculo anterior, es lo que paso de la cola/pila mas grande al otro.

    private static void igualar(StackADT stack, QueueADT queue, StackADT pilaResultado, QueueADT colaResultado) {
        StackADT copiaPila = org.uade.util.StackADTUtil.copy(stack);
        QueueADT copiaCola = org.uade.util.QueueADTUtil.copy(queue);

        cargarResultadosConCopias(colaResultado, copiaCola,pilaResultado,copiaPila);

        int cantidadStack = calcularSize(stack);
        int cantidadQueue = calcularSize(queue);

        int diferencia =  Math.abs(cantidadStack - cantidadQueue); // uso math.abs para valor absoluto
        if(diferencia%2 != 0){
            System.out.println("No es posible igualar cantidades, la diferencia de elementos debe ser par");
            return;
        }

        diferencia = diferencia/2;
        System.out.println("Cantidad de elementos igualar: " + diferencia);

        if(cantidadStack > cantidadQueue){
            System.out.println("Pasando " + diferencia + " elementos de pila a cola.");
            for(int i = 0; i < diferencia; i++){
                Integer elemento = pilaResultado.getElement();
                colaResultado.add(elemento);
                pilaResultado.remove();
            }
        }
        else{
            System.out.println("Pasando " + diferencia + " elementos de cola a pila.");
            for(int i = 0; i < diferencia; i++){
                Integer elemento = colaResultado.getElement();
                pilaResultado.add(elemento);
                colaResultado.remove();
            }
        }
    }

    private static void cargarResultadosConCopias(QueueADT colaResultado, QueueADT copiaCola, StackADT pilaResultado, StackADT copiaPila) {
        while (!copiaCola.isEmpty()) {
            colaResultado.add(copiaCola.getElement());
            copiaCola.remove();
        }

        StackADT aux = new DynamicStackADT();
        while(!copiaPila.isEmpty()) {
            aux.add(copiaPila.getElement());
            copiaPila.remove();
        }
        while(!aux.isEmpty()) {
            pilaResultado.add(aux.getElement());
            aux.remove();
        }

    }



    private static int calcularSize(StackADT stack) {
        StackADT copia = org.uade.util.StackADTUtil.copy(stack);
        int size = 0;
        while (!copia.isEmpty()) {
            size++;
            copia.remove();
        }
        return size;
    }

    private static int calcularSize(QueueADT queue) {
        QueueADT copia = org.uade.util.QueueADTUtil.copy(queue);
        int size = 0;
        while (!copia.isEmpty()) {
            size++;
            copia.remove();
        }
        return size;
    }




    public static void main(String[] args) {
        StackADT pila = new DynamicStackADT();
        QueueADT cola = new DynamicQueueADT();

        pila.add(1);
        pila.add(2);
        pila.add(3);

        cola.add(10);
        cola.add(20);
        cola.add(30);
        cola.add(40);
        cola.add(50);

        StackADT resultadoPila = new DynamicStackADT();
        QueueADT resultadoCola = new DynamicQueueADT();
        igualar(pila, cola,resultadoPila,resultadoCola);

        System.out.println("Igualando...");
        System.out.println("Pila original: "+pila);
        System.out.println("Cola original: "+cola);
        System.out.println("Resultado Pila: "+resultadoPila);
        System.out.println("Resultado Cola: "+resultadoCola);



    }
}
