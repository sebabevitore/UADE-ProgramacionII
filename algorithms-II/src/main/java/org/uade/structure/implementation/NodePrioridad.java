package org.uade.structure.implementation;

public class NodePrioridad {
    private int value;
    private NodePrioridad next;
    private int priority;

    public NodePrioridad(int value, int priority) {
        this.value = value;
        this.priority = priority;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public NodePrioridad getNext() {
        return next;
    }

    public void setNext(NodePrioridad next) {
        this.next = next;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        // formato: Valor(Prioridad)
        String mensaje = this.value + "(P:" + this.priority + ")";

        if (this.next != null) {
            mensaje += " -> " + this.next.toString();
        }

        return mensaje;
    }
}
