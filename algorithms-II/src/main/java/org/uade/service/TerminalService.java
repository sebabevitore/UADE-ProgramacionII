package org.uade.service;

import org.uade.Exception.GenericADTException;
import org.uade.Exception.NotFoundException;
import org.uade.entity.Terminal;
import org.uade.structure.definition.SimpleDictionaryADT;
import org.uade.structure.implementation.dynamic.DynamicSimpleDictionaryADT;

public class TerminalService {
    private SimpleDictionaryADT<String, Terminal> terminales;

    public TerminalService() {
        this.terminales = new DynamicSimpleDictionaryADT<>();
    }

    public void registrarTerminal(Terminal terminal) {
        String codigo = terminal.getCodigo();
        if (!terminales.isEmpty() && codigoExiste(codigo)) {
            throw new GenericADTException("Ya existe una terminal con ese código.");
        }
        this.terminales.add(codigo, terminal);
    }

    public void eliminarTerminal(String codigo) {
        if (!codigoExiste(codigo)) {
            throw new NotFoundException("No existe una terminal con ese código.");
        }
        this.terminales.remove(codigo);
    }

    public Terminal obtenerTerminal(String codigo) {
        if (!codigoExiste(codigo)) {
            throw new NotFoundException("No existe una terminal con el código: " + codigo);
        }
        return this.terminales.get(codigo);
    }

    public SimpleDictionaryADT<String, Terminal> getTerminales() {
        if (this.terminales.isEmpty()) {
            throw new GenericADTException("No hay terminales registradas.");
        }
        return this.terminales;
    }

    public boolean codigoExiste(String codigo) {
        try {
            Terminal terminal = this.terminales.get(codigo);
            return terminal != null;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean estaVacio() {
        return this.terminales.isEmpty();
    }
}


