package org.uade.structure.implementation.dynamic;

import org.uade.Exception.EmptyADTException;
import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.Node;

public class DynamicStackADT implements StackADT {
    private Node node; // tope
    private int count;

    @Override
    public int getElement() throws EmptyADTException {
        if(!this.isEmpty()){
            return this.node.getValue();
        }
        else{
            throw new EmptyADTException("Empty stack");
        }
    }

    @Override
    public void add(int value) {
        if(!this.isEmpty()){
            Node aux = new Node(value);
            aux.setNext(this.node); // el nuevo apunta al que antes era el primero
            this.node = aux;        // el nuevo pasa a ser la cima
            this.count++;
        }
        else{
            this.node = new Node(value);
            this.count++;
        }
    }

    @Override
    public void remove() throws EmptyADTException {
        if(!this.isEmpty()){
            this.node = this.node.getNext();
            this.count--;
        }
        else{
            throw new EmptyADTException("Empty stack");
        }

    }

    @Override
    public boolean isEmpty() {
        return node ==null;
    }

    @Override
    public String toString() {
        return "{" +
                node +
                '}';
    }
}
