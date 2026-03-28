package org.uade.algorithm;


import org.uade.structure.implementation.LinkedListADT;

public class Pruebas {
    public static void main(String[] args) {
        LinkedListADT lista = new LinkedListADT();
        System.out.println("Lista vacia: " + lista);

        lista.add(1);
        lista.add(2);
        lista.add(3);

        System.out.println("Lista con adds: " + lista);

        lista.add(5);

        System.out.println("Lista con add 5: " + lista);

        lista.insert(0,40);
        System.out.println("Lista con insert 0,40: " + lista);

        lista.remove(4);
        System.out.println("Lista con remove del indice 4 (valor 5): " + lista);

        System.out.println("Valor del indice 0: "+lista.get(0));

    }

}
