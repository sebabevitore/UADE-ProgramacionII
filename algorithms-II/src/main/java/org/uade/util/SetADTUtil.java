package org.uade.util;

import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.dynamic.DynamicSetADT;
import org.uade.structure.implementation.fixed.StaticSetADT;
import org.uade.structure.implementation.fixed.StaticStackADT;


public class SetADTUtil {

    public static SetADT copy(SetADT set) {
        SetADT aux = getNewStack(set);
        SetADT copia = getNewStack(set);;

        while (!set.isEmpty()) {
            int element = set.choose();
            aux.add(element);
            set.remove(element);
        }

        while (!aux.isEmpty()) {
            int element = aux.choose();
            set.add(element);
            copia.add(element);
            aux.remove(element);
        }

        return copia;
    }

    private static SetADT getNewStack(SetADT set) {
        if (set instanceof StaticSetADT) {
            return new StaticSetADT();
        } else {
            return new DynamicSetADT();
        }
    }


}
