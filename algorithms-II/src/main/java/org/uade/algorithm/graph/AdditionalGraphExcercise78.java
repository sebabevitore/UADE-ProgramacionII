package org.uade.algorithm.graph;

import org.uade.structure.definition.GraphADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.dynamic.DynamicSetADT;

public class AdditionalGraphExcercise78 {
    //78. Implementa un método que determine si un grafo dirigido contiene aristas
    //redundantes, es decir, aquellas cuya eliminación no afecta la conectividad entre los
    //nodos

    private static boolean aristasRedundantes(GraphADT grafo){
        if(grafo.isEmpty()){
            return false;
        }

        SetADT vertices = grafo.getVertxs();

        while(!vertices.isEmpty()){
            int origen = vertices.choose();
            vertices.remove(origen);

            SetADT posiblesVecinos = grafo.getVertxs();

            while(!posiblesVecinos.isEmpty()){
                int destino = posiblesVecinos.choose();
                posiblesVecinos.remove(destino);

                if(grafo.existsEdge(origen, destino)){
                    int peso = grafo.edgeWeight(origen, destino);
                    grafo.removeEdge(origen, destino);
                    SetADT visitados = new DynamicSetADT();
                    boolean esRedundante = existeCaminoAlternativo(grafo, origen, destino, visitados);
                    grafo.addEdge(origen, destino, peso);
                    if(esRedundante){
                        return true;
                    }
                }
            }

        }
        return false;
    }

    private static boolean existeCaminoAlternativo(GraphADT grafo, int origen, int destino, SetADT visitados){
        if (origen == destino){
            return true;
        }
        if (visitados.exist(origen)){
            return false;
        }
        visitados.add(origen);

        SetADT vertices = grafo.getVertxs();

        while(!vertices.isEmpty()){
            int vecino =  vertices.choose();
            vertices.remove(vecino);

            // ¡Solo salto si hay un cable real desde mi origen hacia el vecino!
            if (grafo.existsEdge(origen, vecino)) {
                if (existeCaminoAlternativo(grafo, vecino, destino, visitados)) {
                    return true;
                }
            }
        }


        return false;
    }





}
