package org.uade.view;

import org.uade.service.FlotaService;
import org.uade.service.ViajeService;
import org.uade.util.ConsoleInput;

public class Menu {
    private final FlotaService flotaService;
    private final ViajeService viajeService;
    private final FlotaModulo flotaModulo;
    private final ViajesModulo viajesModulo;


    public Menu() {
        this.flotaService = new FlotaService();
        this.viajeService = new ViajeService();
        this.flotaModulo = new FlotaModulo(this.flotaService, this.viajeService);
        this.viajesModulo = new ViajesModulo(this.flotaService, this.viajeService);
    }

    public void launch() {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE TRANSPORTE ===");
            System.out.println("1. Flota");
            System.out.println("2. Viajes");
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
                default:
                    System.out.println("Opción no válida.");
                    ConsoleInput.waitEnter();
            }
        } while (opcion != 0);
    }






}
