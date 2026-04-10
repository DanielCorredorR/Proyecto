package com.mycompany.dao.main;

import com.mycompany.dao.dao.ContadorDAO;
import com.mycompany.dao.dao.ContadorDAOImpl;
import com.mycompany.dao.controller.ContadorController;
import com.mycompany.dao.view.ContadorFrame;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                // 1. Creamos la implementación concreta (Persistencia en TXT)
                // Nota: Aquí podrías cambiar ContadorDAOImpl por cualquier otra clase que implemente ContadorDAO
                ContadorDAO daoPersistencia = new ContadorDAOImpl();

                // 2. Creamos la Vista
                ContadorFrame vistaApp = new ContadorFrame();

                // 3. INYECCIÓN DE DEPENDENCIAS: Pasamos la vista y el dao al controlador
                new ContadorController(vistaApp, daoPersistencia);

                // 4. Mostrar la interfaz
                vistaApp.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}