package com.mycompany.dao.modelo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class ContadorAguaTest {

    @Test
    @DisplayName("1. Debería crear el objeto correctamente")
    void testCreacion() {
        ContadorAgua c = new ContadorAgua("C-101", "Sector A", 150.0, 0);
        assertNotNull(c);
    }

    @Test
    @DisplayName("2. El ID debe ser el asignado en el constructor")
    void testGetId() {
        ContadorAgua c = new ContadorAgua("ID-TEST", "Norte", 10.0, 0);
        assertEquals("ID-TEST", c.getIdContador());
    }

    @Test
    @DisplayName("3. Las actualizaciones iniciales deben ser 0")
    void testEstadoInicialActualizaciones() {
        ContadorAgua c = new ContadorAgua("1", "A", 0, 0);
        assertEquals(0, c.getActualizaciones());
    }
}