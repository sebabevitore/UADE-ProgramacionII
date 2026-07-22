package org.uade.algorithm.binarytree;

import org.uade.structure.definition.BinaryTreeADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.SimpleDictionaryADT;
import org.uade.structure.implementation.dynamic.DynamicBinaryTreeADT;
import org.uade.structure.implementation.dynamic.DynamicSetADT;
import org.uade.structure.implementation.dynamic.DynamicSimpleDictionaryADT;

public class AdditionalBinaryTreeExcercise56 {
    //56. Se recibe un arbol binario y se devuelve el nivel con mas nodos, desde el nivel 1
    //hasta nivelHasta. En caso de que el árbol sea vacio se debera retornar cero. Ante un
    //empate debera retornar el número de nivel mas pequeño

    private static int nivelMasNodos(BinaryTreeADT arbol,int nivelHasta){
        if(arbol.isEmpty()){
            return 0;
        }
        SimpleDictionaryADT dict = new DynamicSimpleDictionaryADT();
        recursiva(arbol, nivelHasta, 1, dict);

        SetADT keys = dict.getKeys();
        if (keys == null) return 0;

        int nivelGanador = -1;
        int maxNodos = -1;
        while(!keys.isEmpty()){
            int nivel = keys.choose();
            int nodosEnNivel = dict.get(nivel);
            if(nodosEnNivel > maxNodos || ((nodosEnNivel == maxNodos ) && nivel<nivelGanador )){
                maxNodos = nodosEnNivel;
                nivelGanador = nivel;
            }
            keys.remove(nivel);

        }

        return nivelGanador;
    }

    private static void recursiva(BinaryTreeADT arbol,int nivelHasta,int nivelActual,SimpleDictionaryADT dict){
        if(arbol.isEmpty() || nivelHasta < nivelActual){
            return;
        }
        SetADT keys = dict.getKeys();
        if(keys!=null && keys.exist(nivelActual)){
            dict.add(nivelActual,dict.get(nivelActual)+1);
        }
        else{
            dict.add(nivelActual,1);
        }
        recursiva(arbol.getRight(),nivelHasta,nivelActual+1,dict);
        recursiva(arbol.getLeft(),nivelHasta,nivelActual+1,dict);
    }

    public static void main(String[] args) {
        BinaryTreeADT arbol = new DynamicBinaryTreeADT();
        arbol.add(10);
        arbol.add(5);
        arbol.add(15);
        arbol.add(2);
        arbol.add(7);
        arbol.add(12);
        arbol.add(20);

        int nivelMasAlto = nivelMasNodos(arbol,5);

        System.out.println(nivelMasAlto);



    }




}
