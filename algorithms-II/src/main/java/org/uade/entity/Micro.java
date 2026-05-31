package org.uade.entity;

import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.implementation.dynamic.DynamicLinkedListADT;

import java.time.LocalDate;

public class Micro {
    private String patente;
    private Tipo tipo;
    private LinkedListADT<Viaje> viajes;

    public Micro(String patente, Tipo tipo) {
        this.patente = patente;
        this.tipo = tipo;
        this.viajes = new DynamicLinkedListADT<>();
    }

    public boolean estaDisponible(LocalDate fechaBusqueda) {
        for (int i = 0; i < this.viajes.size(); i++) {
            Viaje viajeActual = this.viajes.get(i);
            if (viajeActual.getFecha().equals(fechaBusqueda)) {
                return false;
            }
        }
        return true;
    }

    public boolean esAsignado() {
        return getCantViajes()>0;
    }

    public void agregarViaje(Viaje viaje) {
        this.viajes.add(viaje);
    }

    public int getCantViajes() { return this.viajes.size(); }

    public String getPatente() { return patente; }
    public Tipo getTipo() { return tipo; }


    @Override
    public String toString() {
        return  "patente: "+this.patente + " " + this.tipo.toString();
    }
}
