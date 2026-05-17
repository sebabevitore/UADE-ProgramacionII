package org.uade.structure.implementation.dynamic;

import org.uade.structure.definition.MultipleDictionaryADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.NodeMultipleDict;

public class DynamicMultipleDictionaryADT implements MultipleDictionaryADT {
    private NodeMultipleDict node;

    @Override
    public void add(int key, int value) {
        // si la key ya esta
        NodeMultipleDict aux = this.node;
        while (aux.getNext() != null) {
            if (aux.getKey() == key) {
                node.getValues().add(value);
                return;
            }
            aux= aux.getNext();
        }







    }

    @Override
    public void remove(int key) {

    }

    @Override
    public int[] get(int key) {
        return new int[0];
    }

    @Override
    public SetADT getKeys() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void remove(int key, int value) {

    }
}
