package com.mycompany.dao.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ContadorFrame extends JFrame {
    public JTextField txtId = new JTextField(10);
    public JTextField txtNuevaUbi = new JTextField(10);
    public JButton btnActualizar = new JButton("Actualizar");
    public JButton btnRefrescar = new JButton("Cargar/Ver Lista");
    
    // Componentes para la Tabla
    public DefaultTableModel modeloTabla = new DefaultTableModel(
        new Object[]{"ID", "Ubicación", "Lectura", "Cambios"}, 0
    );
    public JTable tablaContadores = new JTable(modeloTabla);

    public ContadorFrame() {
        super("Gestor de Contadores v2.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel Superior: Formulario
        JPanel pnlForm = new JPanel();
        pnlForm.add(new JLabel("ID:")); pnlForm.add(txtId);
        pnlForm.add(new JLabel("Nueva Ubi:")); pnlForm.add(txtNuevaUbi);
        pnlForm.add(btnActualizar);
        pnlForm.add(btnRefrescar);

        // Panel Central: La Tabla
        add(pnlForm, BorderLayout.NORTH);
        add(new JScrollPane(tablaContadores), BorderLayout.CENTER);

        setSize(600, 400);
        setLocationRelativeTo(null);
    }
}