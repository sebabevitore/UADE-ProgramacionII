package org.uade.algorithm.binarytree;

import org.uade.structure.definition.BinaryTreeADT;
import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicBinaryTreeADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;

import static org.uade.util.StackADTUtil.copy;

public class AdditionalBinaryTreeExcercise57 {
    //57.   Dado un árbol binario, devuelva una pila que contenga los elementos de la rama
    //      más larga del árbol de entrada


    private static StackADT ramaMasLarga(BinaryTreeADT arbol){
        if(arbol.isEmpty()){
            return new DynamicStackADT();
        }

        StackADT stackIzq = ramaMasLarga(arbol.getLeft());
        StackADT stackDer = ramaMasLarga(arbol.getRight());
        StackADT rutaMasLarga;

        int elementosIzq = contarElementos(stackIzq);
        int elementosDer = contarElementos(stackDer);

        if (elementosIzq > elementosDer) {
            rutaMasLarga = stackIzq;
        }
        else{
            rutaMasLarga = stackDer;
        }

        rutaMasLarga.add(arbol.getRoot());

        return  rutaMasLarga;
    }

    private static int contarElementos(StackADT stack){
        StackADT copia = copy(stack);
        int count = 0;
        while (!copia.isEmpty()){
            count += 1;
            copia.remove();
        }
        return count;
    }

    public static void main(String[] args) {
        BinaryTreeADT arbol = new DynamicBinaryTreeADT();

        // Nivel 1
        arbol.add(10);
        // Nivel 2
        arbol.add(5);
        arbol.add(15);
        // Nivel 3
        arbol.add(2);
        arbol.add(7);
        arbol.add(12);
        arbol.add(20);
        // Nivel 4 (Desempata a favor de la rama derecha)
        arbol.add(25);

        // Visualmente el árbol es:
        //        10
        //      /    \
        //     5      15
        //    / \    /  \
        //   2   7  12  20
        //                \
        //                 25

        StackADT ramaGanadora = ramaMasLarga(arbol);

        System.out.println("Elementos de la rama más larga (desde la raíz hacia la hoja):");

        // Vaciamos la pila para imprimirla.
        while (!ramaGanadora.isEmpty()) {
            System.out.print(ramaGanadora.getElement() + " -> ");
            ramaGanadora.remove();
        }
        System.out.println("FIN");
    }
}
