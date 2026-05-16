package org.uade.structure.implementation;

public class NodeDict {
    private int value;
    private int key;
    private NodeDict next;

    public NodeDict(int value, int key) {
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

    public NodeDict getNext() {
        return next;
    }

    public void setNext(NodeDict next) {
        this.next = next;
    }
}
