package com.mycompany.dao.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ContadorFrame extends JFrame {
    public JTextField txtId = new JTextField(8);
    public JTextField txtUbi = new JTextField(10);
    public JTextField txtLectura = new JTextField(5); // Para nuevos registros

    public JButton btnGuardar = new JButton("Guardar Nuevo");
    public JButton btnActualizar = new JButton("Actualizar");
    public JButton btnEliminar = new JButton("Eliminar");
    public JButton btnRefrescar = new JButton("Refrescar");

    public DefaultTableModel modeloTabla = new DefaultTableModel(
        new Object[]{"ID", "Ubicación", "Lectura", "Cambios"}, 0
    );
    public JTable tablaContadores = new JTable(modeloTabla);

    public ContadorFrame() {
        super("Gestión de Contadores - MVC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Formulario
        JPanel pnlNorte = new JPanel(new FlowLayout());
        pnlNorte.add(new JLabel("ID:")); pnlNorte.add(txtId);
        pnlNorte.add(new JLabel("Ubi:")); pnlNorte.add(txtUbi);
        pnlNorte.add(new JLabel("Lectura:")); pnlNorte.add(txtLectura);
        
        // Botones
        JPanel pnlBotones = new JPanel(new FlowLayout());
        pnlBotones.add(btnGuardar);
        pnlBotones.add(btnActualizar);
        pnlBotones.add(btnEliminar);
        pnlBotones.add(btnRefrescar);

        JPanel pnlSuperior = new JPanel(new GridLayout(2, 1));
        pnlSuperior.add(pnlNorte);
        pnlSuperior.add(pnlBotones);

        add(pnlSuperior, BorderLayout.NORTH);
        add(new JScrollPane(tablaContadores), BorderLayout.CENTER);

        setSize(750, 450);
        setLocationRelativeTo(null);
    }
}