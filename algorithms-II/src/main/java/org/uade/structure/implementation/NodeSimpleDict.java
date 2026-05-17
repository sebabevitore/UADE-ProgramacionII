package org.uade.structure.implementation;

public class NodeSimpleDict {
    private int value;
    private int key;
    private NodeSimpleDict next;

    public NodeSimpleDict(int value, int key) {
        this.value = value;
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public NodeSimpleDict getNext() {
        return next;
    }

    public void setNext(NodeSimpleDict next) {
        this.next = next;
    }
}
