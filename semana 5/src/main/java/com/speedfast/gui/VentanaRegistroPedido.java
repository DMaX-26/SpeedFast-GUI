package com.speedfast.gui;

import com.speedfast.dao.PedidoDAO;
import com.speedfast.model.*;

import javax.swing.*;
import java.util.List;

public class VentanaRegistroPedido extends JFrame {
    private JPanel Registro;
    private JLabel lblDireccion;
    private JLabel lblTipo;
    private JTextField txtDireccion;
    private JComboBox cmbTipo;
    private JLabel lblTitulo;
    private JLabel lblEstado;
    private JButton btnGuardar;
    private JLabel lblRegistros;
    private JTable tblPedidos;
    private JComboBox cmbEstado;
    List<Pedido> pedidos;
    private Pedido editarPedido;

    /**
     * Constructor para registrar un pedido
     */
    public VentanaRegistroPedido() {
        setTitle("Registrar Pedido"); //título de la ventana
        setContentPane(Registro); //Contenedor de la ventana Registro
        setDefaultCloseOperation(EXIT_ON_CLOSE); //cerrar al presionar la "x"
        setSize(550, 350); //ancho por alto de la ventana
        setLocationRelativeTo(null); //ubicación al centro de la pantalla
        setResizable(false); //No se puede redimensionar

        //Se añaden los ítems al combobox Tipo
        cmbTipo.addItem("COMIDA");
        cmbTipo.addItem("ENCOMIENDA");
        cmbTipo.addItem("EXPRESS");

        //Se añaden los ítems al combobox Estado
        cmbEstado.addItem("PENDIENTE");
        cmbEstado.addItem("EN_REPARTO");
        cmbEstado.addItem("ENTREGADO");

        //Se agrega la acción registrarPedido al botón Guardar
        btnGuardar.addActionListener(e -> registrarPedido());
    }

    /**
     * Constructor que abre la ventana para editar un pedido
     * @param pedido
     */
    public VentanaRegistroPedido(Pedido pedido) {
        this.editarPedido = pedido;

        setTitle("Editar Pedido");
        setContentPane(Registro);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(550, 350);
        setLocationRelativeTo(null);
        setResizable(false);

        cmbTipo.addItem("COMIDA");
        cmbTipo.addItem("ENCOMIENDA");
        cmbTipo.addItem("EXPRESS");

        cmbEstado.addItem("PENDIENTE");
        cmbEstado.addItem("EN_REPARTO");
        cmbEstado.addItem("ENTREGADO");

        txtDireccion.setText(pedido.getDireccionEntrega());
        cmbTipo.setSelectedItem(pedido.getTipoPedido());
        cmbEstado.setSelectedItem(pedido.getEstado().name());

        //Se agrega la acción editarPedido al botón Guardar
        btnGuardar.addActionListener(e -> editarPedido());
    }

    /**
     * Metodo que limpia cada componente del formulario
     */
    private void limpiarFormulario(){
        txtDireccion.setText("");
        cmbTipo.setSelectedIndex(0);
        cmbEstado.setSelectedIndex(0);
    }

    /**
     * Metodo que registra un pedido en la interfaz y lo envía al DAO para que se registre en la BD
     */
    public void registrarPedido(){
        String direccion = txtDireccion.getText().trim(); // Se obtiene la "direccion" del campo de texto eliminando espacios en blanco al inicio y al final
        String tipo = (String) cmbTipo.getSelectedItem(); // Se obtiene el ítem seleccionado del combobox tipo, se convierte a String y se guarda en una variable
        String estado = (String) cmbEstado.getSelectedItem();; // Se obtiene el ítem seleccionado del combobox estado, se convierte a String y se guarda en una variable

        if (direccion.isBlank()){
            JOptionPane.showMessageDialog(this, "Debes ingresar la Dirección");
            return;//Se corta la ejecución del metodo
        }
        if (tipo.isBlank()){
            JOptionPane.showMessageDialog(this, "Debes seleccionar un tipo de pedido");
            return;//Se corta la ejecución del metodo
        }
        if (estado.isBlank()){
            JOptionPane.showMessageDialog(this, "Debes seleccionar un tipo de pedido");
            return;//Se corta la ejecución del metodo
        }

        /**
         * Variable donde se guardarán los pedidos
         */
        Pedido pedido;

        /**
         * Switch donde validamos el tipo de pedido seleccionado
         */
        switch (tipo){
            case "COMIDA":
                pedido = new PedidoComida(tipo, direccion, 0); // Se crea una instancia de PedidoComida y le pasamos los datos
                /**
                 * Se asigna el estado al pedido
                 */
                pedido.setEstado(EstadoPedido.valueOf(estado));
                /**
                 * Se crea una instancia de PedidoDAO
                 */
                PedidoDAO pedidoDAO = new PedidoDAO();
                /**
                 * Le pasamos pedidoComida para que mediante el metodo "create" l registre en la BD
                 */
                pedidoDAO.create(pedido);
                break; // Se corta la ejecución del metodo
            case "ENCOMIENDA":
                pedido = new PedidoEncomienda(tipo, direccion, 0); // Se crea una instancia de PedidoEncomienda y le pasamos los datos
                pedido.setEstado(EstadoPedido.valueOf(estado));
                PedidoDAO pedidoDAO1 = new PedidoDAO();
                /**
                 * Le pasamos pedidoEncomienda para que mediante el metodo "create" lo registre en la BD
                 */
                pedidoDAO1.create(pedido);
                break; // Se corta la ejecución del metodo
            case "EXPRESS":
                pedido = new PedidoExpress(tipo, direccion, 0);
                pedido.setEstado(EstadoPedido.valueOf(estado));
                PedidoDAO pedidoDAO2 = new PedidoDAO();
                /**
                 * Le pasamos pedidoExpress para que mediante el metodo "create" lo registre en la BD
                 */
                pedidoDAO2.create(pedido);
                break; // Se corta la ejecución del metodo
            default:
                JOptionPane.showMessageDialog(this, "Tipo de pedido no válido");
                return;
        }
        JOptionPane.showMessageDialog(this, "Pedido registrado correctamente!");

        limpiarFormulario(); // llamado al metodo que limpia el formulario
    }

    /**
     * Metodo que edita un pedido de la interfaz o lo envía al DAO para que se modifique en la BD
     */
    public void editarPedido() {
        /**
         * Se obtienen los datos
         */
        String direccion = txtDireccion.getText().trim();
        String tipo = (String) cmbTipo.getSelectedItem();
        String estado = (String) cmbEstado.getSelectedItem();

        /**
         * Se valída la dirección
         */
        if (direccion.isBlank()) {
            JOptionPane.showMessageDialog(this, "Debes ingresar la Dirección");
            return;
        }

        /**
         * Le pasamos la direccion, tipo y estado a editarPedido
         */
        editarPedido.setDireccionEntrega(direccion);
        editarPedido.setTipoPedido(tipo);
        editarPedido.setEstado(EstadoPedido.valueOf(estado));

        /**
         * Instancia del pedidoDAO
         */
        PedidoDAO pedidoDAO = new PedidoDAO();
        /**
         * Mandamos el pedido modificado al DAO para que se visualice en la BD
         */
        pedidoDAO.update(editarPedido);

        JOptionPane.showMessageDialog(this, "Pedido actualizado correctamente.");
        /**
         * Cierra la ventana
         */
        dispose();
    }
}
