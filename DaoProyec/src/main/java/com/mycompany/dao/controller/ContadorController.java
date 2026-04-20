package com.mycompany.dao.controller;

import com.mycompany.dao.modelo.ContadorAgua;
import com.mycompany.dao.modelo.dao.ContadorDAO;
import com.mycompany.dao.view.ContadorFrame;
import com.mycompany.dao.controller.util.FileLogger; // Ajustado a tu nueva captura
import java.util.List;
import javax.swing.JOptionPane;

public class ContadorController {
    private final ContadorFrame vista;
    private final ContadorDAO dao;
    private static final int LIMITE_ACTUALIZACIONES = 2;

    public ContadorController(ContadorFrame vista, ContadorDAO dao) {
        this.vista = vista;
        this.dao = dao;

        // Listeners de los botones
        this.vista.btnGuardar.addActionListener(e -> gestionarCreacion());
        this.vista.btnActualizar.addActionListener(e -> gestionarActualizacion());
        this.vista.btnEliminar.addActionListener(e -> gestionarEliminacion());
        this.vista.btnRefrescar.addActionListener(e -> cargarDatosATabla());

        // Evento: Al hacer clic en la tabla, llenar los campos
        this.vista.tablaContadores.getSelectionModel().addListSelectionListener(e -> {
            int fila = vista.tablaContadores.getSelectedRow();
            if (fila != -1) {
                vista.txtId.setText(vista.tablaContadores.getValueAt(fila, 0).toString());
                vista.txtUbi.setText(vista.tablaContadores.getValueAt(fila, 1).toString());
                vista.txtLectura.setText(vista.tablaContadores.getValueAt(fila, 2).toString());
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

    // CREATE: Guardar nuevo registro
    private void gestionarCreacion() {
        try {
            String id = vista.txtId.getText();
            String ubi = vista.txtUbi.getText();
            double lec = Double.parseDouble(vista.txtLectura.getText());

            if (dao.buscarPorId(id).isPresent()) throw new Exception("El ID ya existe.");

            dao.guardar(new ContadorAgua(id, ubi, lec, 0));
            cargarDatosATabla();
            limpiarCampos();
            JOptionPane.showMessageDialog(vista, "¡Guardado con éxito!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al crear: " + ex.getMessage());
        }
    }

    // UPDATE: Actualizar ubicación
    private void gestionarActualizacion() {
        String id = vista.txtId.getText();
        ContadorAgua contador = null;
        try {
            contador = dao.buscarPorId(id).orElseThrow(() -> new Exception("ID no encontrado."));
            
            if (contador.getActualizaciones() >= LIMITE_ACTUALIZACIONES) 
                throw new IllegalStateException("Se ha alcanzado el límite de 2 cambios.");

            contador.setUbicacion(vista.txtUbi.getText());
            contador.incrementarActualizacion();
            dao.guardar(contador);
            
            cargarDatosATabla();
            JOptionPane.showMessageDialog(vista, "Ubicación actualizada.");
        } catch (Exception ex) {
            FileLogger.logException("Error en Actualización", ex, (contador != null ? contador : id));
            JOptionPane.showMessageDialog(vista, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // DELETE: Eliminar registro
    private void gestionarEliminacion() {
        try {
            String id = vista.txtId.getText();
            int confirm = JOptionPane.showConfirmDialog(vista, "¿Seguro que desea eliminar el ID " + id + "?");
            if (confirm == JOptionPane.YES_OPTION) {
                dao.eliminar(id);
                cargarDatosATabla();
                limpiarCampos();
                JOptionPane.showMessageDialog(vista, "Registro eliminado.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al eliminar: " + ex.getMessage());
        }
    }

    private void limpiarCampos() {
        vista.txtId.setText("");
        vista.txtUbi.setText("");
        vista.txtLectura.setText("");
    }
}