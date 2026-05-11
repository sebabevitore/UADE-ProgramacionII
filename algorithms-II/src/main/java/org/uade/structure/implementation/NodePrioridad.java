package org.uade.structure.implementation;

public class NodePrioridad<T> {
    private T value;
    private NodePrioridad<T> next;
    private int priority;

    public NodePrioridad(T value, int priority) {
        this.value = value;
        this.priority = priority;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public NodePrioridad<T> getNext() {
        return next;
    }

    public void setNext(NodePrioridad<T> next) {
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
