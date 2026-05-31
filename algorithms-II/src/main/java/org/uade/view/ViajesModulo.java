package org.uade.view;

import org.uade.Exception.EmptyADTException;
import org.uade.Exception.NotFoundException;
import org.uade.Exception.OpcionInvalida;
import org.uade.Exception.UnavailableDateException;
import org.uade.entity.Micro;
import org.uade.entity.Ruta;
import org.uade.entity.Viaje;
import org.uade.service.FlotaService;
import org.uade.service.ViajeService;
import org.uade.util.ConsoleInput;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ViajesModulo {
    private final FlotaService flotaService;
    private final ViajeService viajeService;

    public ViajesModulo(FlotaService flotaService, ViajeService viajeService) {
        this.flotaService = flotaService;
        this.viajeService = viajeService;
    }

    public void ejecutarMenuViajes() {
        int opcionViajes;
        do {
            System.out.println("\n--- MÓDULO VIAJES ---");
            System.out.println("1. Registrar nuevo viaje");
            System.out.println("2. Asignar micro a viaje");
            System.out.println("3. Mostrar viajes");
            System.out.println("4. Cambiar prioridad de viaje");
            System.out.println("0. Volver al menú principal");

            opcionViajes = ConsoleInput.readOption("Seleccione una opción:");

            switch (opcionViajes) {
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                case 1:
                    ejecutarRegistrarMicro();
                    break;
                case 2:
                    asignarMicro();
                    ConsoleInput.waitEnter();
                    break;
                case 3:
                    System.out.println("TODO: Implementar mostrar viajes");
                    ConsoleInput.waitEnter();
                    break;
                case 4:
                    System.out.println("TODO: Implementar cambio prioridad");
                    break;
                default:
                    System.out.println("❌ Opción no válida.");
                    ConsoleInput.waitEnter();
            }
        } while (opcionViajes != 0);
    }


    private void ejecutarRegistrarMicro() {
        try {
            System.out.println("\n--- REGISTRAR NUEVO VIAJE ---");

            // TODO: arreglar ruta cuando lo haga...
            Ruta ruta = new Ruta("bsas","caba");
            // fecha
            String fechaStr = ConsoleInput.readString("Ingrese la fecha del viaje (dd/MM/yyyy):");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fecha = LocalDate.parse(fechaStr, formatter);
            // TODO: validaciones de prioridad
            int prioridad = ConsoleInput.readInt("Ingrese prioridad de viaje");

            viajeService.programarViaje(ruta, fecha, prioridad);
            System.out.println("✅ Viaje registrado exitosamente con fecha: " + fecha);
            ConsoleInput.waitEnter();

        } catch(DateTimeException e){
            System.out.println("❌ Error: Formato de fecha incorrecto. Debe ser dd/MM/yyyy.");
            ConsoleInput.waitEnter();
        } catch (OpcionInvalida e) {
            System.out.println("❌ Error: " + e.getMessage());
            ConsoleInput.waitEnter();
        }
    }

    private void asignarMicro() {
        try {
            System.out.println("\n--- ASIGNAR MICRO PARA PROXIMO VIAJE EN COLA ---");
            String patente = ConsoleInput.readString("Ingrese la patente del micro:");
            Viaje viaje = viajeService.despacharSiguienteViaje();
            Micro micro = flotaService.obtenerMicroSiEstaDisponible(patente,viaje.getFecha());

            viajeService.asignarMicroAViaje(viaje,micro);
            System.out.println(viaje.toString());
            System.out.println("✅ Micro "+micro.toString()+" asignado correctamente.");

        } catch(EmptyADTException e){
            System.out.println("❌ Error: No hay viajes en la cola para despachar.");
            ConsoleInput.waitEnter();
        } catch (NotFoundException | UnavailableDateException e) {
            System.out.println("❌ Error: " + e.getMessage());
            ConsoleInput.waitEnter();
        }
    }
}
