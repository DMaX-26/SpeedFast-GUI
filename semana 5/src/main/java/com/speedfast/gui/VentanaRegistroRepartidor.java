package com.speedfast.gui;

import com.speedfast.dao.RepartidorDAO;
import com.speedfast.model.Repartidor;

import javax.swing.*;

public class VentanaRegistroRepartidor extends JFrame {
    private JPanel Registro;
    private JTextField txtNombre;
    private JLabel lblRegistro;
    private JButton btnGuardar;
    private JLabel lblNombre;
    private Repartidor editarRepartidor;


    /**
     * Constructor de la ventana
     */
    public VentanaRegistroRepartidor() {
        setTitle("Registrar Repartidor");
        setContentPane(Registro);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setResizable(false);

        btnGuardar.addActionListener(e -> registrarRepartidor());
    }

    /**
     * Constructor que permite abrir la ventana para editar un repartidor
     * @param repartidor
     */
    public VentanaRegistroRepartidor(Repartidor repartidor) {
        editarRepartidor = repartidor;

        setTitle("Editar Repartidor");
        setContentPane(Registro);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setResizable(false);

        txtNombre.setText(repartidor.getNombre());
        btnGuardar.addActionListener(e -> registrarRepartidor());
    }

    /**
     * Metodo para registrar un repartidor
     */
    private void registrarRepartidor() {
        /**
         *  Se obtiene el nombre del repartidor
         */
        String nombre = txtNombre.getText().trim();

        /**
         * Se valida el ingreso del nombre
         */
        if (nombre.isBlank()) {
            JOptionPane.showMessageDialog(this, "Debes ingresar el nombre.");
            return;
        }

        /**
         * Se crea el DAO
         */
        RepartidorDAO repartidorDAO = new RepartidorDAO();

        /**
         * Si editarRepartidor es null, significa que estamos registrando
         */
        if (editarRepartidor == null) {
            /**
             * Se crea un nuevo repartidor
             */
            Repartidor repartidor = new Repartidor(0, nombre);
            /**
             * Se guarda el repartidor en la BD
             */
            repartidorDAO.create(repartidor);

            JOptionPane.showMessageDialog(this, "Repartidor registrado correctamente.");

            /**
             * Si no, es porque estamos editando
             */
        } else {
            /**
             * Se modifica el nombre del repartidor seleccionado
             */
            editarRepartidor.setNombre(nombre);
            /**
             * Se actualiza el repartidor en la BD
             */
            repartidorDAO.update(editarRepartidor);

            JOptionPane.showMessageDialog(this, "Repartidor actualizado correctamente.");
        }
        /**
         * Se cierra la ventana
         */
        dispose();
    }
}
