package org.uade.structure.implementation;

public class NodeSimpleDict<K, V> {
    private K key;
    private V value;
    private NodeSimpleDict<K, V> next;

    public NodeSimpleDict(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    public K key() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V value() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public NodeSimpleDict<K, V> next() {
        return next;
    }

    public void setNext(NodeSimpleDict<K, V> next) {
        this.next = next;
    }
}