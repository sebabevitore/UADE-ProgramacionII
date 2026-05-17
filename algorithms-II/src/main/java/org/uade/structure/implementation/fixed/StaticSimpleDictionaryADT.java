package org.uade.structure.implementation.fixed;

import org.uade.Exception.EmptyADTException;
import org.uade.Exception.FullADTException;
import org.uade.Exception.GenericADTException;
import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.SimpleDictionaryADT;
import org.uade.structure.implementation.dynamic.DynamicSetADT;

public class StaticSimpleDictionaryADT implements SimpleDictionaryADT {
    private int [] keys ;
    private int [] values;
    private int count; // elementos reales
    private static final int MAX_SIZE = 1000;

    public StaticSimpleDictionaryADT() {
        this.keys = new int[MAX_SIZE];
        this.values = new int[MAX_SIZE];;
        this.count = 0;
    }

    @Override
    public void add(int key, int value) {
        if (count == MAX_SIZE) {
            throw new FullADTException("Dictionary is full");
        }
        for(int i = 0; i < count; i++) {
            if(keys[i] == key) {
                values[i] = value;
                return;
            }
        }
        keys[count] = key;
        values[count] = value;
        count++;
    }

    @Override
    public void remove(int key) {
        if(isEmpty()){return;}
        for (int i = 0; i < count; i++){
            if(keys[i] == key) {
                keys[i]=keys[count-1];
                values[i]=values[count-1];
                count--;
                return;
            }
        }
    }

    @Override
    public int get(int key) {
        if(isEmpty()){
            throw new EmptyADTException("Dictionary is empty");
        }

        for(int i = 0; i < count; i++){
            if(keys[i] == key) {
                return values[i];
            }
        }

        throw new GenericADTException("Key not found");
    }

    @Override
    public SetADT getKeys() {
        if(isEmpty()){return null;}
        SetADT set = new DynamicSetADT();
        for(int i = 0; i < count; i++){
            set.add(keys[i]);
        }
        return set;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }
}
