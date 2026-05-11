package org.uade.structure.implementation.fixed;

import org.uade.Exception.EmptyADTException;
import org.uade.Exception.FullADTException;
import org.uade.structure.definition.QueueADT;

public class StaticQueueADT<T> implements QueueADT<T> {
    private T[] array;
    private int count;
    private int size = 1000;

    @SuppressWarnings("unchecked")
    public StaticQueueADT() {
        this.array = (T[]) new Object[size];
        this.count = 0;
    }

    @Override
    public T getElement() {
        if(isEmpty()){
            throw new EmptyADTException("Queue is empty");
        }
        return array[0];
    }

    @Override
    public void add(T value) {
        if(count == size){
            throw new FullADTException("Queue is full");
        }
        array[count] = value;
        count++;
    }

    @Override
    public void remove() {
        if(this.isEmpty()){
            throw new EmptyADTException("Queue is empty");
        }
        for (int i = 0; i < this.count - 1; i++) {
            this.array[i] = this.array[i + 1];
        }
        //  limpiar la ult. posición
        this.array[this.count - 1] = null;
        this.count--;

    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }




}
