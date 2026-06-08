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
            Terminal t1 = new Terminal("BUE", "Buenos Aires");
            Terminal t2 = new Terminal("ROS", "Rosario");
            Terminal t3 = new Terminal("CBA", "Córdoba");
            Terminal t4 = new Terminal("MDZ", "Mendoza");
            Terminal t5 = new Terminal("SLA", "Salta");
            Terminal t6 = new Terminal("TUC", "Tucumán");
            Terminal t7 = new Terminal("BRB", "Bariloche");
            Terminal t8 = new Terminal("NQN", "Neuquén");
            Terminal t9 = new Terminal("PSS", "Posadas");
            Terminal t10 = new Terminal("RES", "Resistencia");

            Terminal[] terminales = {t1, t2, t3, t4, t5, t6, t7, t8, t9, t10};

            for (Terminal t : terminales) {
                terminalService.registrarTerminal(t);
                rutaService.registrarTerminal(t); // Lo agregamos al grafo también
            }

            // ==========================================
            // 2. PRECARGAR CONEXIONES (ARISTAS DEL GRAFO)
            // ==========================================
            rutaService.registrarConexion(t1, t2, 300);  // BUE -> ROS
            rutaService.registrarConexion(t2, t3, 400);  // ROS -> CBA
            rutaService.registrarConexion(t3, t5, 800);  // CBA -> SLA
            rutaService.registrarConexion(t5, t6, 310);  // SLA -> TUC
            rutaService.registrarConexion(t1, t4, 1050); // BUE -> MDZ
            rutaService.registrarConexion(t1, t7, 1580); // BUE -> BRB
            rutaService.registrarConexion(t7, t8, 430);  // BRB -> NQN
            rutaService.registrarConexion(t1, t9, 1000); // BUE -> PSS
            rutaService.registrarConexion(t9, t10, 350); // PSS -> RES

            // ==========================================
            // 3. GENERAR Y CREAR RUTAS USANDO TU ALGORITMO DFS
            // ==========================================
            // Aprovechamos tu DFS para que arme las colas de paradas automáticamente
            Ruta ruta1 = rutaService.rutasPosibles(t1, t3, 2).get(0); // BUE -> ROS -> CBA
            Ruta ruta2 = rutaService.rutasPosibles(t1, t6, 3).get(0); // BUE -> ROS -> CBA -> SLA -> TUC
            Ruta ruta3 = rutaService.rutasPosibles(t1, t4, 1).get(0); // BUE -> MDZ (Directo)
            Ruta ruta4 = rutaService.rutasPosibles(t1, t8, 2).get(0); // BUE -> BRB -> NQN
            Ruta ruta5 = rutaService.rutasPosibles(t1, t10, 2).get(0); // BUE -> PSS -> RES

            Ruta[] rutasActivas = {ruta1, ruta2, ruta3, ruta4, ruta5};

            for (Ruta r : rutasActivas) {
                rutaService.crearRuta(r);
            }

            // ==========================================
            // 4. PRECARGAR 15 MICROS
            // ==========================================
            for (int i = 1; i <= 15; i++) {
                String patente = String.format("MIC%03d", i); // Genera MIC001, MIC002, etc.
                Tipo tipo = (i % 3 == 0) ? Tipo.EJECUTIVO : (i % 2 == 0) ? Tipo.CAMA : Tipo.SEMICAMA;
                flotaService.registrarMicro(patente, tipo);
            }

            // ==========================================
            // 5. PRECARGAR 20 VIAJES EN LA COLA DE PRIORIDAD
            // ==========================================
            LocalDate fechaBase = LocalDate.now();

            for (int i = 1; i <= 20; i++) {
                // Asignamos las rutas de forma rotativa para que haya variedad
                Ruta rutaAsignada = rutasActivas[i % 5];

                // Variamos las fechas para que sean en los próximos 10 días
                LocalDate fechaViaje = fechaBase.plusDays(i % 10);

                // Variamos las prioridades de 1 a 10 (Donde 10 asumo es la más alta en tu sistema)
                int prioridad = (i % 10) + 1;

                viajeService.programarViaje(rutaAsignada, fechaViaje, prioridad);
            }

            System.out.println("✅ Precarga finalizada con éxito. ¡Entorno Demo listo!");
            System.out.println("--------------------------------------------------\n");

        } catch (Exception e) {
            System.out.println("⚠️ Hubo un error durante la precarga de datos: " + e.getMessage());
            e.printStackTrace(); // Útil para debugear si algo falla en el arranque
        }
    }
}