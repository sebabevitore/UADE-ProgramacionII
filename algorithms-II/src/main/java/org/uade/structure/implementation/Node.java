package org.uade.structure.implementation;

public class Node {
    int value;
    Node next;

    Node(int value) {
        this.value = value;
        this.next = null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        String mensaje = value+"";
        if (this.next != null) {
            mensaje += ","+this.next.toString();
        }
        return mensaje;
    }
}