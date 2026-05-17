package org.uade.structure.implementation;

import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.dynamic.DynamicSetADT;

public class NodeMultipleDict {
    private int key;
    private SetADT values;
    private NodeMultipleDict next;

    public NodeMultipleDict(int key, int value) {
        this.key = key;
        this.values = new DynamicSetADT();
        this.values.add(value);
        this.next = null;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public SetADT getValues() {
        return values;
    }

    public void setValues(SetADT values) {
        this.values = values;
    }

    public NodeMultipleDict getNext() {
        return next;
    }

    public void setNext(NodeMultipleDict next) {
        this.next = next;
    }
}
