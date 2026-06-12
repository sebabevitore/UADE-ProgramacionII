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
        System.out.println("🔄 Iniciando precarga de datos del sistema...");
        try {
            // ==========================================
            // 1. PRECARGAR 10 TERMINALES
            // ==========================================
            Terminal[] terminales = {
                    new Terminal("BUE", "Buenos Aires"), new Terminal("ROS", "Rosario"),
                    new Terminal("CBA", "Córdoba"), new Terminal("MDZ", "Mendoza"),
                    new Terminal("SLA", "Salta"), new Terminal("TUC", "Tucumán"),
                    new Terminal("BRB", "Bariloche"), new Terminal("NQN", "Neuquén"),
                    new Terminal("PSS", "Posadas"), new Terminal("RES", "Resistencia")
            };

            for (Terminal t : terminales) {
                terminalService.registrarTerminal(t);
                rutaService.registrarTerminal(t);
            }

            // ==========================================
            // 2. PRECARGAR CONEXIONES (ARISTAS)
            // ==========================================
            rutaService.registrarConexion(terminales[0], terminales[1], 300);  // BUE -> ROS
            rutaService.registrarConexion(terminales[1], terminales[2], 400);  // ROS -> CBA
            rutaService.registrarConexion(terminales[2], terminales[4], 800);  // CBA -> SLA
            rutaService.registrarConexion(terminales[4], terminales[5], 310);  // SLA -> TUC
            rutaService.registrarConexion(terminales[0], terminales[3], 1050); // BUE -> MDZ
            rutaService.registrarConexion(terminales[0], terminales[6], 1580); // BUE -> BRB
            rutaService.registrarConexion(terminales[6], terminales[7], 430);  // BRB -> NQN
            rutaService.registrarConexion(terminales[0], terminales[8], 1000); // BUE -> PSS
            rutaService.registrarConexion(terminales[8], terminales[9], 350);  // PSS -> RES

            // ==========================================
            // 3. CREAR RUTAS DEFINITIVAS
            // ==========================================
            Ruta ruta1 = rutaService.rutasPosibles(terminales[0], terminales[2], 2).get(0);
            Ruta ruta2 = rutaService.rutasPosibles(terminales[0], terminales[5], 3).get(0);
            Ruta ruta3 = rutaService.rutasPosibles(terminales[0], terminales[3], 1).get(0);
            Ruta ruta4 = rutaService.rutasPosibles(terminales[0], terminales[7], 2).get(0);
            Ruta ruta5 = rutaService.rutasPosibles(terminales[0], terminales[9], 2).get(0);

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
            // 5. PRECARGAR 20 VIAJES (En Cola de Prioridad)
            // ==========================================
            LocalDate fechaBase = LocalDate.now();
            for (int i = 1; i <= 20; i++) {
                Ruta rutaAsignada = rutasActivas[i % 5];
                LocalDate fechaViaje = fechaBase.plusDays(i % 10);
                int prioridad = (i % 10) + 1;
                viajeService.programarViaje(rutaAsignada, fechaViaje, prioridad);
            }

            // ==========================================
            // 6. SIMULAR DESPACHOS (Para generar historial)
            // ==========================================
            System.out.println("🚐 Despachando viajes para generar estadísticas...");
            for (int i = 1; i <= 5; i++) {
                // Agarramos el de mayor prioridad
                Viaje viajeADespachar = viajeService.getColaViajes().getElement();

                // Le asignamos un micro disponible
                String patente = String.format("MIC%03d", i);
                Micro micro = flotaService.getMicroDisponible(patente, viajeADespachar.getFecha());
                viajeService.asignarMicroAViaje(viajeADespachar, micro);

                // Lo mandamos a la ruta (esto lo guarda en historialDespachados)
                viajeService.despacharSiguienteViaje();
            }

            System.out.println("✅ Precarga finalizada. ¡El historial tiene datos para reportes!");
            System.out.println("----------------------------------------------------------------------\n");

        } catch (Exception e) {
            System.out.println("⚠️ Hubo un error durante la precarga: " + e.getMessage());
        }
    }
}