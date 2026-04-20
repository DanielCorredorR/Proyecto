package com.mycompany.dao.main; // Se queda en su propio paquete

import com.mycompany.dao.modelo.dao.ContadorDAO;
import com.mycompany.dao.modelo.dao.ContadorDAOImpl;
import com.mycompany.dao.controller.ContadorController;
import com.mycompany.dao.view.ContadorFrame;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                // DAO ahora viene de .modelo
                ContadorDAO dao = new ContadorDAOImpl();
                // Frame viene de .view
                ContadorFrame vista = new ContadorFrame();
                // Controlador viene de .controller e inyectamos el DAO
                new ContadorController(vista, dao);

                vista.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
