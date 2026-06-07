package org.uade.service;

import org.uade.Exception.GenericADTException;
import org.uade.entity.Ruta;
import org.uade.entity.Terminal;
import org.uade.structure.definition.GraphADT;
import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.definition.QueueADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.dynamic.DynamicGraphADT;
import org.uade.structure.implementation.dynamic.DynamicLinkedListADT;
import org.uade.structure.implementation.dynamic.DynamicQueueADT;
import org.uade.structure.implementation.dynamic.DynamicSetADT;

public class RutaService {
    private GraphADT<Terminal, Integer> grafoTerminales;
    private SetADT<Ruta> rutasExistentes;

    public RutaService() {
        this.grafoTerminales = new DynamicGraphADT<>();
        this.rutasExistentes = new DynamicSetADT<>();
    }

    public void registrarTerminal(Terminal t) {
        grafoTerminales.addVertx(t);
    }

    public void registrarConexion(Terminal o, Terminal d, int km) {
        if (grafoTerminales.existsEdge(o, d)) {
            throw new GenericADTException("Ya existe una conexión registrada entre estas terminales.");
        }
        grafoTerminales.addEdge(o, d, km);
    }

    public void eliminarConexion(Terminal origen, Terminal destino) {
        if (!grafoTerminales.existsEdge(origen, destino)) {
            throw new GenericADTException("No existe una conexión registrada entre estas terminales.");
        }
        grafoTerminales.removeEdge(origen, destino);
    }

    public SetADT<Ruta> rutasPosibles(Terminal origen, Terminal destino, int maxParadas) {
        SetADT<Ruta> rutasEncontradas = new DynamicSetADT<>();
        LinkedListADT<Terminal> caminoActual = new DynamicLinkedListADT<>();
        SetADT<Terminal> visitados = new DynamicSetADT<>();

        caminoActual.add(origen);
        busquedaConexiones(origen,destino,maxParadas,0,caminoActual,visitados,rutasEncontradas);

        return new DynamicSetADT<>();
    }

    private void busquedaConexiones(Terminal actual, Terminal destino, int maxParadas, int paradasActuales,
                                    LinkedListADT<Terminal> caminoActual, SetADT<Terminal> visitados,
                                    SetADT<Ruta> resultados) {
        // caso 1: llegue al destino
        if (actual.equals(destino)) {
            Ruta ruta = crearRutaDesdeCamino(caminoActual);
            resultados.add(ruta);
            return;
        }
        // caso 2: alcanzo el maximo de paradas intermedias
        if (paradasActuales > maxParadas) {
            return;
        }

        visitados.add(actual);

        LinkedListADT<Terminal> vecinos = obtenerVecinos(actual);

        for (int i = 0; i < vecinos.size(); i++) {
            Terminal vecino = vecinos.get(i);

            if (!visitados.exist(vecino)) {
                caminoActual.add(vecino);

                // llamada recursiva aumentando el contador de paradas
                busquedaConexiones(vecino, destino, maxParadas, paradasActuales + 1, caminoActual, visitados, resultados);

                // BACKTRACKING: Limpiamos el camino para la siguiente iteración del ciclo for
                caminoActual.remove(caminoActual.size() - 1);
            }
        }
        // BACKTRACKING 2: Desmarcamos el nodo actual al salir de este camino
        // para que otras rutas alternativas pasen por ese nodo
        visitados.remove(actual);

    }

    /**
     * Descripcion: Convierte la lista temporal de caminoActual en un objeto Ruta
     */
    private Ruta crearRutaDesdeCamino(LinkedListADT<Terminal> caminoActual) {
        Terminal origen = caminoActual.get(0);
        Terminal destino = caminoActual.get(caminoActual.size() - 1);

        QueueADT<Terminal> paradasIntermedias = new DynamicQueueADT<>();

        // esta iteracion saltea el primero y ultimo, para tener solo intermedias
        for (int i = 1; i < caminoActual.size() - 1; i++) {
            paradasIntermedias.add(caminoActual.get(i));
        }

        return new Ruta(origen, destino, paradasIntermedias);
    }

    /**
     * Descripcion: devuelve una lista de vecinos del vertice,
     * es decir, los que tienen una arista directa
     */
    private LinkedListADT<Terminal> obtenerVecinos(Terminal actual) {
        LinkedListADT<Terminal> vecinos = new DynamicLinkedListADT<>();

        SetADT<Terminal> vertices = grafoTerminales.getVertxs();

        while (!vertices.isEmpty()) {
            Terminal posibleVecino = vertices.choose();
            vertices.remove(posibleVecino);

            if (!actual.equals(posibleVecino)) {
                if (grafoTerminales.existsEdge(actual, posibleVecino)) {
                    vecinos.add(posibleVecino);
                }
            }
        }

        return vecinos;
    }

    public SetADT<Terminal> identificarTerminalesDesconectadas() {
        // TODO: implementar logica para encontrar terminales sin conexiones
        return new DynamicSetADT<>();
    }

    public GraphADT<Terminal, Integer> getGrafoTerminales() {
        return this.grafoTerminales;
    }
}
