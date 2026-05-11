package org.uade.structure.implementation.fixed;

import org.uade.structure.definition.SetADT;

public class StaticSetADT<T> implements SetADT<T> {
    @Override
    public boolean exist(T value) {
        return false;
    }

    @Override
    public T choose() {
        return null;
    }

    @Override
    public void add(T value) {

    }

    @Override
    public void remove(T element) {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
