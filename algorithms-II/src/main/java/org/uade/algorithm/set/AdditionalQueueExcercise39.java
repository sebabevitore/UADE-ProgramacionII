package org.uade.algorithm.set;

import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicSetADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;

import static org.uade.util.StackADTUtil.copy;
import static org.uade.util.SetADTUtil.copy;

public class AdditionalQueueExcercise39 {
    // Dadas una pila y un conjunto, implementa un algoritmo que determine si ambos
    // contienen exactamente los mismos elementos, independientemente del orden.

    private static boolean sonIguales(SetADT conjunto, StackADT pila){
        if(conjunto.isEmpty() && pila.isEmpty()){
            return true;
        }

        StackADT copyPila = copy(pila);
        SetADT setPila = new DynamicSetADT();
        int countSet = 0;
        int countStack = 0;

        while(!copyPila.isEmpty()){
            countStack++;
            int elementoPila = copyPila.getElement();
            if(conjunto.exist(elementoPila) && !setPila.exist(elementoPila)){
                setPila.add(elementoPila);
                countSet++;
            }
            else{
                return false;
            }
            copyPila.remove();

        }

        if(countSet != countStack){
            return false;
        }

        // ahora comparar de conjunto a pila
        SetADT copyConjunto = copy(conjunto);

        while(!copyConjunto.isEmpty()){
            int elemento = copyConjunto.choose();
            if(!setPila.exist(elemento)){
                return false;
            }
            copyConjunto.remove(elemento);
        }

        return true;
    }

    public static void main(String[] args) {
        SetADT set = new DynamicSetADT();

        set.add(2);
        set.add(3);


        StackADT stack = new DynamicStackADT();
        stack.add(2);
        stack.add(3);
        stack.add(3);



        boolean resultado =  sonIguales(set, stack);
        System.out.println(resultado);

    }

}
