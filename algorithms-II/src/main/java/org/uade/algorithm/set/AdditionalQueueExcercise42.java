package org.uade.algorithm.set;

//Crear una aplicacion que dado dos conjuntos devuelva un nuevo conjunto que
//contenga los elementos que están en uno u otro conjunto, pero no en ambos
// esta pidiendo la diferencia simetrica (no dif comun)

import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.dynamic.DynamicSetADT;

import static org.uade.util.SetADTUtil.copy;
import static org.uade.util.SetADTUtil.print;

public class AdditionalQueueExcercise42 {

    private static SetADT ejercicio(SetADT setA, SetADT setB) {
        SetADT resultado = new DynamicSetADT();

        SetADT copy = copy(setA);
        while(!copy.isEmpty()){
            int elemento = copy.choose();
            if(!setB.exist(elemento)){
                resultado.add(elemento);
            }
            copy.remove(elemento);
        }

        SetADT copyB = copy(setB);
        while(!copyB.isEmpty()){
            int elemento = copyB.choose();
            if(!setA.exist(elemento)){
                resultado.add(elemento);
            }
            copyB.remove(elemento);
        }

        return resultado;
    }


    public static void main(String[] args) {
        SetADT setA = new DynamicSetADT();
        SetADT setB = new DynamicSetADT();

        setA.add(5);
        setA.add(3);
        setA.add(4);

        setB.add(5);
        setB.add(6);
        setB.add(7);

        System.out.println("Original A: ");
        print(setA);
        System.out.println("Original B:");
        print(setB);

        System.out.println("Diferencia: ");
        print(ejercicio(setA, setB));

    }

}
