package org.uade.algorithm.queue;

import org.uade.structure.definition.QueueADT;
import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicQueueADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;

import static org.uade.util.QueueADTUtil.copy;

public class AdditionalQueueExcercise23 {
    //Dada una cola, verifica si es un palíndromo utilizando una pila.
    // Una cola es un palíndromo si se lee igual de izquierda a derecha que de derecha a izquierda.
    // Por ejemplo, C = [1, 2, 3, 2, 1] es un palíndromo

    private static boolean esPalindromo(QueueADT queue){
        QueueADT copia = copy(queue);
        StackADT stack = new DynamicStackADT();
        boolean esPalindromo = true;

        while(!copia.isEmpty()){
            stack.add(copia.getElement());
            copia.remove();
        }
        QueueADT copia2 = copy(queue);
        while(!stack.isEmpty()){
            int elementoStack = stack.getElement();
            int elementoCopia2 = copia2.getElement();
            if(elementoStack != elementoCopia2){
                esPalindromo = false;
                break;
            }
            stack.remove();
            copia2.remove();
        }
        return esPalindromo;
    }

    public static void main(String[] args) {
        QueueADT cola = new DynamicQueueADT();
        cola.add(1);
        cola.add(2);
        cola.add(3);
        cola.add(2);
        cola.add(1);

        boolean resultado = esPalindromo(cola);

        if (resultado){
            System.out.println("Es palindromo");
        }
        else{
            System.out.println("No es palindromo");
        }
    }

}
