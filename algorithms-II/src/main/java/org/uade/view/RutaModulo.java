package org.uade.view;

import org.uade.Exception.GenericADTException;
import org.uade.Exception.NotFoundException;
import org.uade.entity.Ruta;
import org.uade.entity.Terminal;
import org.uade.service.RutaService;
import org.uade.service.TerminalService;
import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.definition.SetADT;
import org.uade.util.ConsoleInput;


import static org.uade.util.LinkedListADTUtil.print;
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
            System.out.println("4. Crear ruta");
            System.out.println("5. Reportes");
            // mostrar rutas aisladas,
            // mostrar Terminales con mayor número de salidas y de llegadas y
            // Terminales con más conexiones directas con otras terminales
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
                    ejecutarCrearRuta();
                    ConsoleInput.waitEnter();
                    break;
                case 5:
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

    private LinkedListADT<Ruta> getRutasPosibles() throws GenericADTException, NotFoundException {
        printDict(terminalService.getTerminales());

        String codOrigen = ConsoleInput.readString("Ingrese el código de la terminal origen:");
        Terminal origen = terminalService.obtenerTerminal(codOrigen);

        String codDestino = ConsoleInput.readString("Ingrese el código de la terminal destino:");
        Terminal destino = terminalService.obtenerTerminal(codDestino);

        int cantParadas = ConsoleInput.readInt("Ingrese el número máximo de paradas intermedias:");

        return rutaService.rutasPosibles(origen, destino, cantParadas);
    }

    private void ejecutarVerRutasPosibles() {
        try {
            System.out.println("\n--- BUSCAR RUTAS POSIBLES ---");
            LinkedListADT<Ruta> rutasEncontradas = getRutasPosibles();

            System.out.println("\n--- RUTAS POSIBLES ---");
            print(rutasEncontradas);

        } catch (GenericADTException | NotFoundException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private void ejecutarCrearRuta() {
        try {
            System.out.println("\n--- CREAR RUTA ---");
            LinkedListADT<Ruta> rutasEncontradas = getRutasPosibles();

            System.out.println("\n--- RUTAS DISPONIBLES PARA CREAR ---");
            for (int i = 0; i < rutasEncontradas.size(); i++) {
                System.out.println((i + 1) + ". " + rutasEncontradas.get(i).toString());
            }

            int seleccion = ConsoleInput.readInt("Seleccione una ruta (número):") - 1;

            if (seleccion < 0 || seleccion >= rutasEncontradas.size()) {
                System.out.println("❌ Selección no válida.");
                return;
            }

            Ruta rutaSeleccionada = rutasEncontradas.get(seleccion);
            rutaService.crearRuta(rutaSeleccionada);
            System.out.println("✅ Ruta creada exitosamente.");

        } catch (GenericADTException | NotFoundException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private void ejecutarMostrarTerminalesAisladas() {
        System.out.println("\n--- TERMINALES AISLADAS ---");
        SetADT<Terminal> desconectadas = rutaService.identificarTerminalesDesconectadas();
        if (desconectadas.isEmpty()) {
            System.out.println("No hay terminales aisladas.");
        } else {
            printSet(desconectadas);
        }
    }

    private void ejecutarMostrarReporte() {

    }

}
