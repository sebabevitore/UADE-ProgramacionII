package org.uade.service;

import org.uade.Exception.NotFoundException;
import org.uade.Exception.UnavailableDateException;
import org.uade.entity.Micro;
import org.uade.entity.Tipo;
import org.uade.entity.Viaje;
import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.SimpleDictionaryADT;
import org.uade.structure.implementation.dynamic.DynamicLinkedListADT;
import org.uade.structure.implementation.dynamic.DynamicSimpleDictionaryADT;
import java.time.LocalDate;


public class FlotaService {
    private SimpleDictionaryADT<String, Micro> micros;


    public FlotaService() {
        this.micros = new DynamicSimpleDictionaryADT<String, Micro>();
    }

    public void registrarMicro(String patente, Tipo tipo) {
        // TODO: add validacion de patente segun formato que quiera.
        Micro micro = new Micro(patente, tipo);
        this.micros.add(patente, micro);
    }

    public LinkedListADT<Micro> mostrarMicrosAsignados() {
        LinkedListADT<Micro> microsAsignados = new DynamicLinkedListADT<Micro>();
        SetADT<String> patentes = micros.getKeys();
        while(!patentes.isEmpty()) {
            String patente = patentes.choose();
            Micro micro = micros.get(patente);
            if(micro.esAsignado()){
                microsAsignados.add(micro);
            }
            patentes.remove(patente);
        }
        return microsAsignados;
    }

    public boolean esMicroAsignado(String patente) {
        if(!patenteExiste(patente)){
            Micro micro = this.micros.get(patente);
            return micro.esAsignado();
        }
        return false;
    }

    public boolean estaDisponible(String patente, LocalDate fecha) {
        if(patenteExiste(patente)) {
            Micro micro = this.micros.get(patente);
            return micro.estaDisponible(fecha);
        }
        return false;
    }

    public boolean patenteExiste(String patente) {
        SetADT<String> patentes = micros.getKeys();
        return patentes.exist(patente.toUpperCase());
    }

    public Micro obtenerMicroSiEstaDisponible(String patente, LocalDate fecha) {
        Micro micro = this.micros.get(patente);
        if (micro == null) {
            throw new NotFoundException("El micro " + patente + " no existe");
        }
        if (!estaDisponible(patente, fecha)) {
            throw new UnavailableDateException("El micro no esta disponible en la fecha solicitada.");
        }
        return micro;
    }



}
