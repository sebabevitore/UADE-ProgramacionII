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
        return true;
    }

    public void agregarViaje(Viaje viaje) {
        this.viajes.insertarFinal(viaje);
    }

    public int getCantViajes() {
        return this.viajes.obtenerTamanio();
    }

    public String getPatente() { return patente; }
    public Tipo getTipo() { return tipo; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Micro micro = (Micro) obj;
        return patente.equals(micro.patente);
    }
}
