package org.uade.view;

import org.uade.Exception.EmptyADTException;
import org.uade.Exception.NotFoundException;
import org.uade.Exception.OpcionInvalida;
import org.uade.Exception.UnavailableDateException;
import org.uade.entity.Micro;
import org.uade.entity.Motivo;
import org.uade.entity.Ruta;
import org.uade.entity.Viaje;
import org.uade.service.FlotaService;
import org.uade.service.ViajeService;
import org.uade.util.ConsoleInput;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.uade.util.PriorityQueueADTUtil.print;

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
            System.out.println("3. Mostrar viajes pendientes");
            System.out.println("4. Cambiar prioridad de viaje");
            System.out.println("0. Volver al menú principal");

            opcionViajes = ConsoleInput.readOption("Seleccione una opción:");

            switch (opcionViajes) {
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    ConsoleInput.waitEnter();
                    break;
                case 1:
                    ejecutarRegistrarMicro();
                    ConsoleInput.waitEnter();

                    break;
                case 2:
                    ejecutarAsignarMicro();
                    ConsoleInput.waitEnter();
                    break;
                case 3:
                    ejecutarMostrarViajes();
                    ConsoleInput.waitEnter();
                    break;
                case 4:
                    ejecutarCambioPrioridad();
                    ConsoleInput.waitEnter();
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

        } catch(DateTimeException e){
            System.out.println("❌ Error: Formato de fecha incorrecto. Debe ser dd/MM/yyyy.");
        } catch (OpcionInvalida e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void ejecutarAsignarMicro() {
        try {
            System.out.println("\n--- ASIGNAR MICRO PARA PROXIMO VIAJE EN COLA ---");
            String patente = ConsoleInput.readString("Ingrese la patente del micro:");
            Viaje viaje = viajeService.despacharSiguienteViaje();
            Micro micro = flotaService.getMicroDisponible(patente,viaje.getFecha());
            viajeService.asignarMicroAViaje(viaje,micro);
            System.out.println(viaje.toString());
            System.out.println("✅ Micro "+micro.toString()+" asignado correctamente.");

        } catch(EmptyADTException e){
            System.out.println("❌ Error: No hay viajes en la cola para despachar.");
        } catch (NotFoundException | UnavailableDateException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void ejecutarMostrarViajes(){
        try{
            print(viajeService.getColaViajes());
        }catch (EmptyADTException e){
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void ejecutarCambioPrioridad() {
        try {
            System.out.println("\n--- CAMBIAR PRIORIDAD DE VIAJE ---");

            int idViaje = ConsoleInput.readInt("Ingrese el ID del viaje:");
            int nuevaPrioridad = ConsoleInput.readInt("Ingrese la nueva prioridad:");

            Motivo motivo = null;
            boolean motivoValido = false;
            do {
                System.out.println("\nMotivos de cambio:");
                System.out.println("1. LLUVIA");
                System.out.println("2. VISIBILIDAD");
                System.out.println("3. OTRO");
                int opcionMotivo = ConsoleInput.readInt("Seleccione una opción:");

                switch (opcionMotivo) {
                    case 1:
                        motivo = Motivo.LLUVIA;
                        motivoValido = true;
                        break;
                    case 2:
                        motivo = Motivo.VISIBILIDAD;
                        motivoValido = true;
                        break;
                    case 3:
                        motivo = Motivo.OTRO;
                        motivoValido = true;
                        break;
                    default:
                        System.out.println("❌ Opción no válida. Intente nuevamente.");
                }
            } while (!motivoValido);

            viajeService.cambiarPrioridad(idViaje, nuevaPrioridad, motivo);

            System.out.println("✅ Prioridad cambiada correctamente.");

        } catch (NotFoundException | OpcionInvalida e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }




}
