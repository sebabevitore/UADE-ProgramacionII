package org.uade.algorithm.graph;

import org.uade.structure.definition.GraphADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.SimpleDictionaryADT;
import org.uade.structure.implementation.dynamic.DynamicGraphADT;
import org.uade.structure.implementation.dynamic.DynamicSetADT;
import org.uade.structure.implementation.dynamic.DynamicSimpleDictionaryADT;

public class AdditionalGraphExcercise75 {

    //  75. Dado un diccionario simple se lo quiere convertir en un grafo considerando:
    //      ○ Las claves representan a los vertices
    //      ○ Los valores son con que vertices se conectan
    //      ○ Todas las aristas tienen un valor de 1

    private static GraphADT convertir(SimpleDictionaryADT dict){
        if(dict.isEmpty()){
            return new DynamicGraphADT();
        }

        GraphADT grafo = new DynamicGraphADT();

        cargarVertices(dict, grafo);
        cargarAristas(dict, grafo);

        return grafo;
    }

    private static void cargarVertices (SimpleDictionaryADT dict, GraphADT grafo){
        SetADT keys = dict.getKeys();
        while(!keys.isEmpty()){
            int key = keys.choose();
            keys.remove(key);
            grafo.addVertx(key);
            grafo.addVertx(dict.get(key));
        }
    }

    private static void cargarAristas (SimpleDictionaryADT dict, GraphADT grafo){
        SetADT keys = dict.getKeys();
        while(!keys.isEmpty()){
            int key = keys.choose();
            keys.remove(key);
            grafo.addEdge(key,dict.get(key),1);
        }
    }


    public static void main(String[] args) {
        SimpleDictionaryADT simpleDict = new DynamicSimpleDictionaryADT();
        simpleDict.add(3,1);
        simpleDict.add(2,3);
        simpleDict.add(4,3);
        simpleDict.add(5,1);

        GraphADT grafo = convertir(simpleDict);
        System.out.println("(RE TRUE): - " + grafo.existsEdge(3,1));
        System.out.println("(RE TRUE): - " + grafo.existsEdge(2,3));
        System.out.println("(RE FALSE): - " + grafo.existsEdge(1,5));




    }

}
