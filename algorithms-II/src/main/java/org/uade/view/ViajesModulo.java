package org.uade.view;

import org.uade.Exception.EmptyADTException;
import org.uade.Exception.GenericADTException;
import org.uade.Exception.NotFoundException;
import org.uade.Exception.OpcionInvalida;
import org.uade.Exception.UnavailableDateException;
import org.uade.entity.*;
import org.uade.service.FlotaService;
import org.uade.service.RutaService;
import org.uade.service.ViajeService;
import org.uade.structure.definition.LinkedListADT;
import org.uade.util.ConsoleInput;
import org.uade.util.ValidacionesUtil;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.uade.util.PriorityQueueADTUtil.print;

public class ViajesModulo {
    private final FlotaService flotaService;
    private final ViajeService viajeService;
    private final RutaService rutaService;

    public ViajesModulo(FlotaService flotaService, ViajeService viajeService, RutaService rutaService) {
        this.flotaService = flotaService;
        this.viajeService = viajeService;
        this.rutaService = rutaService;
    }

    public void ejecutarMenuViajes() {
        int opcionViajes;
        do {
            System.out.println("\n--- MÓDULO VIAJES ---");
            System.out.println("1. Registrar nuevo viaje");
            System.out.println("2. Asignar micro a viaje");
            System.out.println("3. Reprogramar viaje");
            System.out.println("4. Despachar próximo viaje");
            System.out.println("5. Mostrar viajes pendientes");
            System.out.println("6. Cambiar prioridad de viaje");
            System.out.println("7. Mostrar historial de viajes despachados");
            System.out.println("0. Volver al menú principal");

            opcionViajes = ConsoleInput.readOption("Seleccione una opción:");

            switch (opcionViajes) {
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    ConsoleInput.waitEnter();
                    break;
                case 1:
                    ejecutarRegistrarViaje();
                    ConsoleInput.waitEnter();
                    break;
                case 2:
                    ejecutarAsignarMicro();
                    ConsoleInput.waitEnter();
                    break;
                case 3:
                    ejecutarReprogramarViaje();
                    ConsoleInput.waitEnter();
                    break;
                case 4:
                    ejecutarDespacharProximoViaje();
                    ConsoleInput.waitEnter();
                    break;
                case 5:
                    ejecutarMostrarViajes();
                    ConsoleInput.waitEnter();
                    break;
                case 6:
                    ejecutarCambioPrioridad();
                    ConsoleInput.waitEnter();
                    break;
                case 7:
                    ejecutarMostrarHistorial();
                    ConsoleInput.waitEnter();
                    break;
                default:
                    System.out.println("❌ Opción no válida.");
                    ConsoleInput.waitEnter();
            }
        } while (opcionViajes != 0);
    }

    private void ejecutarRegistrarViaje() {
        try {
            System.out.println("\n--- REGISTRAR NUEVO VIAJE ---");

            String codOrigen = ConsoleInput.readRegexStringUpper(
                    "Ingrese el código de la terminal origen (Ej: BUE):",
                    ValidacionesUtil.REGEX_CODIGO_TERMINAL,
                    "Código de terminal inválido."
            );
            Terminal origen = new Terminal(codOrigen, codOrigen);

            String codDestino = ConsoleInput.readRegexStringUpper(
                    "Ingrese el código de la terminal destino (Ej: BUE):",
                    ValidacionesUtil.REGEX_CODIGO_TERMINAL,
                    "Código de terminal inválido."
            );
            Terminal destino = new Terminal(codDestino, codDestino);

            Ruta ruta = rutaService.buscarRuta(origen, destino);

            String fechaStr = ConsoleInput.readRegexString(
                    "Ingrese la fecha del viaje (dd/MM/yyyy):",
                    ValidacionesUtil.REGEX_FECHA,
                    "El formato de la fecha debe ser estrictamente dd/MM/yyyy."
            );
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fecha = LocalDate.parse(fechaStr, formatter);

            // Validamos que no ingrese 0 ni negativos
            int prioridad = ConsoleInput.readPositiveInt("Ingrese prioridad de viaje (mayor a 0):");

            viajeService.programarViaje(ruta, fecha, prioridad);
            System.out.println("✅ Viaje registrado exitosamente con fecha: " + fecha);

        } catch(DateTimeException e){
            System.out.println("❌ Error: La fecha ingresada no existe en el calendario.");
        } catch (NotFoundException | GenericADTException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void ejecutarAsignarMicro() {
        try {
            System.out.println("\n--- ASIGNAR MICRO A UN VIAJE ---");
            int idViaje = ConsoleInput.readPositiveInt("Ingrese el ID del viaje:");
            Viaje viaje = viajeService.findViajeById(idViaje);

            if (viaje.getMicroAsignado() != null) {
                System.out.println("⚠️ Advertencia: El viaje ID: " + idViaje + " ya tiene un micro asignado (" + viaje.getMicroAsignado().getPatente() + ").");
                return;
            }

            String patente = ConsoleInput.readRegexStringUpper(
                    "Ingrese la patente del micro (Ej: AAA111 o AA111AA):",
                    ValidacionesUtil.REGEX_PATENTE,
                    "Formato de patente inválido."
            );
            Micro micro = flotaService.getMicroDisponible(patente, viaje.getFecha());
            viajeService.asignarMicroAViaje(viaje, micro);

            System.out.println(viaje.toString());
            System.out.println("✅ Micro " + micro.getPatente() + " asignado correctamente.");

        } catch (NotFoundException | UnavailableDateException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void ejecutarReprogramarViaje() {
        try {
            System.out.println("\n--- REPROGRAMAR VIAJE ---");
            int idViaje = ConsoleInput.readPositiveInt("Ingrese el ID del viaje a reprogramar:");
            Viaje viaje = viajeService.findViajeById(idViaje);

            String fechaStr = ConsoleInput.readRegexString(
                    "Ingrese la nueva fecha del viaje (dd/MM/yyyy):",
                    ValidacionesUtil.REGEX_FECHA,
                    "El formato de la fecha debe ser estrictamente dd/MM/yyyy."
            );
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate nuevaFecha = LocalDate.parse(fechaStr, formatter);

            Micro microActual = viaje.getMicroAsignado();
            if (microActual != null) {
                String patenteActual = microActual.getPatente();
                if (!flotaService.estaDisponible(patenteActual, nuevaFecha)) {
                    System.out.println("⚠️ Aviso: El micro " + patenteActual + " está ocupado en la nueva fecha.");
                    System.out.println("🔄 Desvinculando el micro... El viaje quedará pendiente de nueva asignación.");

                    flotaService.eliminarViaje(microActual,viaje);
                    viajeService.desvincularMicro(viaje);
                }
            }

            viaje.setFecha(nuevaFecha);
            System.out.println("✅ Viaje ID: " + idViaje + " reprogramado exitosamente para la fecha: " + nuevaFecha);

        } catch (DateTimeException e) {
            System.out.println("❌ Error: La fecha ingresada no existe en el calendario.");
        } catch (NotFoundException | GenericADTException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void ejecutarDespacharProximoViaje() {
        try {
            System.out.println("\n--- DESPACHAR PRÓXIMO VIAJE ---");
            Viaje viaje = viajeService.despacharSiguienteViaje();
            System.out.println("✅ Viaje ID: " + viaje.getIdViaje() + " despachado con éxito.");
        } catch (EmptyADTException | GenericADTException e) {
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

            int idViaje = ConsoleInput.readPositiveInt("Ingrese el ID del viaje:");
            int nuevaPrioridad = ConsoleInput.readPositiveInt("Ingrese la nueva prioridad (mayor a 0):");

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

    private void ejecutarMostrarHistorial() {
        try {
            LinkedListADT<Viaje> historial = viajeService.getHistorialDespachados();
            System.out.println("\n--- HISTORIAL DE VIAJES DESPACHADOS ---");

            for (int i = 0; i < historial.size(); i++) {
                System.out.println(historial.get(i).toString());
            }

        } catch (EmptyADTException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}