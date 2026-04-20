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
    @Test
    @DisplayName("4. Debería incrementar el contador de actualizaciones")
    void testIncrementarActualizacion() {
        ContadorAgua c = new ContadorAgua("1", "A", 0, 0);
        c.incrementarActualizacion();
        assertEquals(1, c.getActualizaciones());
    }

    @Test
    @DisplayName("5. Debería cambiar la ubicación correctamente")
    void testSetUbicacion() {
        ContadorAgua c = new ContadorAgua("1", "Antigua", 0, 0);
        c.setUbicacion("Nueva");
        assertEquals("Nueva", c.getUbicacion());
    }

    @Test
    @DisplayName("6. Debería actualizar la lectura actual")
    void testSetLectura() {
        ContadorAgua c = new ContadorAgua("1", "A", 10.5, 0);
        c.setLecturaActual(20.8);
        assertEquals(20.8, c.getLecturaActual());
    }

    @Test
    @DisplayName("7. Debería soportar múltiples incrementos de actualización")
    void testMultiplesIncrementos() {
        ContadorAgua c = new ContadorAgua("1", "A", 0, 0);
        c.incrementarActualizacion();
        c.incrementarActualizacion();
        assertEquals(2, c.getActualizaciones());
    }
    @Test
    @DisplayName("8. El formato toString debe ser el esperado para el archivo plano")
    void testToStringFormat() {
        ContadorAgua c = new ContadorAgua("C1", "Ubi", 50.0, 1);
        String esperado = "C1|Ubi|50.0|1";
        assertEquals(esperado, c.toString());
    }

    @Test
    @DisplayName("9. Debería manejar lecturas de valor cero")
    void testLecturaCero() {
        ContadorAgua c = new ContadorAgua("1", "A", 0.0, 0);
        assertEquals(0.0, c.getLecturaActual());
    }

    @Test
    @DisplayName("10. El ID debe permanecer igual tras cambiar otros campos")
    void testInmutabilidadId() {
        ContadorAgua c = new ContadorAgua("ORIGINAL", "A", 0, 0);
        c.setUbicacion("B");
        c.setLecturaActual(100);
        assertEquals("ORIGINAL", c.getIdContador());
    }
}
