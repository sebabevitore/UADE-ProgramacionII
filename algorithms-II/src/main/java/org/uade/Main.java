package org.uade;

import org.uade.entity.*;
import org.uade.service.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        FlotaService flota = new FlotaService();
        PrioridadService prioridad = new PrioridadService();

        for (int i = 1; i <= 15; i++) {
            flota.registrarMicro(new Micro("AAA" + i, Tipo.CAMA));
        }
        System.out.println("Escenario inicial cargado: 15 micros registrados.");

        Ruta rutaTest = new Ruta("Buenos Aires", "Rosario");
        Viaje viaje1 = new Viaje(101, rutaTest, LocalDate.of(2026, 6, 15), 5);

        flota.asignarMicroAViaje(viaje1, "AAA1");

        prioridad.programarViaje(viaje1);

        prioridad.cambiarPrioridad(viaje1,3,Motivo.LLUVIA);
    }
}
