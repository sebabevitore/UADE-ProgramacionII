package org.uade.view;

import org.uade.Exception.EmptyADTException;
import org.uade.Exception.GenericADTException;
import org.uade.entity.Terminal;
import org.uade.service.ReporteService;
import org.uade.util.ConsoleInput;

public class ReportesModulo {
    private final ReporteService reporteService;

    public ReportesModulo(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    public void ejecutarMenuReportes() {
        int opcion;
        do {
            System.out.println("\n--- MÓDULO DE REPORTES Y ESTADÍSTICAS ---");
            System.out.println("1. Terminal con más conexiones directas");
            System.out.println("2. Terminal con mayor disponibilidad de salidas");
            System.out.println("3. Terminal con mayor disponibilidad de llegadas");
            System.out.println("4. Ruta más utilizada (Historial)");
            System.out.println("5. Ruta menos utilizada (Historial)");
            System.out.println("0. Volver al menú principal");

            opcion = ConsoleInput.readOption("Seleccione una opción:");

            switch (opcion) {
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    ConsoleInput.waitEnter();
                    break;
                case 1:
                    mostrarTerminalMasConectada();
                    ConsoleInput.waitEnter();
                    break;
                case 2:
                    mostrarTerminalMasSalidas();
                    ConsoleInput.waitEnter();
                    break;
                case 3:
                    mostrarTerminalMasLlegadas();
                    ConsoleInput.waitEnter();
                    break;
                case 4:
                    mostrarRutaMasUtilizada();
                    ConsoleInput.waitEnter();
                    break;
                case 5:
                    mostrarRutaMenosUtilizada();
                    ConsoleInput.waitEnter();
                    break;
                default:
                    System.out.println("❌ Opción no válida.");
                    ConsoleInput.waitEnter();
            }
        } while (opcion != 0);
    }

    private void mostrarTerminalMasConectada() {
        try {
            System.out.println("\n--- ANALIZANDO CONEXIONES EN EL GRAFO ---");
            Terminal terminal = reporteService.obtenerTerminalMasConectada();
            System.out.println("📊 La terminal con más conexiones salientes directas es: " + terminal.getDescripcion() + " (" + terminal.getCodigo() + ")");
        } catch (GenericADTException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void mostrarTerminalMasSalidas() {
        try {
            System.out.println("\n--- ANALIZANDO HISTORIAL DE SALIDAS ---");
            String resultado = reporteService.obtenerTerminalMasSalidas();
            System.out.println("📊 " + resultado);
        } catch (EmptyADTException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private void mostrarTerminalMasLlegadas() {
        try {
            System.out.println("\n--- ANALIZANDO HISTORIAL DE LLEGADAS ---");
            String resultado = reporteService.obtenerTerminalMasLlegadas();
            System.out.println("📊 " + resultado);
        } catch (EmptyADTException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private void mostrarRutaMasUtilizada() {
        try {
            System.out.println("\n--- EVALUANDO USO DE RUTA MÁXIMA ---");
            String resultado = reporteService.obtenerRutaMasUtilizada();
            System.out.println("📊 " + resultado);
        } catch (EmptyADTException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private void mostrarRutaMenosUtilizada() {
        try {
            System.out.println("\n--- EVALUANDO USO DE RUTA MÍNIMA ---");
            String resultado = reporteService.obtenerRutaMenosUtilizada();
            System.out.println("📊 " + resultado);
        } catch (EmptyADTException | GenericADTException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }
}