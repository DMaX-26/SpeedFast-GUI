package com.speedfast.gui;

import com.speedfast.dao.PedidoDAO;
import com.speedfast.model.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class VentanaListaPedidos extends JFrame {
    private JPanel Lista;
    private JTable tblPedidos;
    private JLabel lblTitulo;
    private JLabel lblSubtitulo;
    private JButton btnComida;
    private JButton btnEncomienda;
    private JButton btnExpress;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnActualizar;

    public VentanaListaPedidos() {
        setTitle("Listado de pedidos"); //título de la ventana
        setContentPane(Lista); //Contenedor de la ventana Lista
        setDefaultCloseOperation(EXIT_ON_CLOSE); //cerrar al presionar la "x"
        setSize(950, 350); //ancho por alto de la ventana
        setLocationRelativeTo(null); //ubicación al centro de la pantalla
        setResizable(false); //No se puede redimensionar

        /**
         * Acciones de cada botón
         */
        btnEditar.addActionListener(e -> editarPedido());
        btnEliminar.addActionListener(e -> eliminarPedido());
        btnActualizar.addActionListener(e -> cargarPedidos());

        /**
         * Metodo que carga los pedidos en la tabla
         */
        cargarPedidos();
    }

    /**
     * Metodo que obtiene los pedidos desde la BD y los muestra en la tabla.
     */
    private void cargarPedidos() {
        /**
         * Se crea una instancia de PedidoDAO
         */
        PedidoDAO pedidoDAO = new PedidoDAO();

        /**
         * Se listan todos los pedidos y se guardan en una variable
         */
        List<Pedido> pedidos = pedidoDAO.readAll();

        /**
         * Se crean las columnas
         */
        String[] columnas = {"ID", "Dirección", "Tipo", "Estado"};

        /**
         * Se crea el modelo con las columnas creadas y con 0 filas iniciales
         */
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        /**
         * Se recorre la lista de pedidos
         */
        for (Pedido pedido : pedidos) {
            /**
             * Se crea una fila con los datos del pedido
             */
            Object[] fila = {
                    pedido.getIdPedido(),
                    pedido.getDireccionEntrega(),
                    pedido.getTipoPedido(),
                    pedido.getEstado()
            };
            /**
             * Se agrega la fila al model
             */
            modelo.addRow(fila);
        }
        /**
         * Le pasamos el modelo al JTable "tblPedidos"
         */
        tblPedidos.setModel(modelo);
    }

    /**
     * Metodo para editar un pedido desde la interfaz
     */
    public void editarPedido(){
        /**
         * Se obtiene la fila seleccioanda de la tabla
         */
        int fila = tblPedidos.getSelectedRow();

        /**
         * Si no se selecciona una fila, se abre una ventana con un mensaje
         */
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Selecciona un pedido.");
            return;
        }

        /**
         * Se obtiene el id de la fila
         */
        int id = (int) tblPedidos.getValueAt(fila, 0);

        /**
         * Se crea una instancia de pedidoDAO
         */
        PedidoDAO pedidoDAO = new PedidoDAO();

        /**
         * Se obtiene la lista de pedidos
         */
        List<Pedido> pedidos = pedidoDAO.readAll();

        /**
         * Se recorre la lista
         */
        for (Pedido pedido : pedidos) {
            /**
             * Si el id del pedido es igual al id de la fila
             */
            if (pedido.getIdPedido() == id) {
                /**
                 * Se abre la ventana de registro de pedidos para modificar el pedido
                 */
                VentanaRegistroPedido ventana = new VentanaRegistroPedido(pedido);
                ventana.setVisible(true);
                break;
            }
        }
    }

    /**
     * Metodo para editar un pedido desde la interfaz
     */
    public void eliminarPedido(){
        /**
         * Se obtiene la fila seleccionada
         */
        int fila = tblPedidos.getSelectedRow();

        /**
         * Si no se selecciona una fila, se abre una ventana con un mensaje
         */
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Selecciona un pedido.");
            return;
        }
        /**
         * Se obtiene el id de la fila
         */
        int id = (int) tblPedidos.getValueAt(fila, 0);

        /**
         * Se crea una instancia de pedidoDAO
         */
        PedidoDAO pedidoDAO = new PedidoDAO();
        /**
         * Se manda el "id" al metodo "delete" del DAO para que elimine el pedido de la BD
         */
        pedidoDAO.delete(id);

        JOptionPane.showMessageDialog(null, "Pedido eliminado correctamente.");
        /**
         * Se vuelven a cargar los pedidos
         */
        cargarPedidos();
    }
}
