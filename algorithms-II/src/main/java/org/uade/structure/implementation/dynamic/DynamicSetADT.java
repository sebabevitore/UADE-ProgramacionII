package org.uade.structure.implementation.dynamic;

import org.uade.Exception.EmptyADTException;
import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.Node;

import java.util.Random;

public class DynamicSetADT implements SetADT {
    private int count;
    private Node node;


    @Override
    public boolean exist(int value) {
        if(isEmpty()){
            return false;
        }

        Node current = this.node;

        for(int i = 0; i < count; i++){
            if(current.getValue() == value){
                return true;
            }
            current = current.getNext();
        }

        return false;
    }

    @Override
    public int choose() {
        if(isEmpty()){
            throw new EmptyADTException("Empty set");
        }

        int random = new Random().nextInt(count);
        Node current = this.node;


        for (int i = 0; i < random; i++){
            current = current.getNext();
        }

        return current.getValue();
    }

    @Override
    public void add(int value) {
        if (!exist(value)) {
            Node newNode = new Node(value);
            newNode.setNext(this.node);
            this.node = newNode;
            count++;
        }
    }

    @Override
    public void remove(int element) {
        if (isEmpty()) return;

        if (this.node.getValue() == element) {
            this.node = this.node.getNext();
            count--;
            return;
        }

        Node current = this.node;
        while (current.getNext() != null) {
            if (current.getNext().getValue() == element) {
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
