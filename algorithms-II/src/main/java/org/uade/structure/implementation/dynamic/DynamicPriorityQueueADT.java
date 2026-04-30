package org.uade.structure.implementation.dynamic;

import org.uade.Exception.EmptyADTException;
import org.uade.structure.definition.PriorityQueueADT;

import org.uade.structure.implementation.NodePrioridad;

public class DynamicPriorityQueueADT implements PriorityQueueADT {
    private NodePrioridad node; // inicio



    @Override
    public int getElement() {
        if (isEmpty()) {
            throw new EmptyADTException("Queue is empty");
        }
        return node.getValue();
    }

    @Override
    public int getPriority() {
        if (isEmpty()) {
            throw new EmptyADTException("Queue is empty");
        }
        return node.getPriority();
    }

    @Override
    public void add(int value, int priority) {
        NodePrioridad nuevo = new NodePrioridad(value, priority);

        // si esta vacia, o si el nuevo tiene mayor prioridad que el nodo cabeza
        if (isEmpty() || priority > this.node.getPriority()) {
            nuevo.setNext(this.node);
            this.node = nuevo;
            return;
        }

        NodePrioridad current = this.node;

        // buscar mientras el siguiente no sea nulo Y tenga mas (o igual) prioridad que el nuevo
        while (current.getNext() != null && current.getNext().getPriority() >= priority) {
            current = current.getNext();
        }

        // el current es el nodo que va a tener como siguiente el nuevo nodo.
        // al nuevo le seteo como siguiente el que ahora current tiene como siguiente.
        nuevo.setNext(current.getNext());
        // y a current le seteo como siguiente el nuevo.
        current.setNext(nuevo);
    }

    @Override
    public void remove() {
        if (isEmpty()) {
            throw new EmptyADTException("Queue is empty");
        }
        this.node = this.node.getNext();
    }

    @Override
    public boolean isEmpty() {
        return this.node == null;
    }

    @Override
    public String toString() {
        return "{" +
                node +
                '}';
    }


}
