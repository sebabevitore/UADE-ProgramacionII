package org.uade.structure.implementation.dynamic;

import org.uade.Exception.GenericADTException;
import org.uade.structure.implementation.Node;

public class DynamicLinkedListADT<T> implements org.uade.structure.definition.LinkedListADT<T> {
    private Node<T> node;
    private int count;

    @Override
    public void add(T value) {
        if(this.isEmpty()) {//si es el primero, seteo el value
            this.node = new Node<>(value);
        }

        else{
            Node<T> aux = this.node; //busco desde el primer nodo
            while(aux.getNext() != null) {//voy cambiando aux hasta encontrar el q no tenga next
                aux = aux.getNext();
            }
            aux.setNext(new Node<>(value));//seteo el valor del auxiliar porque es el ultimo nodo.
        }
        this.count++;
    }

    @Override
    public void insert(int index, T value) {
        if(index<= count && index >= 0){
            int auxCount = 0;
            Node<T> aux = this.node;
            if (index != 0){
                while(auxCount!=(index-1)){
                    auxCount++;
                    aux = aux.getNext();
                }
                Node<T> newNode = new Node<>(value);
                newNode.setNext(aux.getNext());
                aux.setNext(newNode);
            }
            else{
                Node<T> newNode = new Node<>(value);
                newNode.setNext(aux);
                this.node = newNode;
            }
            this.count++;

        }
        else{
            throw new IndexOutOfBoundsException("Indice inexistente");
        }
    }

    @Override
    public void remove(int index) throws GenericADTException {
        if(index< count && index >= 0){
            int auxCount = 0;
            Node<T> aux = this.node;

            if(index!=0){
                while(auxCount != (index-1)){
                    auxCount++;
                    aux = aux.getNext();
                }
                Node<T> remove = aux.getNext();
                aux.setNext(remove.getNext());
                remove.setNext(null);
            }
            else{ //caso especial si index es 0
                this.node = aux.getNext();
                aux.setNext(null);
            }
            this.count--;

        }
        else{
            throw new GenericADTException("Indice inexistente");
        }
    }

    @Override
    public T get(int index) throws GenericADTException {
        T valor;
        if (index < count && index >= 0) {
            int auxCount = 0;
            Node<T> auxNode = this.node;
            while(index != auxCount){
                auxCount++;
                auxNode = auxNode.getNext();
            }
            valor = auxNode.getValue();

        }
        else{
            throw new GenericADTException("Indice inexistente");
        }

        return valor;
    }

    @Override
    public int size() {
        return this.count;
    }

    @Override
    public boolean isEmpty() {
        return node == null;
    }

    @Override
    public String toString() {
        return "{" +
                node +
                '}';
    }
}
