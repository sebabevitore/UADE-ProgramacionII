package org.uade.service;

import org.uade.entity.Micro;
import org.uade.entity.Viaje;
import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.definition.SimpleDictionaryADT;
import org.uade.structure.implementation.dynamic.DynamicLinkedListADT;
import org.uade.structure.implementation.dynamic.DynamicSimpleDictionaryADT;

import java.util.LinkedList;


public class FlotaService {
    private SimpleDictionaryADT<String, Micro> micros;


    public FlotaService() {
        this.micros = new DynamicSimpleDictionaryADT<String, Micro>();
    }

    public void registrarMicro(Micro micro) {
        this.micros.add(micro.getPatente(), micro);
    }

    public void asignarMicroAViaje(Viaje viaje, String patente) {
        Micro micro = this.micros.get(patente);
        if (micro == null) {
            System.out.println("Error: El micro no existe.");
            return;
        }

        if (micro.estaDisponible(viaje.getFecha())) {
            micro.agregarViaje(viaje);
            viaje.setMicroAsignado(micro);
            System.out.println("Micro asignado con éxito.");
        } else {
            System.out.println("Error: El micro no está disponible en esa fecha.");
        }
    }

    public LinkedListADT<Micro> mostrarMicrosAsignados() {
        LinkedListADT<Micro> microsAsignados = new DynamicLinkedListADT<Micro>();

        while(){

        }

        return microsAsignados;
    }

    public boolean esMicroAsignado(String patente) {

        return false;
    }


}
