package org.uade.service;

import org.uade.Exception.EmptyADTException;
import org.uade.Exception.NotFoundException;
import org.uade.entity.Viaje;
import org.uade.entity.Motivo;
import org.uade.entity.CambioPrioridad;
import org.uade.structure.definition.PriorityQueueADT;
import org.uade.structure.implementation.dynamic.DynamicPriorityQueueADT;

import static org.uade.util.PriorityQueueADTUtil.copy;

public class PrioridadService {
    private PriorityQueueADT<Viaje> colaViajes;

    public PrioridadService() {
        this.colaViajes = new DynamicPriorityQueueADT<>();
    }

    public void programarViaje(Viaje viaje) {
        this.colaViajes.add(viaje, viaje.getPrioridadActual());
    }

    public void cambiarPrioridad(Viaje viaje, int nuevaPrioridad, Motivo motivo) {
        if (this.colaViajes.isEmpty() || !viajeEnCola(viaje)) {
            throw new NotFoundException("El viaje ID: " + viaje.getIdViaje() + " no esta en la cola");
        }
        removeViaje(viaje);
        CambioPrioridad cambio = new CambioPrioridad(motivo, nuevaPrioridad);
        viaje.cambiarPrioridad(cambio);
        programarViaje(viaje);
        System.out.println("Cambio prioridad: " + cambio.getPrioridad());
        System.out.println("Motivo: " + cambio.getMotivo());

    }

    private void removeViaje(Viaje viaje){
        PriorityQueueADT<Viaje> aux = new DynamicPriorityQueueADT<Viaje>();
        while(!this.colaViajes.isEmpty()){
            aux.add(this.colaViajes.getElement(),this.colaViajes.getPriority());
            this.colaViajes.remove();
        }

        while(!aux.isEmpty()){
            if(!aux.getElement().equals(viaje)){
                this.colaViajes.add(aux.getElement(),aux.getPriority());
            }
            aux.remove();
        }
    }


    private boolean viajeEnCola(Viaje viaje) {
        PriorityQueueADT<Viaje> copy = copy(this.colaViajes);
        while(!copy.isEmpty()) {
            Viaje viajeActual = copy.getElement();
            if(viajeActual.equals(viaje)) {
                return true;
            }
            copy.remove();
        }
        return false;
    }



    public Viaje despacharSiguienteViaje() {
        if(this.colaViajes.isEmpty()) {
            throw new EmptyADTException("No hay viajes en cola");
        }
        Viaje viaje = this.colaViajes.getElement();
        this.colaViajes.remove();

        return viaje;
    }
}
