package org.uade.util;

import org.uade.structure.definition.GraphADT;
import org.uade.structure.definition.SetADT;

public class GraphADTUtil {

    /**
     * Imprime el grafo completo por consola en formato de Lista de Adyacencia.
     */
    public static void print(GraphADT graph) {
        if (graph == null || graph.isEmpty()) {
            System.out.println("El grafo está vacío.");
            return;
        }

        // Recuperamos los vértices. Como tu método getVertxs() devuelve un nuevo DynamicSetADT,
        // podemos usar choose() y remove() para iterarlo sin miedo a romper el grafo original.
        SetADT vertices = graph.getVertxs();

        System.out.println("--- Contenido del Grafo ---");

        while (!vertices.isEmpty()) {
            int origen = vertices.choose();
            vertices.remove(origen);

            System.out.print("Vértice " + origen + ": ");

            // Pedimos los vértices de nuevo para iterar como posibles destinos
            SetADT posiblesDestinos = graph.getVertxs();
            boolean tieneVecinos = false;

            while (!posiblesDestinos.isEmpty()) {
                int destino = posiblesDestinos.choose();
                posiblesDestinos.remove(destino);

                // Si hay un cable real desde 'origen' hacia 'destino', lo imprimimos
                if (graph.existsEdge(origen, destino)) {
                    int peso = graph.edgeWeight(origen, destino);
                    System.out.print("-> [" + destino + "] (peso: " + peso + ")  ");
                    tieneVecinos = true;
                }
            }

            // Si revisamos todos los destinos y no tenía conexiones salientes
            if (!tieneVecinos) {
                System.out.print("Sin conexiones salientes.");
            }

            System.out.println(); // Salto de línea para el próximo vértice
        }

        System.out.println("---------------------------");
    }
}