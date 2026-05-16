package org.uade.service;

import org.uade.entity.Micro;
import org.uade.entity.Viaje;
import org.uade.structure.definition.IDiccionario;
import org.uade.structure.definition.ILinkedList;
import java.time.LocalDate;

public class FlotaService {
    private IDiccionario<String, Micro> micros;

    public FlotaService() {
        this.micros = new DiccionarioClase<String, Micro>();
    }

    public void registrarMicro(Micro micro) {
        this.micros.insertar(micro.getPatente(), micro);
    }

    public void asignarMicroAViaje(Viaje viaje, String patente) {
        Micro micro = this.micros.obtener(patente);
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

    public ILinkedList<Micro> obtenerMicrosMasAsignados() {
        return null;
    }
}
