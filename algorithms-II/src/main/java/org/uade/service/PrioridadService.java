package org.uade.service;

import org.uade.entity.Viaje;
import org.uade.entity.Motivo;
import org.uade.entity.CambioPrioridad;
import org.uade.structure.definition.IPriorityQueueADT;

public class PrioridadService {
    private IPriorityQueueADT<Viaje> colaViajes;

    public PrioridadService() {
        this.colaViajes = new PriorityQueueDynamic<Viaje>();
    }

    public void programarViaje(Viaje viaje) {
        this.colaViajes.insertar(viaje, viaje.getPrioridadActual());
    }

    public void cambiarPrioridad(Viaje viaje, Motivo motivo, String desc, int nuevaPrioridad) {
        CambioPrioridad cambio = new CambioPrioridad(motivo, desc);
        viaje.registrarCambioClimatico(cambio, nuevaPrioridad);
        this.colaViajes.eliminar(viaje);
        this.colaViajes.insertar(viaje, nuevaPrioridad);
    }

    public Viaje despacharSiguienteViaje() {
        return this.colaViajes.desacoplar();
    }
}
