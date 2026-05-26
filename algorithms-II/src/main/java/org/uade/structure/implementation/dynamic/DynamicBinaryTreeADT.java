package org.uade.structure.implementation.dynamic;

import org.uade.structure.definition.BinaryTreeADT;

public class DynamicBinaryTreeADT implements BinaryTreeADT {
    private Integer root;
    private BinaryTreeADT left;
    private BinaryTreeADT right;

    @Override
    public int getRoot() {
        return this.root;
    }

    @Override
    public BinaryTreeADT getLeft() {
        return this.left;
    }

    @Override
    public BinaryTreeADT getRight() {
        return this.right;
    }

    public DynamicBinaryTreeADT() {
        this.root = null;
        this.left = null;
        this.right = null;
    }

    @Override
    public void add(int value) {
        // caso 1: El arbol esta vacio, este se transforma en la raiz.
        if(isEmpty()) {
            this.root = value;
            this.left = new DynamicBinaryTreeADT();
            this.right = new DynamicBinaryTreeADT();
            return;
        }
        // caso 2: el arbol ya tiene datos. Arbol de busqueda)\
        if (value < this.root) {
            this.left.add(value);  // recursividad al add de arbol izquierda
        }
        else if (value > this.root) {
            this.right.add(value); // recursividad al add de arbol derecha
        }
        // Si el valor es igual, no hacemos nada
    }

    @Override
    public void remove(int value) {
        if (isEmpty()) {
            return;
        }

        if(value < this.root) {
            this.left.remove(value);
            return;
        }
        else if (value > this.root) {
            this.right.remove(value);
            return;
        }

        // cuando llegue aca va a estar en this.root = value

        // Caso 1: es una hoja
        if(this.left.isEmpty() && this.right.isEmpty()) {
            this.root = null;
            return;
        }
        // Caso 2: tiene solo hijo derecho
        else if (this.left.isEmpty()) {
            this.root = this.right.getRoot();
            this.left = this.right.getLeft();
            this.right = this.right.getRight();
        }
        // Caso 2: tiene solo hijo izquierdo
        else if (this.right.isEmpty()) {
            this.root = this.left.getRoot();
            this.left = this.left.getLeft();
            this.right = this.left.getRight();
        }
        // si tiene dos hijos: se reemplaza por el valor mas alto del arbol izquierdo (funcion maximo)
        else{
            int treeMax = maximo(this.left);
            // se pisa la raiz con ese valor
            this.root = treeMax;
            // llamo de nuevo a la funcion para remover el duplicado que quedaria abajo,
            // pero arrancando desde el arbol izq.
            this.left.remove(treeMax);
        }

    }

    private int maximo(BinaryTreeADT tree) {
        if(tree.getRight().isEmpty()) {
            return tree.getRoot();
        }
        return maximo(tree.getRight());
    }

    @Override
    public boolean isEmpty() {
        return this.root == null;
    }
}
