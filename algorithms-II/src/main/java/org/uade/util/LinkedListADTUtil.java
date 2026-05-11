package org.uade.util;

import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.implementation.dynamic.DynamicLinkedListADT;
import org.uade.structure.implementation.fixed.StaticLinkedListADT;


public class LinkedListADTUtil {

    public static <T> void print(LinkedListADT<T> list) {
        LinkedListADT<T> copy = copy(list);
        if (copy.isEmpty()) {
            System.out.println("La lista está vacía.");
            return;
        }

        for (int i = 0; i < copy.size(); i++) {
            System.out.print(copy.get(i));
            if (i < copy.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();

    }

    public static <T> LinkedListADT<T> copy(LinkedListADT<T> list) {
        LinkedListADT<T> aux = getNewLinkedList(list);
        for (int i = 0; i < list.size(); i++) {
            aux.add(list.get(i));
        }
        return aux;
    }

    public static <T> boolean areEqual(LinkedListADT<T> listOne, LinkedListADT<T> listTwo) {
        LinkedListADT<T> copy1 = copy(listOne);
        LinkedListADT<T> copy2 = copy(listTwo);

        if (copy1.size() != copy2.size()) {
            return false;
        }

        for (int i = 0; i < copy1.size(); i++) {
            if (!copy1.get(i).equals(copy2.get(i))) {
                return false;
            }
        }

        return true;
    }


    private static <T> LinkedListADT<T> getNewLinkedList(LinkedListADT<T> list) {
        if (list instanceof StaticLinkedListADT) {
            return new StaticLinkedListADT<>();
        } else {
            return new DynamicLinkedListADT<>();
        }
    }
}