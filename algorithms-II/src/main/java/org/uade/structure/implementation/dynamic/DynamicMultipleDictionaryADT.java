package org.uade.structure.implementation.dynamic;

import org.uade.Exception.EmptyADTException;
import org.uade.Exception.GenericADTException;
import org.uade.structure.definition.MultipleDictionaryADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.NodeMultipleDict;

import static org.uade.util.SetADTUtil.copy;

public class DynamicMultipleDictionaryADT implements MultipleDictionaryADT {
    private NodeMultipleDict node;

    @Override
    public void add(int key, int value) {
        // si la key ya esta
        NodeMultipleDict aux = this.node;
        while (aux != null) {
            if (aux.getKey() == key) {
                aux.getValues().add(value);
                return;
            }
            aux = aux.getNext();
        }
        // 2. Si llegó acá, la clave NO existe (o el diccionario está vacío)
        NodeMultipleDict nuevo = new NodeMultipleDict(key, value);
        nuevo.setNext(this.node); // El nuevo nodo apunta a la antigua cabeza
        this.node = nuevo;
    }

    @Override
    public void remove(int key) {
        if (isEmpty()) throw new EmptyADTException("Empty ADT");

        // CASO A: El elemento a borrar es la cabeza (el primero)
        if (this.node.getKey() == key) {
            this.node = this.node.getNext(); // Movemos la cabeza al siguiente nodo
            return;
        }
        // CASO B: El elemento está más adelante
        NodeMultipleDict aux = this.node;
        // Nos aseguramos de que exista un "siguiente" antes de preguntar su clave
        while (aux.getNext() != null) {
            if (aux.getNext().getKey() == key) {
                // Hacemos el bypass (si el de más allá es null, setea null de forma segura)
                aux.setNext(aux.getNext().getNext());
                return; // Ya borramos, salimos
            }
            aux = aux.getNext(); // Avanzamos de forma segura
        }
    }

    @Override
    public int[] get(int key) {
        if (isEmpty()) throw new EmptyADTException("Empty ADT");
        NodeMultipleDict aux = this.node;
        while (aux != null) {
            if (aux.getKey() == key) {
                return getValuesToArray(aux);
            }
            aux = aux.getNext();
        }
        throw new GenericADTException("Key " + key + " not found");
    }

    private int countValues(NodeMultipleDict node) {
        int count = 0;
        SetADT setOriginal = node.getValues();
        SetADT copy = copy(setOriginal);
        while(!copy.isEmpty()) {
            int aux = copy.choose();
            count++;
            copy.remove(aux);
        }
        return count;
    }

    private int[] getValuesToArray(NodeMultipleDict node) {
        int size = countValues(node);
        int [] values = new int[size];
        SetADT setOriginal = node.getValues();
        SetADT copy = copy(setOriginal);
        for(int i = 0; i < size; i++) {
            int elemento = copy.choose();
            values[i] = elemento;
            copy.remove(elemento);
        }
        return values;


    }

    @Override
    public SetADT getKeys() {
        SetADT set = new DynamicSetADT();
        NodeMultipleDict aux = this.node;
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

    @Override
    public void remove(int key, int value) {
        if (isEmpty()) throw new EmptyADTException("Empty ADT");
        NodeMultipleDict aux = this.node;
        while (aux != null) {
            if (aux.getKey() == key) {
                aux.getValues().remove(value);
                if (aux.getValues().isEmpty()) {
                    this.remove(key);
                }
                return;
            }
            aux = aux.getNext();
        }
    }


}
