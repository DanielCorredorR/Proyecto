package com.mycompany.dao.controller;

import com.mycompany.dao.dao.ContadorDAO;
import com.mycompany.dao.modelo.ContadorAgua;
import com.mycompany.dao.view.ContadorFrame;
import com.mycompany.dao.util.FileLogger;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * CAPA: CONTROLADOR (MVC)
 * Coordina la interacción entre la interfaz gráfica y la persistencia.
 */
public class ContadorController {
    private final ContadorFrame vista;
    private final ContadorDAO dao;
    private static final int LIMITE_CAMBIOS = 2; // Restricción #3

    public ContadorController(ContadorFrame vista, ContadorDAO dao) {
        this.vista = vista;
        this.dao = dao;

        // ASIGNACIÓN DE EVENTOS (Listeners)
        this.vista.btnActualizar.addActionListener(e -> gestionarActualizacion());
        this.vista.btnRefrescar.addActionListener(e -> cargarDatosATabla());
        
        // UX: Al hacer clic en la tabla, el ID se pone en el campo de texto
        this.vista.tablaContadores.getSelectionModel().addListSelectionListener(e -> {
            int fila = vista.tablaContadores.getSelectedRow();
            if (fila != -1) {
                String idSeleccionado = vista.tablaContadores.getValueAt(fila, 0).toString();
                vista.txtId.setText(idSeleccionado);
            }
        });

        // Carga inicial de datos
        cargarDatosATabla();
    }

    /**
     * Carga todos los contadores desde el archivo TXT a la JTable
     */
    private void cargarDatosATabla() {
        try {
            vista.modeloTabla.setRowCount(0); // Limpiar tabla
            List<ContadorAgua> lista = dao.listarTodos();
            for (ContadorAgua c : lista) {
                vista.modeloTabla.addRow(new Object[]{
                    c.getIdContador(), c.getUbicacion(), c.getLecturaActual(), c.getActualizaciones()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al leer datos: " + ex.getMessage());
        }
    }

    /**
     * Aplica la lógica de negocio y las Restricciones #2, #3 y #4
     */
    private void gestionarActualizacion() {
        String id = vista.txtId.getText();
        String nuevaUbi = vista.txtNuevaUbi.getText();
        ContadorAgua contador = null;

        try {
            // RESTRICCIÓN #2: Validar campos vacíos
            if (nuevaUbi == null || nuevaUbi.isBlank()) {
                throw new IllegalArgumentException("La ubicación no puede estar vacía.");
            }

            // Buscar el contador en el archivo
            contador = dao.buscarPorId(id)
                    .orElseThrow(() -> new Exception("Contador con ID '" + id + "' no encontrado."));

            // RESTRICCIÓN #3: Límite de 2 actualizaciones máximo
            if (contador.getActualizaciones() >= LIMITE_CAMBIOS) {
                throw new IllegalStateException("Límite de cambios (2) alcanzado para este contador.");
            }

            // Aplicar cambios si todo es correcto
            contador.setUbicacion(nuevaUbi);
            contador.incrementarActualizacion();
            dao.guardar(contador);

            // Refrescar la vista
            cargarDatosATabla();
            JOptionPane.showMessageDialog(vista, "Ubicación actualizada correctamente.");
            vista.txtNuevaUbi.setText(""); // Limpiar campo

        } catch (Exception ex) {
            // RESTRICCIÓN #4: Log de error con timestamp de milisegundos
            FileLogger.logException("Error en actualización GUI", ex, (contador != null ? contador : "ID: " + id));
            
            JOptionPane.showMessageDialog(vista, "ERROR: " + ex.getMessage(), "Fallo en operación", JOptionPane.ERROR_MESSAGE);
        }
    }
}