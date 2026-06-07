package org.uade.view;

import org.uade.Exception.GenericADTException;
import org.uade.Exception.NotFoundException;
import org.uade.entity.Ruta;
import org.uade.entity.Terminal;
import org.uade.service.RutaService;
import org.uade.service.TerminalService;
import org.uade.structure.definition.SetADT;
import org.uade.util.ConsoleInput;


import static org.uade.util.SetADTUtil.printSet;
import static org.uade.util.SimpleDictionaryADTUtil.printDict;

public class RutaModulo {
    private final RutaService rutaService;
    private final TerminalService terminalService;

    public RutaModulo(RutaService rutaService, TerminalService terminalService) {
        this.rutaService = rutaService;
        this.terminalService = terminalService;
    }

    public void ejecutarMenuRutas() {
        int opcionRutas;
        do {
            System.out.println("\n--- MÓDULO RUTAS ---");
            System.out.println("1. Crear conexion entre terminales");
            System.out.println("2. Eliminar conexion entre terminales");
            System.out.println("3. Ver rutas posibles entre dos terminales");
            System.out.println("4. Mostrar terminales aisladas");
            System.out.println("0. Volver al menú principal");

            opcionRutas = ConsoleInput.readOption("Seleccione una opción:");

            switch (opcionRutas) {
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    ConsoleInput.waitEnter();
                    break;
                case 1:
                    ejecutarCrearConexion();
                    ConsoleInput.waitEnter();
                    break;
                case 2:
                    ejecutarEliminarConexion();
                    ConsoleInput.waitEnter();
                    break;
                case 3:
                    ejecutarVerRutasPosibles();
                    ConsoleInput.waitEnter();
                    break;
                case 4:
                    ejecutarMostrarTerminalesAisladas();
                    ConsoleInput.waitEnter();
                    break;
                default:
                    System.out.println("❌ Opción no válida.");
                    ConsoleInput.waitEnter();
            }
        } while (opcionRutas != 0);
    }

    private void ejecutarCrearConexion() {
        try {
            System.out.println("\n--- CREAR CONEXIÓN ENTRE TERMINALES ---");
            printDict(terminalService.getTerminales());

            String codOrigen = ConsoleInput.readString("Ingrese el código de la terminal origen:");
            Terminal origen = terminalService.obtenerTerminal(codOrigen);

            String codDestino = ConsoleInput.readString("Ingrese el código de la terminal destino:");
            Terminal destino = terminalService.obtenerTerminal(codDestino);

            int km = ConsoleInput.readInt("Ingrese la distancia en km:");

            rutaService.registrarConexion(origen, destino, km);
            System.out.println("✅ Conexión registrada exitosamente.");
        } catch (GenericADTException | NotFoundException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void ejecutarEliminarConexion() {
        try {
            System.out.println("\n--- ELIMINAR CONEXIÓN ENTRE TERMINALES ---");
            printDict(terminalService.getTerminales());

            String codOrigen = ConsoleInput.readString("Ingrese el código de la terminal origen:");
            Terminal origen = terminalService.obtenerTerminal(codOrigen);

            String codDestino = ConsoleInput.readString("Ingrese el código de la terminal destino:");
            Terminal destino = terminalService.obtenerTerminal(codDestino);

            rutaService.eliminarConexion(origen, destino);
            System.out.println("✅ Conexión eliminada exitosamente.");
        } catch (GenericADTException | NotFoundException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void ejecutarVerRutasPosibles() {
        try{
            printDict(terminalService.getTerminales());
            String codOrigen = ConsoleInput.readString("Ingrese el codigo de origen:");
            Terminal origen = terminalService.obtenerTerminal(codOrigen);
            String codDestino = ConsoleInput.readString("Ingrese el codigo de destino:");
            Terminal destino =  terminalService.obtenerTerminal(codDestino);
            int cantParadas = ConsoleInput.readInt("Ingrese el numero maximo de paradas:");
            SetADT<Ruta> rutasEncontradas = rutaService.rutasPosibles(origen,destino,cantParadas);
            System.out.println("\n--- RUTAS POSIBLES ---");
            printSet(rutasEncontradas);
        }catch (GenericADTException e){
            throw new GenericADTException("❌ Error: " + e.getMessage());
        }

    }

    private void ejecutarMostrarTerminalesAisladas() {
        // TODO: Implementar lógica
    }

}
