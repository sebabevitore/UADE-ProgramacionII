package org.uade.structure.implementation.dynamic;

import org.uade.Exception.EmptyADTException;
import org.uade.Exception.GenericADTException;
import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.SimpleDictionaryADT;
import org.uade.structure.implementation.NodeSimpleDict;

// Añadimos <K, V> a la clase y a la interfaz que implementa
public class DynamicSimpleDictionaryADT<K, V> implements SimpleDictionaryADT<K, V> {
    // El nodo ahora también debe ser genérico
    private NodeSimpleDict<K, V> node;

    @Override
    public void add(K key, V value) {
        // Validación opcional si la clave es nula
        if (key == null) {
            throw new GenericADTException("Key cannot be null");
        }

        NodeSimpleDict<K, V> aux = this.node;
        while (aux != null) {
            // CAMBIO CRÍTICO: Usamos .equals() en lugar de ==
            if (aux.getKey().equals(key)) {
                aux.setValue(value);
                return;
            }
            aux = aux.getNext();
        }

        // Si llega acá, la clave NO existe (o el diccionario está vacío)
        NodeSimpleDict<K, V> nuevoNodo = new NodeSimpleDict<>(key, value);
        nuevoNodo.setNext(this.node);
        this.node = nuevoNodo;
    }

    @Override
    public void remove(K key) {
        if (isEmpty()) {
            throw new EmptyADTException("Dictionary is empty");
        }
        if (key == null) {
            throw new GenericADTException("Key cannot be null");
        }

        // CAMBIO CRÍTICO: Usamos .equals() para el primer nodo
        if (this.node.getKey().equals(key)) {
            this.node = this.node.getNext();
            return;
        }

        NodeSimpleDict<K, V> aux = this.node;
        while (aux.getNext() != null) {
            // CAMBIO CRÍTICO: Usamos .equals() para los nodos siguientes
            if (aux.getNext().getKey().equals(key)) {
                aux.setNext(aux.getNext().getNext());
                return;
            }
            aux = aux.getNext();
        }
    }

    @Override
    public V get(K key) {
        if (isEmpty()){
            throw new EmptyADTException("Dictionary is empty");
        }
        if (key == null) {
            throw new GenericADTException("Key cannot be null");
        }

        NodeSimpleDict<K, V> aux = this.node;
        while (aux != null) {
            // CAMBIO CRÍTICO: Usamos .equals()
            if (aux.getKey().equals(key)) {
                return aux.getValue();
            }
            aux = aux.getNext();
        }

        throw new GenericADTException("Key not found");
    }

    @Override
    public SetADT<K> getKeys() {
        if(isEmpty()){
            return null;
        }
        // Tu SetADT también debería estar adaptado a generics <K>
        SetADT<K> set = new DynamicSetADT<>();

        NodeSimpleDict<K, V> aux = this.node;
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