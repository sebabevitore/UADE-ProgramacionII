package org.uade.util;

import org.uade.entity.*;
import org.uade.service.*;

import java.time.LocalDate;

public class PrecargaDatos {
    private final TerminalService terminalService;
    private final FlotaService flotaService;
    private final RutaService rutaService;
    private final ViajeService viajeService;

    public PrecargaDatos(TerminalService terminalService, FlotaService flotaService,
                         RutaService rutaService, ViajeService viajeService) {
        this.terminalService = terminalService;
        this.flotaService = flotaService;
        this.rutaService = rutaService;
        this.viajeService = viajeService;
    }

    public void cargar() {
        System.out.println("🔄 Iniciando precarga estratégica de datos...");
        try {
            // ==========================================
            // 1. PRECARGAR 10 TERMINALES (1 AISLADA)
            // ==========================================
            Terminal tBUE = new Terminal("BUE", "Buenos Aires");
            Terminal tROS = new Terminal("ROS", "Rosario");
            Terminal tCBA = new Terminal("CBA", "Córdoba");
            Terminal tMDZ = new Terminal("MDZ", "Mendoza");
            Terminal tSLA = new Terminal("SLA", "Salta");
            Terminal tTUC = new Terminal("TUC", "Tucumán");
            Terminal tBRC = new Terminal("BRC", "Bariloche");
            Terminal tNQN = new Terminal("NQN", "Neuquén");
            Terminal tPOS = new Terminal("POS", "Posadas");
            Terminal tRGL = new Terminal("RGL", "Río Gallegos"); // ¡ESTA QUEDARÁ AISLADA!

            Terminal[] terminales = {tBUE, tROS, tCBA, tMDZ, tSLA, tTUC, tBRC, tNQN, tPOS, tRGL};
            for (Terminal t : terminales) {
                terminalService.registrarTerminal(t);
                rutaService.registrarTerminal(t);
            }

            // ==========================================
            // 2. CONEXIONES (BUE como concentrador)
            // ==========================================
            rutaService.registrarConexion(tBUE, tROS, 300);
            rutaService.registrarConexion(tBUE, tCBA, 700); // Conexión directa
            rutaService.registrarConexion(tBUE, tMDZ, 1050);
            rutaService.registrarConexion(tBUE, tBRC, 1580);
            rutaService.registrarConexion(tBUE, tPOS, 1000);

            rutaService.registrarConexion(tROS, tCBA, 400);
            rutaService.registrarConexion(tCBA, tSLA, 800);
            rutaService.registrarConexion(tSLA, tTUC, 310);
            rutaService.registrarConexion(tBRC, tNQN, 430);
            rutaService.registrarConexion(tROS, tPOS, 850);
            // Nota: No le agregamos NINGUNA conexión a tRGL (Río Gallegos)

            // ==========================================
            // 3. CREAR RUTAS DEFINITIVAS
            // ==========================================
            Ruta ruta1 = rutaService.rutasPosibles(tBUE, tCBA, 1).get(0); // Ruta más usada
            Ruta ruta2 = rutaService.rutasPosibles(tBUE, tTUC, 3).get(0);
            Ruta ruta3 = rutaService.rutasPosibles(tBUE, tMDZ, 0).get(0);
            Ruta ruta4 = rutaService.rutasPosibles(tBRC, tNQN, 0).get(0); // Ruta menos usada (Fantasma)
            Ruta ruta5 = rutaService.rutasPosibles(tCBA, tSLA, 0).get(0);

            Ruta[] rutasActivas = {ruta1, ruta2, ruta3, ruta4, ruta5};
            for (Ruta r : rutasActivas) rutaService.crearRuta(r);

            // ==========================================
            // 4. PRECARGAR 15 MICROS
            // ==========================================
            for (int i = 1; i <= 15; i++) {
                String patente = String.format("MIC%03d", i);
                Tipo tipo = (i % 3 == 0) ? Tipo.EJECUTIVO : (i % 2 == 0) ? Tipo.CAMA : Tipo.SEMICAMA;
                flotaService.registrarMicro(patente, tipo);
            }

            // ==========================================
            // 5. SIMULAR HISTORIAL (DESPACHOS)
            // ==========================================
            System.out.println("🚐 Generando historial estadístico para reportes...");
            LocalDate hoy = LocalDate.now();
            Micro microEstrella = flotaService.getMicros().get("MIC001");
            Micro microSecundario = flotaService.getMicros().get("MIC002");

            // Agregamos a la cola viajes pasados con MUCHA prioridad (100) para que salgan primeros
            viajeService.programarViaje(ruta1, hoy.minusDays(10), 100); // ID 0
            viajeService.programarViaje(ruta1, hoy.minusDays(9), 99);   // ID 1
            viajeService.programarViaje(ruta1, hoy.minusDays(8), 98);   // ID 2
            viajeService.programarViaje(ruta2, hoy.minusDays(7), 97);   // ID 3
            viajeService.programarViaje(ruta3, hoy.minusDays(6), 96);   // ID 4
            viajeService.programarViaje(ruta5, hoy.minusDays(5), 95);   // ID 5

            // Despachamos esos 6 viajes para inflar el historial
            for(int i = 0; i < 6; i++) {
                Viaje v = viajeService.getColaViajes().getElement();
                // El MIC001 se lleva 4 viajes (Gana el reporte de más asignado)
                Micro mAsignar = (i < 4) ? microEstrella : microSecundario;
                viajeService.asignarMicroAViaje(v, mAsignar);
                viajeService.despacharSiguienteViaje();
            }

            // ==========================================
            // 6. PROGRAMAR VIAJES PENDIENTES
            // ==========================================
            // Estos quedan en la cola para la Demo
            for (int i = 1; i <= 15; i++) {
                Ruta ruta = rutasActivas[i % 5];
                viajeService.programarViaje(ruta, hoy.plusDays(i), i); // Prioridad baja/normal
            }

            // Asignamos micros a un par de viajes pendientes para que el
            // reporte de "Utilización Promedio" tenga % reales y no de 0%
            viajeService.asignarMicroAViaje(viajeService.findViajeById(6), flotaService.getMicros().get("MIC003"));
            viajeService.asignarMicroAViaje(viajeService.findViajeById(7), flotaService.getMicros().get("MIC004"));
            viajeService.asignarMicroAViaje(viajeService.findViajeById(8), flotaService.getMicros().get("MIC003"));
            viajeService.asignarMicroAViaje(viajeService.findViajeById(9), flotaService.getMicros().get("MIC005"));

            System.out.println("✅ Precarga finalizada. ¡Los reportes están listos para la Demo!");
            System.out.println("----------------------------------------------------------------------\n");

        } catch (Exception e) {
            System.out.println("⚠️ Hubo un error durante la precarga: " + e.getMessage());
        }
    }
}