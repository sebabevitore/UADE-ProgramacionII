package org.uade.entity;

import org.uade.structure.definition.IStackADT;
import java.time.LocalDate;

public class Viaje {
    private int idViaje;
    private Ruta ruta;
    private Micro microAsignado;
    private LocalDate fecha;
    private boolean completado;
    private int prioridadActual;
    private IStackADT<CambioPrioridad> cambios;

    public Viaje(int idViaje, Ruta ruta, LocalDate fecha, int prioridadBase) {
        this.idViaje = idViaje;
        this.ruta = ruta;
        this.fecha = fecha;
        this.prioridadActual = prioridadBase;
        this.completado = false;
        this.microAsignado = null;
        this.cambios = new StackDynamic<CambioPrioridad>();
    }

    public void registrarCambioClimatico(CambioPrioridad cambio, int nuevaPrioridad) {
        this.cambios.apilar(cambio);
        this.prioridadActual = nuevaPrioridad;
    }

    public int getIdViaje() { return idViaje; }
    public LocalDate getFecha() { return fecha; }
    public int getPrioridadActual() { return prioridadActual; }
    public Micro getMicroAsignado() { return microAsignado; }
    public void setMicroAsignado(Micro micro) { this.microAsignado = micro; }
    public boolean isCompletado() { return completado; }
    public void setCompletado(boolean completado) { this.completado = completado; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Viaje viaje = (Viaje) obj;
        return idViaje == viaje.idViaje;
    }
}
