package org.uade.util;

import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.dynamic.DynamicSetADT;
import org.uade.structure.implementation.fixed.StaticSetADT;
import org.uade.structure.implementation.fixed.StaticStackADT;


public class SetADTUtil {

    public static <T> void print(SetADT<T> set) {
        SetADT<T> copy = copy(set);
        while (!copy.isEmpty()) {
            T element = copy.choose();
            System.out.println(element);
            copy.remove(element);
        }
    }

    public static <T> SetADT<T> copy(SetADT<T> set) {
        SetADT<T> aux = getNewStack(set);
        SetADT<T> copia = getNewStack(set);

        while (!set.isEmpty()) {
            T element = set.choose();
            aux.add(element);
            set.remove(element);
        }

        while (!aux.isEmpty()) {
            T element = aux.choose();
            set.add(element);
            copia.add(element);
            aux.remove(element);
        }

        return copia;
    }

    private static <T> SetADT<T> getNewStack(SetADT<T> set) {
        if (set instanceof StaticSetADT) {
            return new StaticSetADT<>();
        } else {
            return new DynamicSetADT<>();
        }
    }


}
