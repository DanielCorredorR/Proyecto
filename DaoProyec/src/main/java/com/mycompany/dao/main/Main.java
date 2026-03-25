package com.mycompany.dao.main;

import com.mycompany.dao.dao.*;
import com.mycompany.dao.servicio.ContadorService;
import com.mycompany.dao.modelo.ContadorAgua;
import com.mycompany.dao.util.ExploradorUtil;

public class Main {
    public static void main(String[] args) {
        try {
            // Inicialización (DIP: Inyectamos la implementación en el servicio)
            ContadorDAO dao = new ContadorDAOImpl();
            ContadorService servicio = new ContadorService(dao);

            String idPrueba = "CONT-001";

            // 1. Crear un contador inicial directamente en el DAO
            System.out.println("Creando contador inicial...");
            dao.guardar(new ContadorAgua(idPrueba, "Sector Norte", 0.0, 0));

            // 2. Probar Restricción #3 (Máximo 2 actualizaciones)
            System.out.println("Ejecutando actualización 1...");
            servicio.actualizarUbicacion(idPrueba, "Sector Sur");
            
            System.out.println("Ejecutando actualización 2...");
            servicio.actualizarUbicacion(idPrueba, "Sector Centro");

            // 3. Esta debe fallar (Tercera actualización)
            System.out.println("Intentando actualización 3 (Debe fallar)...");
            servicio.actualizarUbicacion(idPrueba, "Sector Occidente");

        } catch (Exception e) {
            System.err.println("Capturado en Main: " + e.getMessage());
        } finally {
            // 4. Mostrar Restricción #5 al final
            ExploradorUtil.mostrarPropiedadesArchivo("datos_contadores.txt");
            
            // También mostramos el log de errores si existe
            ExploradorUtil.mostrarPropiedadesArchivo("error_sistema.log");
        }
    }
}