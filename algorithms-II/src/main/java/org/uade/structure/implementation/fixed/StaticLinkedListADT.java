package org.uade.structure.implementation.fixed;

import org.uade.Exception.GenericADTException;
import org.uade.Exception.FullADTException;
import org.uade.structure.definition.LinkedListADT;

public class StaticLinkedListADT<T> implements LinkedListADT<T> {
    private T[] array;
    private final int MAX = 1000;
    private int count;

    @SuppressWarnings("unchecked")
    public StaticLinkedListADT() {
        this.array = (T[]) new Object[MAX];
        this.count = 0;
    }

    @Override
    public void add(T value){
        if(count >= MAX){
            throw new FullADTException("Lista llena");
        }
        this.array[count] = value;
        count++;
    }

    @Override
    public void insert(int index, T value) {
        if (index < 0 || index > count) {
            throw new GenericADTException("Index out of bounds");
        }
        if (count >= MAX){
            throw new FullADTException("Lista llena");
        }

        for (int i = count ; i > index ; i--){
            this.array [i] = this.array[i-1];
        }

        this.array[index] = value;
        this.count++;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= count) {
            throw new GenericADTException("Index out of bounds");
        }

        for (int i = index ; i < count-1 ; i++){
            this.array[i] = this.array[i+1];
        }

        this.array[count - 1] = null;
        this.count--;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= count) {
            throw new GenericADTException("Index out of bounds");
        }
        return this.array[index];
    }

    @Override
    public int size() {
        return this.count;
    }

    @Override
    public boolean isEmpty() {
        return this.count==0;
    }
}
