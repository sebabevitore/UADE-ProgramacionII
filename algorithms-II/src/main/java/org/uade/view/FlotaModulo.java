package org.uade.view;

import org.uade.Exception.OpcionInvalida;
import org.uade.entity.Tipo;
import org.uade.service.FlotaService;
import org.uade.service.ViajeService;
import org.uade.util.ConsoleInput;

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
            System.out.println("2. Asignar micro");
            System.out.println("3. OPCION");
            System.out.println("4. OPCION");
            System.out.println("0. Volver al menú principal");

            opcionFlota = ConsoleInput.readOption("Seleccione una opción:");

            switch (opcionFlota) {
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                case 1:
                    ejecutarRegistrarMicro();
                    break;
                case 2:
                    System.out.println("PENDIENTE");
                    ConsoleInput.waitEnter();
                    break;
                case 3:
                    System.out.println("PENDIENTE");
                    ConsoleInput.waitEnter();
                    break;
                case 4:
                    System.out.println("PENDIENTE");
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
        } catch (OpcionInvalida e) {
            System.out.println("❌ Error: " + e.getMessage());
            ConsoleInput.waitEnter();
        }
    }
}
