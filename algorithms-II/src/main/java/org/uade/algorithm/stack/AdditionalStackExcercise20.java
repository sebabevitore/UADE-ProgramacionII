package org.uade.algorithm.stack;

import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;

import static org.uade.util.StackADTUtil.copy;

public class AdditionalStackExcercise20 {
    //Cree e inicialice la pila DADA con al menos 4 elementos. Intercambie la mitad
    //superior con la mitad inferior de la pila.

    private static void ejercicio(StackADT stack){
        if (stack.isEmpty()){return;}

        int mitad = size(stack)/2;
        StackADT aux1 = new DynamicStackADT();
        StackADT aux2 = new DynamicStackADT();

        int count =1;

        //primer while para llegar hasta la mitad.
        while(count <= mitad){
            int elemento = stack.getElement();
            aux1.add(elemento);
            stack.remove();
            count++;
        }

        while(!stack.isEmpty()){
            int elemento = stack.getElement();
            aux2.add(elemento);
            stack.remove();
        }
        System.out.println(aux1 + " " + aux2);
        while(!aux1.isEmpty()){
            int elemento = aux1.getElement();
            stack.add(elemento);
            aux1.remove();
        }

        while(!aux2.isEmpty()){
            int elemento = aux2.getElement();
            stack.add(elemento);
            aux2.remove();
        }

    }

    //contabilizar
    private static int size(StackADT stack){
        if (stack.isEmpty()){return 0;}
        StackADT copia = copy(stack);
        int count = 0;
        while(!copia.isEmpty()){
            copia.remove();
            count++;
        }
        return count;
    }



    public static void main(String[] args) {
        StackADT dada = new DynamicStackADT();
        dada.add(2);
        dada.add(3);
        dada.add(6);
        dada.add(4);
        dada.add(5);

        System.out.println("Dada inicial: "+dada);
        ejercicio(dada);
        System.out.println("aplicando ejercicio...");
        System.out.println("Dada final: "+dada);

    }
}
