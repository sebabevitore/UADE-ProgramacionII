package org.uade.algorithm.binarytree;

import org.uade.structure.definition.BinaryTreeADT;
import org.uade.structure.implementation.dynamic.DynamicBinaryTreeADT;

public class AdditionalBinaryTreeExcercise54 {
    //54. Se recibe un árbol binario(a) y dos enteros (desde y hasta) mayores a 0. Retorna la
    //cantidad de nodos ubicados entre los niveles desde y hasta del árbol a.
    //En caso de no haber ningún nodo entre los niveles dados o que éstos no sean
    //válidos (desde>hasta), se deberáretornar 0. Recordar que (en caso de existir) la raíz
    //de un árbol ocupa el nivel 1.

    private static int cantidadNodos(BinaryTreeADT arbol, int desde, int hasta) {
        if (desde > hasta || arbol.isEmpty()) {
            return 0;
        }

        return recursiva(arbol, desde, hasta, 1);
    }

    private static int recursiva(BinaryTreeADT arbol, int desde, int hasta, int nivel) {
        if (arbol.isEmpty()) {
            return 0;
        }
        if (nivel > hasta) {
            return 0;
        }

        // Paso 3: Lógica del Nodo Actual (¿Me cuento o no me cuento?)
        int cantidadActual = 0;
        if (nivel >= desde && nivel <= hasta) {
            cantidadActual = 1;
        }


        return cantidadActual + recursiva(arbol.getLeft(), desde, hasta, nivel + 1) + recursiva(arbol.getRight(), desde, hasta, nivel + 1);

    }


    public static void main(String[] args) {
        BinaryTreeADT tree = new DynamicBinaryTreeADT();
        // Insertamos: 3(Raíz, Nivel 1) -> 2(Izq, Nivel 2) -> 5(Der, Nivel 2) -> 4(Izq de 5, Nivel 3) -> 6(Der de 5, Nivel 3) -> 7(Der de 6, Nivel 4)
        tree.add(3);
        tree.add(5);
        tree.add(2);
        tree.add(4);
        tree.add(6);
        tree.add(7);

        System.out.println("ejercicio");
        System.out.println("-------------");

        // Buscamos cuántos nodos hay entre el Nivel 1 y el 4. En este caso, son todos (6 nodos).
        System.out.println("Nodos entre nivel 1 y 4: " + cantidadNodos(tree, 1, 4));

        // Prueba extra: ¿Cuántos hay en el nivel 3? (Deberían ser el 4 y el 6, total 2)
        System.out.println("Nodos en el nivel 3: " + cantidadNodos(tree, 3, 3));
    }

}
