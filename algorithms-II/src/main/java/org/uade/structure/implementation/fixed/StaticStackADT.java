package org.uade.structure.implementation.fixed;

import org.uade.Exception.EmptyADTException;
import org.uade.Exception.FullADTException;
import org.uade.structure.definition.StackADT;

public class StaticStackADT implements StackADT {
    private int[] array;
    private int count;
    private int max = 1000;

    public StaticStackADT() {
        this.array = new int[max];
        this.count = 0;
    }

    @Override
    public int getElement() {
        if (!isEmpty()){
            return this.array[this.count-1];
        }
        else{
            throw new EmptyADTException("Pila vacia");
        }
    }

    @Override
    public void add(int value) {
        if(this.count < this.array.length){
            array[this.count] = value;
            this.count++;
        }
        else{
            throw new FullADTException("Pila llena");
        }

    }

    @Override
    public void remove() {
        if (this.isEmpty()){
            throw new EmptyADTException("Pila vaca");
        }else{
            this.count--;

        }
    }

    @Override
    public boolean isEmpty() {
        return this.count==0;
    }

}
