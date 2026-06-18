package org.uade.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uade.Exception.EmptyADTException;
import org.uade.Exception.GenericADTException;
import org.uade.Exception.NotFoundException;
import org.uade.Exception.UnavailableDateException;
import org.uade.entity.Micro;
import org.uade.entity.Tipo;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FlotaServiceTest {

    private FlotaService flotaService;

    @BeforeEach
    void setUp() {
        flotaService = new FlotaService();
    }

    @Test
    void testRegistrarMicroSuccess() {
        flotaService.registrarMicro("AAA111", Tipo.CAMA);
        
        assertFalse(flotaService.getMicros().isEmpty(), "El diccionario de micros no debería estar vacío tras registrar");
        
        Micro micro = flotaService.getMicroDisponible("AAA111", LocalDate.now());
        assertNotNull(micro, "El micro debería existir");
        assertEquals("AAA111", micro.getPatente(), "La patente debería coincidir");
        assertEquals(Tipo.CAMA, micro.getTipo(), "El tipo debería coincidir");
    }

    @Test
    void testRegistrarMicroDuplicatedThrowsException() {
        flotaService.registrarMicro("BBB222", Tipo.SEMICAMA);
        
        GenericADTException exception = assertThrows(GenericADTException.class, () -> {
            flotaService.registrarMicro("BBB222", Tipo.EJECUTIVO);
        });
        
        assertEquals("Ya existe un micro con esa patente.", exception.getMessage());
    }

    @Test
    void testGetMicrosEmptyThrowsException() {
        EmptyADTException exception = assertThrows(EmptyADTException.class, () -> {
            flotaService.getMicros();
        });
        
        assertEquals("No hay micros registrados.", exception.getMessage());
    }

    @Test
    void testEstaDisponibleSuccess() {
        flotaService.registrarMicro("CCC333", Tipo.EJECUTIVO);
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        
        assertTrue(flotaService.estaDisponible("CCC333", tomorrow), "Un micro recién creado debería estar disponible");
    }

    @Test
    void testEstaDisponibleNotFoundThrowsException() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            flotaService.estaDisponible("NOTFOUND", tomorrow);
        });
        
        assertEquals("No se encontro el micro.", exception.getMessage());
    }
}
