package org.uade.algorithm.stack;

import org.uade.Exception.EmptyADTException;
import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;

import static org.uade.util.StackADTUtil.esPar;

public class AdditionalStackExcercise12 {

    // 12)
    //  Cree e inicialice las pilas DADA, VALOR, PAR, IMPAR. Cargue la pila VALOR con al
    //  menos un valor. Determine si la cantidad de elementos de la pila DADA es par. Si es
    //  par, pase el elemento del tope de la pila VALOR a la pila PAR y sino páselo a la pila
    //  IMPAR.

    private static void ejercicio(StackADT dada, StackADT valor, StackADT par, StackADT impar){
        if(!dada.isEmpty()){
            int tope = valor.getElement();
            valor.remove();  // porq el ejercicio pide MOVER

            if(esPar(dada)){
                par.add(tope);
            }
            else{
                impar.add(tope);
            }
        }
        else{
            throw new EmptyADTException("Empty Stack");
        }
    }



    public static void main(String[] args) {
        StackADT dada = new DynamicStackADT();
        StackADT valor = new DynamicStackADT();
        StackADT par = new DynamicStackADT();
        StackADT impar = new DynamicStackADT();

    }
}
