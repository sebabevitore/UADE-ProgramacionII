package org.uade.algorithm.set;

import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicSetADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;


import static org.uade.util.StackADTUtil.copy;
import static org.uade.util.SetADTUtil.copy;


public class AdditionalQueueExcercise40 {
    //Dada una pila, utiliza un conjunto para identificar y eliminar los
    // elementos duplicados en la pila. Al final, la pila debe contener solo elementos únicos,
    // conservando el orden original en que aparecieron.

    private static StackADT eliminarDuplicados(StackADT stack) {
        StackADT copia = copy(stack);
        StackADT invertida = copy(stack);
        SetADT set = new DynamicSetADT();

        // lleno el set y voy invirtiendo la pila en invertida
        while(!copia.isEmpty()){
            int elemento = copia.getElement();
            set.add(elemento);
            invertida.add(elemento);
            copia.remove();
        }

        //recorro invertida, y el set, voy agregando a copia los elementos
        // de invertida que salen del set
        SetADT agregados = new  DynamicSetADT();
        while(!invertida.isEmpty()){
            int elemento = invertida.getElement();
            SetADT copiaSet = copy(set);

            while(!copiaSet.isEmpty()){
                int elementoSet = copiaSet.choose();
                if(elemento == elementoSet && !agregados.exist(elementoSet)){
                    agregados.add(elementoSet);
                    copia.add(elementoSet);
                }
                copiaSet.remove(elementoSet);
            }
            invertida.remove();
        }

        return copia;
    }


    public static void main(String[] args) {
        StackADT stack = new DynamicStackADT();
        stack.add(2);
        stack.add(3);
        stack.add(4);
        stack.add(3);
        stack.add(5);
        System.out.println("Original: ");
        System.out.println(stack);
        System.out.println("Eliminando duplicados...");
        System.out.println(eliminarDuplicados(stack));
    }


}

