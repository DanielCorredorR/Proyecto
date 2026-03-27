package com.mycompany.dao.servicio;

import com.mycompany.dao.dao.ContadorDAO;
import com.mycompany.dao.modelo.ContadorAgua;
import com.mycompany.dao.util.FileLogger;
import java.io.IOException;
/**
 * CAPA: SERVICIOS / CONTROLADOR (MVC)
 * Aquí se centraliza la lógica de negocio y las validaciones.
 * RESTRICCIÓN #2: Validación de datos no nulos.
 * RESTRICCIÓN #3: Límite de 2 actualizaciones máximo.
 */
public class ContadorService {
    private final ContadorDAO dao; // SOLID: DIP (Dependemos de la interfaz)
    private static final int LIMITE_CAMBIOS = 2; // Restricción #3

    public ContadorService(ContadorDAO dao) {
        this.dao = dao;
    }

    public void actualizarUbicacion(String id, String nuevaUbi) throws Exception {
        ContadorAgua contador = null;
        try {
            // Restricción #2: Validar que no sea null o vacío
            if (nuevaUbi == null || nuevaUbi.isBlank()) {
                throw new IllegalArgumentException("La ubicación no puede estar vacía.");
            }

            // Buscamos el contador
            contador = dao.buscarPorId(id)
                    .orElseThrow(() -> new Exception("Contador con ID " + id + " no encontrado."));

            // Restricción #3: Solo dejar actualizar 2 veces
            if (contador.getActualizaciones() >= LIMITE_CAMBIOS) {
                throw new IllegalStateException("Límite de actualizaciones (2) alcanzado para el ID: " + id);
            }

            // Si pasa las reglas, actualizamos
            contador.setUbicacion(nuevaUbi);
            contador.incrementarActualizacion(); // Aumenta el contador interno
            
            dao.guardar(contador);
            System.out.println("Actualización exitosa para ID: " + id);

        } catch (Exception e) {
            // Restricción #4: Guardar en el log cuando algo falla
            FileLogger.logException("Fallo al actualizar ubicación", e, (contador != null ? contador : "ID: " + id));
            throw e; // Volvemos a lanzar la excepción para que el Main sepa que falló
        }
    }
}