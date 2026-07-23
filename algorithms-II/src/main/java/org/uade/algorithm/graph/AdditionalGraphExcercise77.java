package org.uade.algorithm.graph;

import org.uade.structure.definition.GraphADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.dynamic.DynamicSetADT;

public class AdditionalGraphExcercise77 {
    //77. Dado un grafo dirigido, implementa un método que identifique las aristas críticas, es
    //decir, aquellas cuya eliminación desconecta componentes del grafo o elimina
    //caminos dirigidos entre ciertos nodos.

    private static void identificarAristasCriticas(GraphADT graph){
        if(graph.isEmpty()){
            return;
        }
        SetADT verticesOrigen = graph.getVertxs();

        while(!verticesOrigen.isEmpty()){
            int origen = verticesOrigen.choose();
            verticesOrigen.remove(origen);

            SetADT verticesDestino = graph.getVertxs();
            while(!verticesDestino.isEmpty()){
                int destino = verticesDestino.choose();
                verticesDestino.remove(destino);

                if(graph.existsEdge(origen, destino)){
                    int pesoOriginal = graph.edgeWeight(origen, destino);

                    // 1. CORTAR: Eliminamos temporalmente la conexión
                    graph.removeEdge(origen, destino);

                    // 2. EXPLORAR: Mandamos al explorador a buscar otra ruta
                    SetADT visitados = new DynamicSetADT();
                    boolean hayRutaAlternativa = existeCamino(graph, origen, destino, visitados);

                    // 3. EVALUAR: Si no hay ruta alternativa, ¡era crítica!
                    if (!hayRutaAlternativa) {
                        System.out.println("Arista CRÍTICA encontrada: " + origen + " -> " + destino);
                    }

                    // 4. RESTAURAR: Volvemos a conectar el cable para no romper el mapa
                    graph.addEdge(origen, destino, pesoOriginal);
                }
            }

        }
    }

    private static boolean existeCamino(GraphADT graph, int actual,int destino, SetADT visitados){
        // Caso Base 1: ¡Llegamos al destino por otra ruta!
        if (actual==destino) return true;

        // Caso Base 2: Ciclo o callejón sin salida
        if (visitados.exist(actual)) {
            return false;
        }

        visitados.add(actual);

        SetADT posiblesVecinos = graph.getVertxs();
        while (!posiblesVecinos.isEmpty()){
            int vecino = posiblesVecinos.choose();
            posiblesVecinos.remove(vecino);

            if (graph.existsEdge(actual, vecino)) {
                // Si ese vecino logra encontrar el camino, avisamos hacia arriba que hubo éxito
                if (existeCamino(graph, vecino, destino, visitados)) {
                    return true;
                }
            }
        }

        // Si revisamos todos los vecinos y ninguno llegó al destino...
        return false;

    }


    public static void main(String[] args) {

    }


}
