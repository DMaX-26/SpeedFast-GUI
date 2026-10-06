package com.speedfast.gui;

import com.speedfast.dao.EntregaDAO;
import com.speedfast.dao.PedidoDAO;
import com.speedfast.dao.RepartidorDAO;
import com.speedfast.model.Entrega;
import com.speedfast.model.Pedido;
import com.speedfast.model.Repartidor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class VentanaRegistroEntrega extends JFrame {
    private JPanel Entrega;
    private JComboBox cmbPedido;
    private JComboBox cmbRepartidor;
    private JTextField txtFecha;
    private JTextField txtHora;
    private JLabel lblRegistros;
    private JLabel lblIdEntrega;
    private JLabel lblFecha;
    private JLabel lblHora;
    private JButton btnGuardar;
    private Entrega editarEntrega;


    /**
     * Constructor para registrar una entrega
     */
    public VentanaRegistroEntrega() {
        setTitle("Registrar Entrega");
        setContentPane(Entrega);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(700, 250);
        setLocationRelativeTo(null);
        setResizable(false);

        /**
         * Acción del botón Guardar
         */
        btnGuardar.addActionListener(e -> registrarEntrega());
        /**
         * Carga los pedidos y repartidores en los combobox
         */
        cargarCmbs();
    }

    /**
     * Constructor para editar una entrega
     */
    public VentanaRegistroEntrega(Entrega entrega) {
        editarEntrega = entrega;

        setTitle("Editar Entrega");
        setContentPane(Entrega);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setResizable(false);

        /**
         * Carga los pedidos y repartidores al combobox
         */
        cargarCmbs();

        /**
         * Se muestran la fecha y hora actuales
         */
        txtFecha.setText(entrega.getFecha());
        txtHora.setText(entrega.getHora());

        /**
         * Acción del botón Guardar
         */
        btnGuardar.addActionListener(e -> registrarEntrega());
    }

    private void registrarEntrega() {
        /**
         * Se obtiene el pedido seleccionado del combobox
         */
        Pedido pedidoSeleccionado = (Pedido) cmbPedido.getSelectedItem();

        /**
         * Se obtiene el repartidor seleccionado del combobox
         */
        Repartidor repartidorSeleccionado = (Repartidor) cmbRepartidor.getSelectedItem();

        /**
         * Se obtiene la fecha y hora de las cajas de texto
         */
        String fecha = txtFecha.getText().trim();
        String hora = txtHora.getText().trim();

        /**
         * Se validan los datos
         */
        if (pedidoSeleccionado == null) {
            JOptionPane.showMessageDialog(this,
                    "Debes seleccionar un pedido.");
            return;
        }
        if (repartidorSeleccionado == null) {
            JOptionPane.showMessageDialog(this,
                    "Debes seleccionar un repartidor.");
            return;
        }
        if (fecha.isBlank()) {
            JOptionPane.showMessageDialog(this,
                    "Debes ingresar la fecha.");
            return;
        }
        if (hora.isBlank()) {
            JOptionPane.showMessageDialog(this,
                    "Debes ingresar la hora.");
            return;
        }

        /**
         * Se crea el DAO
         */
        EntregaDAO entregaDAO = new EntregaDAO();

        /**
         * Si editarEntrega es null, se registra una nueva entrega
         */
        if (editarEntrega == null) {
            Entrega entrega = new Entrega(pedidoSeleccionado.getIdPedido(), repartidorSeleccionado.getIdRepartidor(), fecha, hora);

            entregaDAO.create(entrega);

            JOptionPane.showMessageDialog(this, "Entrega registrada correctamente!");

            /**
             * Si no, se edita la entrega
             */
        } else {

            /**
             * Se modifican los datos de la entrega
             */
            editarEntrega.setFecha(fecha);
            editarEntrega.setHora(hora);

            /**
             * Se actualiza la entrega en la BD
             */
            entregaDAO.update(editarEntrega);

            JOptionPane.showMessageDialog(this, "Entrega actualizada correctamente!");
        }
        /**
         * Se cierra la ventana
         */
        dispose();
    }

    /**
     * Metodo que carga los pedidos y repartidores desde la BD al combobox
     */
    private void cargarCmbs() {
        /**
         * Instancia de PedidoDAO
         */
        PedidoDAO pedidoDAO = new PedidoDAO();
        /**
         * El DAO consulta todos los pedidos registrados en la BD
         */
        List<Pedido> pedidos = pedidoDAO.readAll();

        /**
         * Se recorre la lista "pedido" y cada pedido encontrado se agrega al combobox
         */
        for (Pedido pedido : pedidos) {
            cmbPedido.addItem(pedido);
        }
        RepartidorDAO repartidorDAO = new RepartidorDAO();
        List<Repartidor> repartidores = repartidorDAO.readAll();

        for (Repartidor repartidor : repartidores) {
            cmbRepartidor.addItem(repartidor);
        }
    }
}
