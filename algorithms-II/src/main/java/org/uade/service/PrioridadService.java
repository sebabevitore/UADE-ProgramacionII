package org.uade.service;

import org.uade.entity.Viaje;
import org.uade.entity.Motivo;
import org.uade.entity.CambioPrioridad;
import org.uade.structure.definition.PriorityQueueADT;
import org.uade.structure.implementation.dynamic.DynamicPriorityQueueADT;

public class PrioridadService {
    private PriorityQueueADT<Viaje> colaViajes;

    public PrioridadService() {
        this.colaViajes = new DynamicPriorityQueueADT<>();
    }

    public void programarViaje(Viaje viaje) {
        this.colaViajes.add(viaje, viaje.getPrioridadActual());
    }

    public void cambiarPrioridad(Viaje viaje, Motivo motivo, String desc, int nuevaPrioridad) {
        CambioPrioridad cambio = new CambioPrioridad(motivo, desc);
        viaje.registrarCambioClimatico(cambio, nuevaPrioridad);
        this.colaViajes.remove(viaje);
        this.colaViajes.add(viaje, nuevaPrioridad);
    }

    public Viaje despacharSiguienteViaje() {
        return this.colaViajes.desacoplar();
    }
}
