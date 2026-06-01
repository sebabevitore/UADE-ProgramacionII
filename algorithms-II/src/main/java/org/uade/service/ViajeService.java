package org.uade.service;

import org.uade.Exception.EmptyADTException;
import org.uade.Exception.NotFoundException;
import org.uade.entity.*;
import org.uade.structure.definition.PriorityQueueADT;
import org.uade.structure.implementation.dynamic.DynamicPriorityQueueADT;

import java.time.LocalDate;

import static org.uade.util.PriorityQueueADTUtil.copy;

public class ViajeService {
    private PriorityQueueADT<Viaje> colaViajes;

    public ViajeService() {
        this.colaViajes = new DynamicPriorityQueueADT<>();
    }

    public void programarViaje(Ruta ruta, LocalDate fecha, int prioridadBase) {
        //TODO: add validaciones
        Viaje viaje = new Viaje(ruta, fecha, prioridadBase);
        this.colaViajes.add(viaje, prioridadBase);
    }

    private void programarViaje(Viaje viaje) {
        this.colaViajes.add(viaje,viaje.getPrioridadActual());
    }

    public void cambiarPrioridad(int idViaje, int nuevaPrioridad, Motivo motivo) {
        Viaje viaje = findViajeById(idViaje);
        if (this.colaViajes.isEmpty() || !viajeEnCola(viaje)) {
            throw new NotFoundException("El viaje ID: " + viaje.getIdViaje() + " no esta en la cola");
        }
        removeViaje(viaje);
        CambioPrioridad cambio = new CambioPrioridad(motivo, nuevaPrioridad);
        viaje.cambiarPrioridad(cambio);
        programarViaje(viaje);
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

    private Viaje findViajeById(int idViaje){
        PriorityQueueADT<Viaje> copy = copy(this.colaViajes);
        while(!copy.isEmpty()){
            if(copy.getElement().getIdViaje() == idViaje){
                return copy.getElement();
            }
            copy.remove();
        }
        throw new NotFoundException("El viaje ID: " + idViaje + " no esta en la cola.");

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

    public void asignarMicroAViaje(Viaje viaje, Micro micro) {
        viaje.asignarMicro(micro);
        micro.agregarViaje(viaje);
    }

    public PriorityQueueADT<Viaje> getColaViajes() {
        if(this.colaViajes.isEmpty()) {
            throw new EmptyADTException("No hay viajes pendientes.");
        }
        return this.colaViajes;
    }


}
