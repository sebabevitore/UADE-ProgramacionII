package org.uade.structure.implementation.dynamic;

import org.uade.Exception.EmptyADTException;
import org.uade.Exception.GenericADTException;
import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.SimpleDictionaryADT;
import org.uade.structure.implementation.NodeDict;

public class DynamicSimpleDictionaryADT implements SimpleDictionaryADT {
    private NodeDict node;

    @Override
    public void add(int key, int value) {
        //Si la clave ya existe, reemplazamos el valor y salimos
        NodeDict aux = this.node;
        NodeDict aux2 = null;
        while (aux != null) {
            if(aux.getKey() == key) {
                aux.setValue(value);
                return;
            }
            aux2 = aux;
            aux = aux.getNext();
        }
        //si  llega aca, la clave NO existe (o el diccionario esta vacio)
        NodeDict nuevoNodo = new NodeDict(key, value);
        nuevoNodo.setNext(this.node);
        this.node = nuevoNodo;
    }

    @Override
    public void remove(int key) {
        if (isEmpty()) {
            throw new EmptyADTException("Dictionary is empty");
        }
        if (this.node.getKey() == key) {
            this.node = this.node.getNext();
            return;
        }

        NodeDict aux = this.node;
        while (aux.getNext() != null) {
            if (aux.getNext().getKey() == key) {
                // si aux.getNext().getNext() es null, al hacer ese ultimo get trae null, entonces setea null,
                // asi q funciona si es en el medio o en el ultimo nodo.
                aux.setNext(aux.getNext().getNext());
                return;
            }
            aux = aux.getNext();
        }

    }

    @Override
    public int get(int key) {
        if (isEmpty()){
            throw new EmptyADTException("Dictionary is empty");
        }

        NodeDict aux = this.node;
        while (aux != null) {
            if (aux.getKey() == key) {
                return aux.getValue();
            }
            aux = aux.getNext();
        }

        // si no encontro la key, llega aca
        throw new GenericADTException("Key not found");
    }

    @Override
    public SetADT getKeys() {
        if(isEmpty()){
            return null;
        }
        SetADT set = new DynamicSetADT();

        NodeDict aux = this.node;
        while (aux != null) {
            set.add(aux.getKey());
            aux = aux.getNext();
        }

        return set;
    }

    @Override
    public boolean isEmpty() {
        return this.node == null;
    }
}
