package org.uade.structure.implementation.dynamic;

import org.uade.Exception.EmptyException;
import org.uade.structure.definition.QueueADT;
import org.uade.structure.implementation.Node;

public class DynamicQueueADT implements QueueADT {
    private Node node; // inicio
    private int count;
    private Node last;

    @Override
    public int getElement() {
        if (isEmpty()) {
            throw new EmptyException("Queue is empty");
        }
        return this.node.getValue();
    }

    @Override
    public void add(int value) {
        Node newNode = new Node(value);
        if(isEmpty()) {
            this.node = newNode;
            this.last = newNode;
        }
        else {
            last.setNext(newNode);
            this.last = newNode;
        }
        this.count++;
    }

    @Override
    public void remove() {
        if(isEmpty()) {
            throw new EmptyException("Queue is empty");
        }
        this.node = this.node.getNext();
        this.count--;

        if (this.node == null) {
            this.last = null;
        }
    }

    @Override
    public boolean isEmpty() {
        return node == null;
    }

    @Override
    public String toString() {
        return "{" +
                node +
                '}';
    }


}
