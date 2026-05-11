package org.uade.structure.implementation.fixed;


import org.uade.Exception.EmptyADTException;
import org.uade.Exception.FullADTException;
import org.uade.structure.definition.PriorityQueueADT;


public class StaticPriorityQueueADT<T> implements PriorityQueueADT<T> {
    private T[] values;
    private int[] priorities;
    private int count;
    private final int MAX_SIZE = 1000;

    @SuppressWarnings("unchecked")
    public StaticPriorityQueueADT() {
        this.values = (T[]) new Object[MAX_SIZE];
        this.priorities = new int[MAX_SIZE];
        this.count = 0;
    }

    @Override
    public T getElement() {
        if(isEmpty()){
            throw new EmptyADTException("Priority Queue is empty");
        }
        return values[0];
    }

    @Override
    public int getPriority() {
        if(isEmpty()){
            throw new EmptyADTException("Priority Queue is empty");
        }
        return priorities[0];
    }

    @Override
    public void add(T value, int priority) {
        if(count == MAX_SIZE){
            throw new FullADTException("Priority queue is full");
        }

        int index = 0;
        // buscar el indice donde debe ir (mientras la prioridad sea menor o igual a la actual)
        //  >= (los de igual prioridad se encolan atrás)
        while (index < count && priorities[index] >= priority) {
            index++;
        }

        //desplazar desde final hasta index
        for (int j = count; j > index; j--) {
            values[j] = values[j - 1];
            priorities[j] = priorities[j - 1];
        }

        // ocupar el hueco
        values[index] = value;
        priorities[index] = priority;
        count++;

    }

    @Override
    public void remove() {
        if (isEmpty()) {
            throw new EmptyADTException("Priority Queue is empty");
        }
        for (int i = 0; i < count - 1; i++) {
            values[i] = values[i + 1];
            priorities[i] = priorities[i + 1];
        }
        count--;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }
}
