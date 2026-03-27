package com.mycompany.dao.main;

import com.mycompany.dao.dao.*;
import com.mycompany.dao.servicio.ContadorService;
import com.mycompany.dao.modelo.ContadorAgua;
/**
 * CAPA: VISTA / ENTRY POINT (MVC)
 * Punto de entrada del sistema que simula la interacción del usuario.
 * Demuestra el flujo completo: Captura -> Validación -> Persistencia -> Logging.
 */
public class Main {
    public static void main(String[] args) {
        try {
            // Inicializamos el sistema (SOLID: Inyección de dependencias)
            ContadorDAO dao = new ContadorDAOImpl();
            ContadorService servicio = new ContadorService(dao);

            String idTest = "PRUEBA-999";

            // 1. INSERTAR UN REGISTRO INICIAL
            System.out.println("--- PASO 1: Insertando registro inicial ---");
            ContadorAgua nuevo = new ContadorAgua(idTest, "Sede Central", 0.0, 0);
            dao.guardar(nuevo);
            System.out.println("Registro guardado exitosamente.");

            // 2. INTENTAR ACTUALIZAR CON CAMPOS NULL (Esto debe disparar la excepción)
            System.out.println("\n--- PASO 2: Intentando actualizar con ubicación NULL ---");
            // El servicio validará el null, lanzará la excepción y FileLogger creará el archivo con el timestamp
            servicio.actualizarUbicacion(idTest, null); 

        } catch (Exception e) {
            System.err.println("\n[CAPTURA EN MAIN]: Se detectó el error esperado -> " + e.getMessage());
            System.out.println("Revisa tu carpeta del proyecto, deberías ver un archivo 'log20260326...log'");
        }
    }
}