package org.uade.view;

import org.uade.Exception.GenericADTException;
import org.uade.Exception.NotFoundException;
import org.uade.entity.Terminal;
import org.uade.service.RutaService;
import org.uade.service.TerminalService;
import org.uade.structure.definition.SimpleDictionaryADT;
import org.uade.util.ConsoleInput;

import static org.uade.util.SimpleDictionaryADTUtil.printDict;

public class TerminalModulo {
    private final TerminalService terminalService;
    private final RutaService rutaService;

    public TerminalModulo(TerminalService terminalService, RutaService rutaService) {
        this.terminalService = terminalService;
        this.rutaService = rutaService;
    }

    public void ejecutarMenuTerminales() {
        int opcionTerminales;
        do {
            System.out.println("\n--- MÓDULO TERMINALES ---");
            System.out.println("1. Registrar nueva terminal");
            System.out.println("2. Eliminar terminal");
            System.out.println("3. Mostrar todas las terminales");
            System.out.println("0. Volver al menú principal");

            opcionTerminales = ConsoleInput.readOption("Seleccione una opción:");

            switch (opcionTerminales) {
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    ConsoleInput.waitEnter();
                    break;
                case 1:
                    ejecutarRegistrarTerminal();
                    ConsoleInput.waitEnter();
                    break;
                case 2:
                    ejecutarEliminarTerminal();
                    ConsoleInput.waitEnter();
                    break;
                case 3:
                    ejecutarMostrarTerminales();
                    ConsoleInput.waitEnter();
                    break;
                default:
                    System.out.println("❌ Opción no válida.");
                    ConsoleInput.waitEnter();
            }
        } while (opcionTerminales != 0);
    }

    private void ejecutarRegistrarTerminal() {
        try {
            System.out.println("\n--- REGISTRAR NUEVA TERMINAL ---");

            String codigo = ConsoleInput.readString("Ingrese el código de la terminal:");
            String descripcion = ConsoleInput.readString("Ingrese la descripción de la terminal:");
            Terminal terminal = new Terminal(codigo, descripcion);
            terminalService.registrarTerminal(terminal);
            rutaService.registrarTerminal(terminal);
            System.out.println("✅ Terminal registrada exitosamente.");

        } catch (GenericADTException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void ejecutarEliminarTerminal() {
        try {
            System.out.println("\n--- ELIMINAR TERMINAL ---");
            String codigo = ConsoleInput.readString("Ingrese el código de la terminal a eliminar:");
            terminalService.eliminarTerminal(codigo);
            System.out.println("✅ Terminal eliminada exitosamente.");
        } catch (NotFoundException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void ejecutarMostrarTerminales() {
        try {
            System.out.println("\n--- TERMINALES REGISTRADAS ---");
            SimpleDictionaryADT<String, Terminal> terminales = terminalService.getTerminales();
            printDict(terminales);
        } catch (GenericADTException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}


