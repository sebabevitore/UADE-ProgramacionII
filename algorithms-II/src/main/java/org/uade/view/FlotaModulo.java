package org.uade.view;

import org.uade.Exception.EmptyADTException;
import org.uade.Exception.GenericADTException;
import org.uade.Exception.NotFoundException;
import org.uade.Exception.OpcionInvalida;
import org.uade.entity.Micro;
import org.uade.entity.Tipo;
import org.uade.service.FlotaService;
import org.uade.service.ViajeService;
import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.definition.SimpleDictionaryADT;
import org.uade.util.ConsoleInput;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.uade.util.LinkedListADTUtil.print;
import static org.uade.util.SimpleDictionaryADTUtil.print;

public class FlotaModulo {
    private final FlotaService flotaService;
    private final ViajeService viajeService;

    public FlotaModulo(FlotaService flotaService, ViajeService viajeService) {
        this.flotaService = flotaService;
        this.viajeService = viajeService;
    }

    public void ejecutarMenuFlota() {
        int opcionFlota;
        do {
            System.out.println("\n--- MÓDULO FLOTA ---");
            System.out.println("1. Registrar nuevo micro");
            System.out.println("2. Disponibilidad de micro");
            System.out.println("3. Mostrar micros con viajes (pendientes/terminados)");
            System.out.println("4. Mostrar todos los micros");
            System.out.println("0. Volver al menú principal");

            opcionFlota = ConsoleInput.readOption("Seleccione una opción:");

            switch (opcionFlota) {
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    ConsoleInput.waitEnter();
                    break;
                case 1:
                    ejecutarRegistrarMicro();
                    ConsoleInput.waitEnter();
                    break;
                case 2:
                    ejecutarEstaDisponible();
                    ConsoleInput.waitEnter();
                    break;
                case 3:
                    ejecutarMostrarMicrosAsignados();
                    ConsoleInput.waitEnter();
                    break;
                case 4:
                    ejecutarMostrarMicros();
                    ConsoleInput.waitEnter();
                    break;
                default:
                    System.out.println("❌ Opción no válida.");
                    ConsoleInput.waitEnter();
            }
        } while (opcionFlota != 0);
    }


    private void ejecutarRegistrarMicro() {
        try {
            System.out.println("\n--- REGISTRAR NUEVO MICRO ---");

            String patente = ConsoleInput.readString("Ingrese la patente del micro:");
            int tipoInt = ConsoleInput.readInt("Tipo (1-Cama, 2-Semicama, 3-Ejecutivo):");

            Tipo tipo;
            if (tipoInt == 1) {
                tipo = Tipo.CAMA;
            } else if (tipoInt == 2) {
                tipo = Tipo.SEMICAMA;
            } else if (tipoInt == 3) {
                tipo = Tipo.EJECUTIVO;
            } else {
                throw new OpcionInvalida("Tipo incorrecto.");
            }

            flotaService.registrarMicro(patente,tipo);
            System.out.println("✅ Micro registrado exitosamente.");
        } catch (OpcionInvalida | GenericADTException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void ejecutarMostrarMicrosAsignados(){
        try{
            LinkedListADT<Micro> microsAsignados = flotaService.mostrarMicrosAsignados();
            print(microsAsignados);
        }catch (EmptyADTException e){
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void ejecutarEstaDisponible(){
        try{
            String patente = ConsoleInput.readString("Ingrese la patente del micro:");
            String fechaStr = ConsoleInput.readString("Ingrese la fecha del viaje (dd/MM/yyyy):");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fecha = LocalDate.parse(fechaStr, formatter);

            if(flotaService.estaDisponible(patente, fecha)){
                System.out.println("✅ El micro "+patente+" esta disponible el dia  "+fechaStr);
            }
            else{
                System.out.println("❌ El micro "+patente+" esta ocupado el dia  "+fechaStr);
            }

        }catch (NotFoundException e){
            System.out.println("❌ Error:  " + e.getMessage());
        } catch(DateTimeException e){
            System.out.println("❌ Error: Formato de fecha incorrecto. Debe ser dd/MM/yyyy.");
        }
    }

    private void ejecutarMostrarMicros(){
        try{
            SimpleDictionaryADT<String, Micro> micros = flotaService.getMicros();
            print(micros);
        }catch (EmptyADTException e){
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
