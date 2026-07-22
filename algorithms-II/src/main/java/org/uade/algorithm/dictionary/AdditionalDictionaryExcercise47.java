package org.uade.algorithm.dictionary;

import org.uade.structure.definition.MultipleDictionaryADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.SimpleDictionaryADT;
import org.uade.structure.implementation.dynamic.DynamicMultipleDictionaryADT;
import org.uade.structure.implementation.dynamic.DynamicSimpleDictionaryADT;

import java.util.Arrays;

public class AdditionalDictionaryExcercise47 {
    //47.   Dado un Diccionario Simple, crea un Diccionario Multiple donde las claves sean los
    //      valores únicos del diccionario simple, y los valores asociados sean las claves del
    //      diccionario simple que tienen ese valor

    private static MultipleDictionaryADT simpleAMultiple(SimpleDictionaryADT simpleDict) {
        MultipleDictionaryADT multipleDict = new DynamicMultipleDictionaryADT();
        SetADT keysSimple = simpleDict.getKeys();

        while(!keysSimple.isEmpty()){
            int keyOriginal = keysSimple.choose();
            int valueOriginal = simpleDict.get(keyOriginal);

            multipleDict.add(valueOriginal, keyOriginal);

            keysSimple.remove(keyOriginal);
        }
        return multipleDict;
    }

    public static void main(String[] args) {
        SimpleDictionaryADT simpleDict = new DynamicSimpleDictionaryADT();
        simpleDict.add(3,1);
        simpleDict.add(2,3);
        simpleDict.add(4,3);
        simpleDict.add(5,1);

        MultipleDictionaryADT multiple = simpleAMultiple(simpleDict);
        System.out.println(Arrays.toString(multiple.get(1)));
        System.out.println(Arrays.toString(multiple.get(3)));


    }


}
