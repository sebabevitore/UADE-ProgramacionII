package org.uade.service;

import org.uade.Exception.GenericADTException;
import org.uade.entity.Ruta;
import org.uade.entity.Terminal;
import org.uade.structure.definition.GraphADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.dynamic.DynamicGraphADT;
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
        grafoTerminales.addEdge(o, d, km);
    }

    // TODO: implementar algoritmo DFS para determinar rutas posibles con maximo de paradas
    public SetADT<Ruta> determinarRutasPosibles(Terminal o, Terminal d, int maxParadas) {
        // TODO: implementar busqueda DFS sobre el grafo
        return new DynamicSetADT<>();
    }

    // TODO: implementar identificacion de terminales desconectadas del grafo
    public SetADT<Terminal> identificarTerminalesDesconectadas() {
        // TODO: implementar logica para encontrar terminales sin conexiones
        return new DynamicSetADT<>();
    }

    public GraphADT<Terminal, Integer> getGrafoTerminales() {
        return this.grafoTerminales;
    }
}
