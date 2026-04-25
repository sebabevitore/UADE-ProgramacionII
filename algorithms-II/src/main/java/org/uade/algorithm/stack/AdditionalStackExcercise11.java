package org.uade.algorithm.stack;

import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;

public class AdditionalStackExcercise11 {
    //Cree e inicialice las pilas MOD y DADA. Elimine de la pila DADA todos los elementos
    //que sean iguales a los elementos de la pila MOD

    private static void eliminarDuplicados(StackADT MOD, StackADT DADA) {
        StackADT aux = new DynamicStackADT();

        while(!DADA.isEmpty()){
            int elementoDada = DADA.getElement();
            StackADT MODCopia = copiarStack(MOD);
            DADA.remove();

            boolean found = false;

            while((!MODCopia.isEmpty())){
                int elementoCopia =  MODCopia.getElement();
                if(elementoCopia == elementoDada){
                    found = true;
                    break;
                }
                MODCopia.remove();
            }
            if (!found){
                aux.add(elementoDada);
            }
        }

        while(!aux.isEmpty()){
            DADA.add(aux.getElement());
            aux.remove();
        }



    }

    private static StackADT copiarStack(StackADT og) {
        StackADT aux = new DynamicStackADT();
        StackADT copia = new DynamicStackADT();

        // Pasamos de og a aux (og queda vacía, aux queda invertida)
        while (!og.isEmpty()) {
            aux.add(og.getElement());
            og.remove();
        }
        // Pasamos de aux a og (para restaurarla) y a copia
        while (!aux.isEmpty()) {
            int v = aux.getElement();
            og.add(v);    // Restauramos la original
            copia.add(v); // Creamos la copia
            aux.remove();
        }
        return copia;
    }

    public static void main(String[] args) {
        StackADT MOD = new DynamicStackADT();

        MOD.add(1);
        MOD.add(3);
        MOD.add(5);
        MOD.add(4);

        StackADT DADA = new DynamicStackADT();
        DADA.add(1);
        DADA.add(8);
        DADA.add(5);
        DADA.add(3);

        System.out.println("MOD: "+MOD);
        System.out.println("DADA: "+DADA);
        System.out.println("eliminando duplicados...");
        eliminarDuplicados(MOD,DADA);

        System.out.println("MOD: "+MOD);
        System.out.println("DADA: "+DADA);
    }
}
