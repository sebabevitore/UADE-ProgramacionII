package org.uade.structure.implementation.fixed;

import org.uade.Exception.GenericADTException;
import org.uade.Exception.FullADTException;
import org.uade.structure.definition.LinkedListADT;

public class StaticLinkedListADT implements LinkedListADT {
    private int [] array;
    private final int MAX = 1000;
    private int count;

    public StaticLinkedListADT() {
        this.array = new int[MAX];
        this.count = 0;
    }

    @Override
    public void add(int value){
        if(count >= MAX){
            throw new FullADTException("Lista llena");
        }
        this.array[count] = value;
        count++;
    }

    @Override
    public void insert(int index, int value) {
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

        this.array[count - 1] = 0;
        this.count--;
    }

    @Override
    public int get(int index) {
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
