package org.uade.algorithm.dictionary;

import org.uade.structure.definition.MultipleDictionaryADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.SimpleDictionaryADT;
import org.uade.structure.implementation.dynamic.DynamicMultipleDictionaryADT;
import org.uade.structure.implementation.dynamic.DynamicSimpleDictionaryADT;

public class AdditionalDictionaryExcercise49 {
    //49. Dado un Diccionario Simple y un Diccionario Multiple, implementa un método que
    //  genere un único Diccionario Simple combinando los valores según las siguientes
    //  reglas:
    //  ○ Si una clave está en ambos, toma el valor del diccionario múltiple con la prioridad más alta.
    //  ○ Si está solo en uno, conserva su valor.

    private static SimpleDictionaryADT fusion(SimpleDictionaryADT simple, MultipleDictionaryADT multiple){
        SimpleDictionaryADT resultado = new DynamicSimpleDictionaryADT();

        SetADT keysSimple = simple.getKeys();
        SetADT keysMultiple = multiple.getKeys();

        // caso 1: revisar simple
        while(!keysSimple.isEmpty()){
            int keySimple = keysSimple.choose();
            int valueSimple = simple.get(keySimple);
            // caso A: existe en ambos
            if (!keysMultiple.isEmpty() && keysMultiple.exist(keySimple)){
                int [] valuesMultiple = multiple.get(keySimple);
                int mayor = valueMayor(valuesMultiple);
                resultado.add(keySimple, mayor);
                keysMultiple.remove(keySimple);
            }
            // caso B: solo existe en dictSimple
            else{
                resultado.add(keySimple, valueSimple);
            }
            keysSimple.remove(keySimple);
        }
        // caso 2: revisar lo que queda de dictMultiple
        while(!keysMultiple.isEmpty()){
            int keyMultiple = keysMultiple.choose();
            int valueMayor = valueMayor(multiple.get(keyMultiple));
            resultado.add(keyMultiple,valueMayor);
            keysMultiple.remove(keyMultiple);
        }
        return resultado;
    }

    private static int valueMayor (int[] valuesMultiple){
        int mayor = valuesMultiple[0];
        for (int i = 0; i<valuesMultiple.length; i++){
            if (valuesMultiple[i]>mayor){
                mayor = valuesMultiple[i];
            }
        }
        return mayor;
    }


    public static void main(String[] args) {
        // Inicializamos las estructuras
        SimpleDictionaryADT simple = new DynamicSimpleDictionaryADT();
        MultipleDictionaryADT multiple = new DynamicMultipleDictionaryADT();

        // ----------------------------------------------------
        // CASO 1: Clave en ambos (Debe ganar el mayor del múltiple)
        // ----------------------------------------------------
        simple.add(1, 10);

        multiple.add(1, 5);
        multiple.add(1, 20);
        multiple.add(1, 15);

        // ----------------------------------------------------
        // CASO 2: Clave solo en el Simple (Debe conservar su valor)
        // ----------------------------------------------------
        simple.add(2, 30);

        // ----------------------------------------------------
        // CASO 3: Clave solo en el Múltiple (Debe quedar el mayor)
        // ----------------------------------------------------
        multiple.add(3, 40);
        multiple.add(3, 50);

        // Ejecutamos la fusión
        System.out.println("Ejecutando la fusión de diccionarios...");
        SimpleDictionaryADT resultado = fusion(simple, multiple);

        // Imprimimos y validamos los resultados
        System.out.println("----------------------------------------------------");
        System.out.println("Resultados:");
        System.out.println("Clave 1 (Esperado 20) -> Obtenido: " + resultado.get(1));
        System.out.println("Clave 2 (Esperado 30) -> Obtenido: " + resultado.get(2));
        System.out.println("Clave 3 (Esperado 50) -> Obtenido: " + resultado.get(3));
        System.out.println("----------------------------------------------------");
    }
}
