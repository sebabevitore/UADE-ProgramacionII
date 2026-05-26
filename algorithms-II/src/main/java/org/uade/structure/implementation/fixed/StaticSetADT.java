package org.uade.structure.implementation.fixed;

import org.uade.Exception.EmptyADTException;
import org.uade.Exception.FullADTException;
import org.uade.structure.definition.SetADT;

import java.util.Random;

public class StaticSetADT implements SetADT {
    private int MAX_SIZE = 1000;
    private int [] values;
    private int count;


    public StaticSetADT() {
        this.values = new int [MAX_SIZE];
        this.count = 0;
    }


    @Override
    public boolean exist(int value) {
        for (int i = 0; i < count; i++) {
            if (values[i] == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int choose() {
        int randomIndex = new Random().nextInt(count);
        return values[randomIndex];
    }

    @Override
    public void add(int value) {
        if (count == MAX_SIZE) {
            throw new FullADTException("Set is full");
        }
        if (this.exist(value)) {
            return;
        }
        this.values[count] = value;
        count++;
    }

    @Override
    public void remove(int element) {
        if (this.isEmpty()) {
            throw new EmptyADTException("Set is empty");
        }

        for (int i = 0; i < count; i++) {
            if (values[i] == element) {
                // Lo pisamos con el último elemento válido actual
                this.values[i] = values[count - 1];
                count--;
                return;
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }
}
