package org.uade.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uade.Exception.EmptyADTException;
import org.uade.Exception.GenericADTException;
import org.uade.Exception.NotFoundException;
import org.uade.entity.CambioPrioridad;
import org.uade.entity.Micro;
import org.uade.entity.Motivo;
import org.uade.entity.Ruta;
import org.uade.entity.Terminal;
import org.uade.entity.Tipo;
import org.uade.entity.Viaje;
import org.uade.structure.definition.PriorityQueueADT;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ViajeServiceTest {

    private ViajeService viajeService;
    private Ruta rutaTest;

    @BeforeEach
    void setUp() {
        viajeService = new ViajeService();
        Terminal origen = new Terminal("BUE", "Buenos Aires");
        Terminal destino = new Terminal("MAR", "Mar del Plata");
        org.uade.structure.definition.QueueADT<Terminal> paradas = new org.uade.structure.implementation.dynamic.DynamicQueueADT<>();
        rutaTest = new Ruta(origen, destino, paradas); 
    }

    @Test
    void testProgramarViajeYGetCola() {
        LocalDate fecha = LocalDate.now().plusDays(2);
        viajeService.programarViaje(rutaTest, fecha, 5);

        PriorityQueueADT<Viaje> cola = viajeService.getColaViajes();
        assertFalse(cola.isEmpty(), "La cola de viajes no debería estar vacía");
        
        Viaje viajeEnCola = cola.getElement();
        assertEquals(5, viajeEnCola.getPrioridadActual(), "La prioridad debería ser 5");
        assertEquals(fecha, viajeEnCola.getFecha(), "La fecha debería coincidir");
    }

    @Test
    void testCambiarPrioridadSuccess() {
        LocalDate fecha = LocalDate.now().plusDays(2);
        viajeService.programarViaje(rutaTest, fecha, 3);
        
        // Obtenemos el ID del viaje recién creado (será el primero en la cola)
        Viaje viajeOriginal = viajeService.getColaViajes().getElement();
        int idViaje = viajeOriginal.getIdViaje();

        viajeService.cambiarPrioridad(idViaje, 10, Motivo.OTRO);

        // Volvemos a obtener el tope de la cola (debería ser el mismo viaje actualizado)
        Viaje viajeModificado = viajeService.getColaViajes().getElement();
        
        assertEquals(10, viajeModificado.getPrioridadActual(), "La prioridad debería haber cambiado a 10");
        assertEquals(10, viajeService.getColaViajes().getPriority(), "La prioridad en el PriorityQueue debería reflejar el cambio a 10");
    }

    @Test
    void testCambiarPrioridadNotFoundThrowsException() {
        assertThrows(NotFoundException.class, () -> {
            viajeService.cambiarPrioridad(999, 10, Motivo.LLUVIA);
        });
    }

    @Test
    void testDespacharSiguienteViajeSuccess() {
        LocalDate fecha = LocalDate.now().plusDays(2);
        viajeService.programarViaje(rutaTest, fecha, 5);
        
        // Obtenemos el viaje para asignarle el micro
        Viaje viaje = viajeService.getColaViajes().getElement();
        Micro micro = new Micro("TEST99", Tipo.CAMA);
        
        viajeService.asignarMicroAViaje(viaje, micro);
        
        Viaje viajeDespachado = viajeService.despacharSiguienteViaje();
        
        assertNotNull(viajeDespachado, "Debería retornar el viaje despachado");
        assertTrue(viajeDespachado.isCompletado(), "El estado del viaje debería ser completado");
        
        // La cola debería estar vacía ahora
        assertThrows(EmptyADTException.class, () -> viajeService.getColaViajes());
        
        // El historial debería contener el viaje
        assertFalse(viajeService.getHistorialDespachados().isEmpty(), "El historial no debería estar vacío");
    }

    @Test
    void testDespacharSiguienteViajeSinMicroThrowsException() {
        LocalDate fecha = LocalDate.now().plusDays(2);
        viajeService.programarViaje(rutaTest, fecha, 5);
        
        // Intentamos despachar sin asignar micro
        GenericADTException exception = assertThrows(GenericADTException.class, () -> {
            viajeService.despacharSiguienteViaje();
        });
        
        assertTrue(exception.getMessage().contains("porque no tiene un micro asignado"));
    }

    @Test
    void testDespacharSiguienteViajeEmptyThrowsException() {
        EmptyADTException exception = assertThrows(EmptyADTException.class, () -> {
            viajeService.despacharSiguienteViaje();
        });
        
        assertEquals("No hay viajes en cola", exception.getMessage());
    }
}
