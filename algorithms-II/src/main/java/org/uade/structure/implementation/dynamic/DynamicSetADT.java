package org.uade.structure.implementation.dynamic;

import org.uade.Exception.EmptyADTException;
import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.Node;

import java.util.Random;

public class DynamicSetADT<T> implements SetADT<T> {
    private int count;
    private Node<T> node;


    @Override
    public boolean exist(T value) {
        if(isEmpty()){
            return false;
        }

        Node<T> current = this.node;

        for(int i = 0; i < count; i++){
            if(current.getValue().equals(value)){
                return true;
            }
            current = current.getNext();
        }

        return false;
    }

    @Override
    public T choose() {
        if(isEmpty()){
            throw new EmptyADTException("Empty set");
        }

        int random = new Random().nextInt(count);
        Node<T> current = this.node;


        for (int i = 0; i < random; i++){
            current = current.getNext();
        }

        return current.getValue();
    }

    @Override
    public void add(T value) {
        if (!exist(value)) {
            Node<T> newNode = new Node<>(value);
            newNode.setNext(this.node);
            this.node = newNode;
            count++;
        }
    }

    @Override
    public void remove(T element) {
        if (isEmpty()) return;

        if (this.node.getValue().equals(element)) {
            this.node = this.node.getNext();
            count--;
            return;
        }

        Node<T> current = this.node;
        while (current.getNext() != null) {
            if (current.getNext().getValue().equals(element)) {
                current.setNext(current.getNext().getNext());
                count--;
                return;
            }
            current = current.getNext();
        }
    }

    @Override
    public boolean isEmpty() {
        return count==0;
    }
}
