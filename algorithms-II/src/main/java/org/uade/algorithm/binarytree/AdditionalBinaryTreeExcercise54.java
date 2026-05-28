package org.uade.algorithm.binarytree;

import com.sun.source.tree.BinaryTree;
import org.uade.Exception.GenericADTException;
import org.uade.structure.definition.BinaryTreeADT;
import org.uade.structure.implementation.dynamic.DynamicBinaryTreeADT;

public class AdditionalBinaryTreeExcercise54 {
//    54. Se recibe un árbol binario(a) y dos enteros (desde y hasta) mayores a 0. Retorna la
//    cantidad de nodos ubicados entre los niveles desde y hasta del árbol a.
//    En caso de no haber ningún nodo entre los niveles dados o que éstos no sean
//    válidos (desde>hasta), se deberá retornar 0. Recordar que (en caso de existir) la raíz
//    de un árbol ocupa el nivel 1.

    public static int cantidadEnNivel(BinaryTreeADT tree, int desde, int hasta) {
        // 1. Validaciones de rangos inválidos o árbol vacío
        if (desde > hasta || tree.isEmpty() || hasta <= 0) {
            return 0;
        }

        int totalNodosEsteNodo = 0;

        // 2. Si el nodo actual está dentro del rango, lo contamos (vale 1)
        if (desde <= 1 && hasta >= 1) {
            totalNodosEsteNodo = 1;
        }

        // 3. Tiramos la recursión bajando los niveles en los hijos
        int nodosIzquierda = cantidadEnNivel(tree.getLeft(), desde - 1, hasta - 1);
        int nodosDerecha = cantidadEnNivel(tree.getRight(), desde - 1, hasta - 1);

        // 4. Retornamos la suma total real
        return totalNodosEsteNodo + nodosIzquierda + nodosDerecha;
    }


    public static void main(String[] args) {
        BinaryTreeADT tree = new DynamicBinaryTreeADT();
        tree.add(3);
        tree.add(5);
        tree.add(2);
        tree.add(4);
        tree.add(6);
        tree.add(7);



        System.out.println("ejercicio");
        System.out.println("-------------");
        System.out.println(cantidadEnNivel(tree, 1, 4));

    }


}
