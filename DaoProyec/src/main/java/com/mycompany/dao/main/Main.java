package com.mycompany.dao.main;

import com.mycompany.dao.dao.ContadorDAOImpl;
import com.mycompany.dao.controller.ContadorController;
import com.mycompany.dao.view.ContadorFrame;

/**
 * CAPA: PUNTO DE ENTRADA
 * Ensambla el patrón MVC e inicia la aplicación.
 */
public class Main {
    public static void main(String[] args) {
        // Ejecutamos en el hilo de despacho de eventos de Swing (Buena práctica GUI)
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                // 1. Instanciar el acceso a datos (El Archivo TXT)
                ContadorDAOImpl dao = new ContadorDAOImpl();

                // 2. Instanciar la Interfaz Gráfica (La Ventana)
                ContadorFrame vista = new ContadorFrame();

                // 3. Crear el Controlador y "unir" los dos anteriores
                new ContadorController(vista, dao);

                // 4. Mostrar la aplicación al usuario
                vista.setVisible(true);

            } catch (Exception e) {
                System.err.println("Error crítico al iniciar la aplicación: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}