package org.uade.algorithm.dictionary;

import org.uade.structure.definition.MultipleDictionaryADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.SimpleDictionaryADT;
import org.uade.structure.implementation.dynamic.DynamicMultipleDictionaryADT;
import org.uade.structure.implementation.dynamic.DynamicSetADT;
import org.uade.structure.implementation.dynamic.DynamicSimpleDictionaryADT;

import java.util.Arrays;

import static org.uade.util.SetADTUtil.copy;

public class AdditionalDictionaryExcercise48 {
    //48. Dado un Diccionario Simple y un rango de valores [min, max], genera un Diccionario
    //Multiple con las claves dentro del rango y el conjunto de valores posibles del
    //diccionario simple.

    private static MultipleDictionaryADT ejercicio (SimpleDictionaryADT simple, int min, int max) {
        MultipleDictionaryADT dict = new DynamicMultipleDictionaryADT();
        SetADT keys = simple.getKeys();
        SetADT valuesPosibles = values(simple,keys);
        while(!keys.isEmpty()) {
            int key = keys.choose();
            if(key >= min && key <= max) {
                SetADT copiaValuesPosibles = copy(valuesPosibles);
                while(!copiaValuesPosibles.isEmpty()) {
                    int elemento = copiaValuesPosibles.choose();
                    dict.add(key,elemento);
                    copiaValuesPosibles.remove(elemento);
                }
            }
            keys.remove(key);
        }
        return dict;
    }

    private static SetADT values (SimpleDictionaryADT simple,SetADT keys){
        SetADT values = new DynamicSetADT();
        SetADT copyKeys = copy(keys);
        while(!copyKeys.isEmpty()) {
            int key = copyKeys.choose();
            values.add(simple.get(key));
            copyKeys.remove(key);
        }
        return values;
    }

    public static void main(String[] args) {
        SimpleDictionaryADT simple = new DynamicSimpleDictionaryADT();
        simple.add(2,100);
        simple.add(5,200);
        simple.add(8,100);
        simple.add(12,300);

        int min = 4;
        int max = 10;


        MultipleDictionaryADT dictMultiple = ejercicio(simple,min,max);
        System.out.println(Arrays.toString(dictMultiple.get(5)));
        System.out.println(Arrays.toString(dictMultiple.get(8)));


    }


}
