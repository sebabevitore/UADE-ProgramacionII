package org.uade.entity;

import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;

import java.time.LocalDate;

public class Viaje {
    private int idViaje;
    private Ruta ruta;
    private Micro microAsignado;
    private LocalDate fecha;
    private boolean completado;
    private int prioridadActual;
    private StackADT<CambioPrioridad> cambios;

    public Viaje(int idViaje, Ruta ruta, LocalDate fecha, int prioridadBase) {
        this.idViaje = idViaje;
        this.ruta = ruta;
        this.fecha = fecha;
        this.prioridadActual = prioridadBase;
        this.completado = false;
        this.microAsignado = null;
        this.cambios = new DynamicStackADT<CambioPrioridad>();
    }

    public void cambiarPrioridad(CambioPrioridad cambio) {
        this.cambios.add(cambio);
        this.prioridadActual = cambio.getPrioridad();
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Viaje) {
            Viaje aux = (Viaje) obj;
            return this.idViaje == aux.idViaje;
        }
        return false;
    }

    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    public Micro getMicroAsignado() {
        return microAsignado;
    }

    public void setMicroAsignado(Micro microAsignado) {
        this.microAsignado = microAsignado;
    }

    public int getPrioridadActual() {
        return prioridadActual;
    }

    public void setPrioridadActual(int prioridadActual) {
        this.prioridadActual = prioridadActual;
    }

    public StackADT<CambioPrioridad> getCambios() {
        return cambios;
    }

    public void setCambios(StackADT<CambioPrioridad> cambios) {
        this.cambios = cambios;
    }


}
