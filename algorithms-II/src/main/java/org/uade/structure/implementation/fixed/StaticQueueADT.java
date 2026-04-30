package org.uade.structure.implementation.fixed;

import org.uade.Exception.EmptyException;
import org.uade.Exception.OverflowException;
import org.uade.structure.definition.QueueADT;

public class StaticQueueADT implements QueueADT {
    private int[] array;
    private int count;
    private int size = 1000;

    public StaticQueueADT() {
        this.array = new int[size];
        this.count = 0;
    }

    @Override
    public int getElement() {
        if(isEmpty()){
            throw new EmptyException("Queue is empty");
        }
        return array[0];
    }

    @Override
    public void add(int value) {
        if(count == size){
            throw new OverflowException("Queue is full");
        }
        array[count] = value;
        count++;

    }

    @Override
    public void remove() {
        if(this.isEmpty()){
            throw new EmptyException("Queue is empty");
        }
        for (int i = 0; i < this.count - 1; i++) {
            this.array[i] = this.array[i + 1];
        }
        //  limpiar la ult. posición
        this.array[this.count - 1] = 0;
        this.count--;

    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }




}
