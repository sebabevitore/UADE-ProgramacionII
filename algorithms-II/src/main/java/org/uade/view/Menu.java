package org.uade.view;

import org.uade.entity.Micro;
import org.uade.entity.Ruta;
import org.uade.entity.Tipo;
import org.uade.entity.Viaje;
import org.uade.service.FlotaService;
import org.uade.service.RutaService;
import org.uade.service.ViajeService;
import org.uade.util.ConsoleInput;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Menu {
    private final FlotaService flotaService;
    private final ViajeService viajeService;
    private final RutaService rutaService;
    private final FlotaModulo flotaModulo;
    private final ViajesModulo viajesModulo;
    private final RutaModulo rutaModulo;

    Micro sandero = new Micro("AB672PT", Tipo.EJECUTIVO);
    Micro ecosport = new Micro("AC095VV", Tipo.CAMA);
    Ruta ruta = new Ruta(1, 2); // bsas -> cordoba
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate fecha = LocalDate.parse("27/06/2001", formatter);
    Viaje viaje = new Viaje(ruta,fecha,2);

    public Menu() {
        this.flotaService = new FlotaService();
        this.viajeService = new ViajeService();
        this.rutaService = new RutaService();
        this.flotaModulo = new FlotaModulo(this.flotaService, this.viajeService);
        this.viajesModulo = new ViajesModulo(this.flotaService, this.viajeService);
        this.rutaModulo = new RutaModulo(this.rutaService);

        //PARA TEST
        this.flotaService.registrarMicro(sandero.getPatente(),sandero.getTipo());
        this.flotaService.registrarMicro(ecosport.getPatente(),ecosport.getTipo());
        this.viajeService.programarViaje(this.ruta,this.fecha,viaje.getPrioridadActual());
    }

    public void launch() {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE TRANSPORTE ===");
            System.out.println("1. Flota");
            System.out.println("2. Viajes");
            System.out.println("3. Rutas");
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
                default:
                    System.out.println("Opción no válida.");
                    ConsoleInput.waitEnter();
            }
        } while (opcion != 0);
    }
}
