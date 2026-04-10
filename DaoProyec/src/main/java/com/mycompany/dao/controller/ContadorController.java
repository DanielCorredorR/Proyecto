package com.mycompany.dao.controller;

import com.mycompany.dao.dao.ContadorDAO;
import com.mycompany.dao.modelo.ContadorAgua;
import com.mycompany.dao.view.ContadorFrame;
import com.mycompany.dao.util.FileLogger;
import java.util.List;
import javax.swing.JOptionPane;

public class ContadorController {
    private final ContadorFrame vista;
    private final ContadorDAO dao; // Dependemos de la Abstracción (Interfaz)
    private static final int LIMITE_CAMBIOS = 2;

    // INYECCIÓN POR CONSTRUCTOR: Recibimos las dependencias desde fuera
    public ContadorController(ContadorFrame vista, ContadorDAO dao) {
        this.vista = vista;
        this.dao = dao;

        // Configurar eventos de botones
        this.vista.btnActualizar.addActionListener(e -> gestionarActualizacion());
        this.vista.btnRefrescar.addActionListener(e -> cargarDatosATabla());

        // UX: Clic en tabla
        this.vista.tablaContadores.getSelectionModel().addListSelectionListener(e -> {
            int fila = vista.tablaContadores.getSelectedRow();
            if (fila != -1) {
                vista.txtId.setText(vista.tablaContadores.getValueAt(fila, 0).toString());
            }
        });

        cargarDatosATabla();
    }

    private void cargarDatosATabla() {
        try {
            vista.modeloTabla.setRowCount(0);
            List<ContadorAgua> lista = dao.listarTodos();
            for (ContadorAgua c : lista) {
                vista.modeloTabla.addRow(new Object[]{
                    c.getIdContador(), c.getUbicacion(), c.getLecturaActual(), c.getActualizaciones()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al cargar: " + ex.getMessage());
        }
    }

    private void gestionarActualizacion() {
        String id = vista.txtId.getText();
        String nuevaUbi = vista.txtNuevaUbi.getText();
        ContadorAgua contador = null;

        try {
            if (nuevaUbi == null || nuevaUbi.isBlank()) 
                throw new IllegalArgumentException("La ubicación no puede estar vacía.");

            contador = dao.buscarPorId(id)
                    .orElseThrow(() -> new Exception("Contador no encontrado."));

            if (contador.getActualizaciones() >= LIMITE_CAMBIOS) 
                throw new IllegalStateException("Límite de cambios (2) alcanzado.");

            contador.setUbicacion(nuevaUbi);
            contador.incrementarActualizacion();
            dao.guardar(contador);

            cargarDatosATabla();
            JOptionPane.showMessageDialog(vista, "Actualización exitosa.");

        } catch (Exception ex) {
            FileLogger.logException("Error GUI", ex, (contador != null ? contador : "ID: " + id));
            JOptionPane.showMessageDialog(vista, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}