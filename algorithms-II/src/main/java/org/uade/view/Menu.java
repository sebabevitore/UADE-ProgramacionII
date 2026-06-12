package org.uade.view;

import org.uade.service.*;
import org.uade.util.ConsoleInput;
import org.uade.util.PrecargaDatos;


public class Menu {
    private final FlotaService flotaService;
    private final ViajeService viajeService;
    private final RutaService rutaService;
    private final TerminalService terminalService;
    private final FlotaModulo flotaModulo;
    private final ViajesModulo viajesModulo;
    private final RutaModulo rutaModulo;
    private final TerminalModulo terminalModulo;
    private final ReporteService reporteService;
    private final ReportesModulo reportesModulo;


    public Menu() {
        this.terminalService = new TerminalService();
        this.flotaService = new FlotaService();
        this.viajeService = new ViajeService();
        this.rutaService = new RutaService();
        this.reporteService = new ReporteService(this.rutaService, this.viajeService);

        this.flotaModulo = new FlotaModulo(this.flotaService);
        this.viajesModulo = new ViajesModulo(this.flotaService, this.viajeService, this.rutaService);
        this.rutaModulo = new RutaModulo(this.rutaService,this.terminalService);
        this.terminalModulo = new TerminalModulo(this.terminalService,this.rutaService);
        this.reportesModulo = new ReportesModulo(this.reporteService);


        PrecargaDatos demo = new PrecargaDatos(terminalService, flotaService, rutaService, viajeService);
        demo.cargar();
    }

    public void launch() {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE TRANSPORTE ===");
            System.out.println("1. Flota");
            System.out.println("2. Viajes");
            System.out.println("3. Rutas");
            System.out.println("4. Terminales");
            System.out.println("5. Reportes y Estadísticas");
            System.out.println("0. Salir");

            opcion = ConsoleInput.readOption("Seleccione una opción:");

            switch (opcion) {
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                case 1:
                    flotaModulo.ejecutarMenuFlota();
                    break;
                case 2:
                    viajesModulo.ejecutarMenuViajes();
                    break;
                case 3:
                    rutaModulo.ejecutarMenuRutas();
                    break;
                case 4:
                    terminalModulo.ejecutarMenuTerminales();
                    break;
                case 5:
                    reportesModulo.ejecutarMenuReportes();
                    break;
                default:
                    System.out.println("Opción no válida.");
                    ConsoleInput.waitEnter();
            }
        } while (opcion != 0);
    }
}
