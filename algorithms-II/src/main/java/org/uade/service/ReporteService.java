package org.uade.service;

import org.uade.Exception.EmptyADTException;
import org.uade.Exception.GenericADTException;
import org.uade.entity.Ruta;
import org.uade.entity.Terminal;
import org.uade.entity.Viaje;
import org.uade.structure.definition.GraphADT;
import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.SimpleDictionaryADT;
import org.uade.structure.implementation.dynamic.DynamicSimpleDictionaryADT;
import org.uade.util.SetADTUtil;

public class ReporteService {
    private final RutaService rutaService;
    private final ViajeService viajeService;

    public ReporteService(RutaService rutaService, ViajeService viajeService) {
        this.rutaService = rutaService;
        this.viajeService = viajeService;
    }

    /**
     * Terminales con más conexiones directas (salientes en el Grafo)
     */
    public Terminal obtenerTerminalMasConectada() {
        GraphADT<Terminal, Integer> grafo = rutaService.getGrafoTerminales();
        if (grafo.isEmpty()) {
            throw new EmptyADTException("No hay terminales registradas.");
        }

        SetADT<Terminal> vertices = grafo.getVertxs();

        Terminal maxTerminal = null;
        int maxConexiones = -1;

        while (!vertices.isEmpty()) {
            Terminal actual = vertices.choose();
            vertices.remove(actual);

            LinkedListADT<Terminal> vecinos = rutaService.obtenerVecinos(actual);

            int conexionesActuales = vecinos.size();

            if (conexionesActuales > maxConexiones) {
                maxConexiones = conexionesActuales;
                maxTerminal = actual;
            }
        }

        return maxTerminal;
    }

    /**
     * Reporte de Terminales con mayor número de salidas.
     */
    public String obtenerTerminalMasSalidas() {
        GraphADT<Terminal, Integer> grafo = rutaService.getGrafoTerminales();
        if (grafo.isEmpty()) {
            throw new EmptyADTException("El grafo de terminales está vacío.");
        }

        SetADT<Terminal> vertices = grafo.getVertxs();
        Terminal maxTerminal = null;
        int maxSalidas = -1;

        while (!vertices.isEmpty()) {
            Terminal actual = vertices.choose();
            vertices.remove(actual);

            LinkedListADT<Terminal> vecinos = rutaService.obtenerVecinos(actual);
            int salidas = vecinos.size();

            if (maxSalidas == -1 || salidas > maxSalidas) {
                maxSalidas = salidas;
                maxTerminal = actual;
            }
        }

        return maxTerminal.getDescripcion() + " (" + maxTerminal.getCodigo() + ") con " + maxSalidas + " rutas de salida.";
    }

    public String obtenerTerminalMasLlegadas() {
        GraphADT<Terminal, Integer> grafo = rutaService.getGrafoTerminales();
        if (grafo.isEmpty()) {
            throw new EmptyADTException("El grafo de terminales está vacío.");
        }

        SetADT<Terminal> verticesDestino = grafo.getVertxs();
        Terminal maxTerminal = null;
        int maxLlegadas = -1;

        while (!verticesDestino.isEmpty()) {
            Terminal destinoActual = verticesDestino.choose();
            verticesDestino.remove(destinoActual);

            int llegadas = 0;
            // se busca en las terminales, para ver cuantas apuntan a destinoActual
            SetADT<Terminal> posiblesOrigenes = grafo.getVertxs();

            while (!posiblesOrigenes.isEmpty()) {
                Terminal origen = posiblesOrigenes.choose();
                posiblesOrigenes.remove(origen);

                // Si existe arista desde origen hacia destinoActual, se suma la llegada.
                if (!destinoActual.equals(origen) && grafo.existsEdge(origen, destinoActual)) {
                    llegadas++;
                }
            }

            if (maxLlegadas == -1 || llegadas > maxLlegadas) {
                maxLlegadas = llegadas;
                maxTerminal = destinoActual;
            }
        }

        return maxTerminal.getDescripcion() + " (" + maxTerminal.getCodigo() + ") con " + maxLlegadas + " rutas de llegada.";
    }

    private SimpleDictionaryADT<String, Integer> calcularFrecuenciaUsoRutas(LinkedListADT<Viaje> historial) {

        // Diccionario para contar cuantas veces se uso cada ruta del historial
        // key: "ORIGEN-DESTINO"
        // value: cantidad de usos

        SimpleDictionaryADT<String, Integer> usoRutas = new DynamicSimpleDictionaryADT<>();


        for (int i = 0; i < historial.size(); i++) {
            Ruta r = historial.get(i).getRuta();
            String claveRuta = r.getOrigen().getCodigo() + "-" + r.getDestino().getCodigo();

            int cant = 0;
            if (!usoRutas.isEmpty()) {
                SetADT<String> clavesExistentes = usoRutas.getKeys();
                if (clavesExistentes != null && clavesExistentes.exist(claveRuta)) {
                    cant = usoRutas.get(claveRuta);
                }
            }

            usoRutas.add(claveRuta, cant + 1);
        }
        return usoRutas;
    }

    public String obtenerRutaMasUtilizada() {
        LinkedListADT<Viaje> historial = viajeService.getHistorialDespachados();
        if (historial.isEmpty()) {
            throw new EmptyADTException("No hay viajes despachados.");
        }

        SimpleDictionaryADT<String, Integer> usoRutas = calcularFrecuenciaUsoRutas(historial);

        String rutaMasUsada = "Ninguna";
        int maxUsos = -1;

        SetADT<String> keysUso = SetADTUtil.copy(usoRutas.getKeys());
        while (!keysUso.isEmpty()) {
            String key = keysUso.choose();
            keysUso.remove(key);
            int cant = usoRutas.get(key);
            if (cant > maxUsos) {
                maxUsos = cant;
                rutaMasUsada = key;
            }
        }

        return "Ruta mas utilizada: " + rutaMasUsada + " (" + maxUsos + " veces)";
    }

    public String obtenerRutaMenosUtilizada() {
        SetADT<Ruta> rutasExistentes = rutaService.getRutasExistentes();
        if (rutasExistentes.isEmpty()) {
            throw new GenericADTException("No hay rutas registradas en el sistema.");
        }

        LinkedListADT<Viaje> historial = viajeService.getHistorialDespachados();
        if (historial.isEmpty()) {
            throw new EmptyADTException("No se han realizado viajes. No es posible determinar la ruta menos utilizada.");
        }

        SimpleDictionaryADT<String, Integer> usoRutas = calcularFrecuenciaUsoRutas(historial);

        SetADT<Ruta> copiaRutas = SetADTUtil.copy(rutasExistentes);
        Ruta rutaMenosUsada = null;
        int minUsos = -1;

        while (!copiaRutas.isEmpty()) {
            Ruta r = copiaRutas.choose();
            copiaRutas.remove(r);
            String claveRuta = r.getOrigen().getCodigo() + "-" + r.getDestino().getCodigo();

            int cant = 0;
            SetADT<String> keysUso = usoRutas.getKeys();
            if (keysUso != null && keysUso.exist(claveRuta)) {
                cant = usoRutas.get(claveRuta);
            }

            if (minUsos == -1 || cant < minUsos) {
                minUsos = cant;
                rutaMenosUsada = r;
            }
        }

        String menosUsadaStr = rutaMenosUsada.getOrigen().getCodigo() + "-" + rutaMenosUsada.getDestino().getCodigo();

        return "Ruta menos utilizada: " + menosUsadaStr + " (" + minUsos + " veces)";
    }


}