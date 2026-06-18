package org.uade.service;

import org.uade.Exception.EmptyADTException;
import org.uade.Exception.GenericADTException;
import org.uade.entity.Micro;
import org.uade.entity.Ruta;
import org.uade.entity.Terminal;
import org.uade.entity.Viaje;
import org.uade.structure.definition.GraphADT;
import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.definition.PriorityQueueADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.SimpleDictionaryADT;
import org.uade.structure.implementation.dynamic.DynamicSimpleDictionaryADT;
import org.uade.util.PriorityQueueADTUtil;
import org.uade.util.SetADTUtil;

public class ReporteService {
    private final FlotaService flotaService;
    private final RutaService rutaService;
    private final ViajeService viajeService;

    public ReporteService(FlotaService flotaService, RutaService rutaService, ViajeService viajeService) {
        this.flotaService = flotaService;
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

    public String obtenerMicroMasAsignado() {
        SimpleDictionaryADT<String, Micro> micros = flotaService.getMicros();
        if (micros.isEmpty()) {
            throw new EmptyADTException("No hay micros registrados.");
        }

        SetADT<String> patentes = SetADTUtil.copy(micros.getKeys());
        Micro microMax = null;
        int maxAsignaciones = -1;

        while (!patentes.isEmpty()) {
            String patente = patentes.choose();
            patentes.remove(patente);
            
            Micro microActual = micros.get(patente);
            int cantidad = microActual.getCantViajes();
            
            if (maxAsignaciones == -1 || cantidad > maxAsignaciones) {
                maxAsignaciones = cantidad;
                microMax = microActual;
            }
        }

        if (microMax == null || maxAsignaciones == 0) {
            return "Ningún micro tiene viajes asignados aún.";
        }

        return "Micro más asignado: " + microMax.getPatente() + " con " + maxAsignaciones + " viaje(s).";
    }

    public String obtenerUtilizacionPromedioMicros() {
        PriorityQueueADT<Viaje> colaOriginal;
        try {
            colaOriginal = viajeService.getColaViajes();
        } catch (EmptyADTException e) {
            return "No hay viajes en la cola para calcular utilización.";
        }

        PriorityQueueADT<Viaje> colaCopia = PriorityQueueADTUtil.copy(colaOriginal);
        
        SimpleDictionaryADT<String, Integer> viajesPorMicro = new DynamicSimpleDictionaryADT<>();
        int totalViajesEnCola = 0;

        // Contar total de viajes en cola y cuántos pertenecen a cada patente
        while (!colaCopia.isEmpty()) {
            Viaje viaje = colaCopia.getElement();
            totalViajesEnCola++;
            
            if (viaje.getMicroAsignado() != null) {
                String patente = viaje.getMicroAsignado().getPatente();
                
                int count = 0;
                if (!viajesPorMicro.isEmpty()) {
                    SetADT<String> keys = viajesPorMicro.getKeys();
                    if (keys != null && keys.exist(patente)) {
                        count = viajesPorMicro.get(patente);
                    }
                }
                viajesPorMicro.add(patente, count + 1);
            }
            colaCopia.remove();
        }

        if (totalViajesEnCola == 0) {
            return "La cola de viajes está vacía.";
        }

        SimpleDictionaryADT<String, Micro> todosLosMicros;
        try {
            todosLosMicros = flotaService.getMicros();
        } catch (EmptyADTException e) {
            return "No hay micros registrados en la flota.";
        }

        return construirReporteUtilizacion(viajesPorMicro, totalViajesEnCola, todosLosMicros);
    }

    private String construirReporteUtilizacion(SimpleDictionaryADT<String, Integer> viajesPorMicro, int totalViajesEnCola, SimpleDictionaryADT<String, Micro> todosLosMicros) {
        String resultado = "";
        SetADT<String> patentes = SetADTUtil.copy(todosLosMicros.getKeys());
        
        while (!patentes.isEmpty()) {
            String patente = patentes.choose();
            patentes.remove(patente);
            
            int asignados = 0;
            if (!viajesPorMicro.isEmpty()) {
                SetADT<String> keys = viajesPorMicro.getKeys();
                if (keys != null && keys.exist(patente)) {
                    asignados = viajesPorMicro.get(patente);
                }
            }
            
            double porcentaje = (asignados / (double) totalViajesEnCola) * 100.0;
            // Formatear a 2 decimales manualmente
            porcentaje = Math.round(porcentaje * 100.0) / 100.0;
            
            resultado += "- Micro " + patente + ": " + porcentaje + "% de utilización en la cola\n";
        }

        return resultado;
    }
}