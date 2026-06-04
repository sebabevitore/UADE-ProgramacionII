package org.uade.view;

import org.uade.Exception.GenericADTException;
import org.uade.entity.Terminal;
import org.uade.service.RutaService;
import org.uade.structure.definition.SetADT;
import org.uade.util.ConsoleInput;

import static org.uade.util.SetADTUtil.print;

public class RutaModulo {
    private final RutaService rutaService;

    public RutaModulo(RutaService rutaService) {
        this.rutaService = rutaService;
    }

    public void ejecutarMenuRutas() {
        int opcionRutas;
        do {
            System.out.println("\n--- MÓDULO RUTAS ---");
            System.out.println("1. Registrar terminal");
            System.out.println("2. Registrar conexion entre terminales");
            System.out.println("3. Mostrar terminales");
            System.out.println("0. Volver al menú principal");

            opcionRutas = ConsoleInput.readOption("Seleccione una opción:");

            switch (opcionRutas) {
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    ConsoleInput.waitEnter();
                    break;
                case 1:
                    ejecutarRegistrarTerminal();
                    ConsoleInput.waitEnter();
                    break;
                case 2:
                    ejecutarRegistrarConexion();
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
        } while (opcionRutas != 0);
    }

    private void ejecutarRegistrarTerminal() {
        try {
            System.out.println("\n--- REGISTRAR NUEVA TERMINAL ---");

            int codigo = ConsoleInput.readInt("Ingrese el codigo de la terminal:");
            String descripcion = ConsoleInput.readString("Ingrese la descripcion de la terminal:");

            Terminal terminal = new Terminal(codigo, descripcion);
            rutaService.registrarTerminal(terminal);
            System.out.println("✅ Terminal registrada exitosamente.");
        } catch (GenericADTException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void ejecutarRegistrarConexion() {
        try {
            System.out.println("\n--- REGISTRAR CONEXION ENTRE TERMINALES ---");

            int codigoOrigen = ConsoleInput.readInt("Ingrese el codigo de la terminal origen:");
            int codigoDestino = ConsoleInput.readInt("Ingrese el codigo de la terminal destino:");
            int km = ConsoleInput.readInt("Ingrese la distancia en km:");

            Terminal origen = new Terminal(codigoOrigen, "");
            Terminal destino = new Terminal(codigoDestino, "");

            rutaService.registrarConexion(origen, destino, km);
            System.out.println("✅ Conexion registrada exitosamente.");
        } catch (GenericADTException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void ejecutarMostrarTerminales() {
        try {
            SetADT<Terminal> terminales = rutaService.getGrafoTerminales().getVertxs();
            print(terminales);
        } catch (GenericADTException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
