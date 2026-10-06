package com.speedfast.gui;

import com.speedfast.dao.EntregaDAO;
import com.speedfast.model.Entrega;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class VentanaListaEntregas extends JFrame {
    private JPanel Lista;
    private JButton btnEditar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JTable tblEntregas;
    private JLabel lblTitulo;

    public VentanaListaEntregas() {
        setTitle("Lista de entregas");
        setContentPane(Lista);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(700, 350);
        setLocationRelativeTo(null);
        setResizable(false);

        /**
         * Acciones de los botones
         */
        btnEditar.addActionListener(e -> editarEntrega());
        btnEliminar.addActionListener(e -> eliminarEntrega());
        btnActualizar.addActionListener(e -> cargarEntregas());
    }

    /**
     * Metodo que obtiene las entregas desde la BD
     * y las muestra en la tabla
     */
    private void cargarEntregas() {

        /**
         * Se crea una instancia del DAO
         */
        EntregaDAO entregaDAO = new EntregaDAO();

        /**
         * Se obtienen todas las entregas
         */
        List<Entrega> entregas = entregaDAO.readAll();

        /**
         * Se crean las columnas
         */
        String[] columnas = {"ID", "Pedido", "Repartidor", "Fecha", "Hora"};

        /**
         * Se crea el modelo de la tabla
         */
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        /**
         * Se recorren las entregas
         */
        for (Entrega entrega : entregas) {

            /**
             * Se crea una fila con los datos
             */
            Object[] fila = {
                    entrega.getId(),
                    entrega.getIdPedido(),
                    entrega.getIdRepartidor(),
                    entrega.getFecha(),
                    entrega.getHora()
            };

            /**
             * Se agrega la fila al modelo
             */
            modelo.addRow(fila);
        }
        /**
         * Se agrega el modelo a la tabla
         */
        tblEntregas.setModel(modelo);
    }


    /**
     * Metodo para editar una entrega
     */
    private void editarEntrega() {

        /**
         * Se obtiene la fila seleccionada
         */
        int fila = tblEntregas.getSelectedRow();

        /**
         * Se valida que exista una fila seleccionada
         */
        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                    "Debes seleccionar una entrega.");
            return;
        }

        /**
         * Se obtiene el ID de la entrega
         */
        int id = (int) tblEntregas.getValueAt(fila, 0);

        /**
         * Se obtiene la lista de entregas
         */
        EntregaDAO entregaDAO = new EntregaDAO();
        List<Entrega> entregas = entregaDAO.readAll();

        /**
         * Se busca la entrega seleccionada
         */
        for (Entrega entrega : entregas) {
            if (entrega.getId() == id) {
                /**
                 * Se abre la ventana de registro
                 * para editar la entrega
                 */
                VentanaRegistroEntrega ventana = new VentanaRegistroEntrega(entrega);
                ventana.setVisible(true);
                break;
            }
        }
    }

    /**
     * Metodo para eliminar una entrega
     */
    private void eliminarEntrega() {

        /**
         * Se obtiene la fila seleccionada
         */
        int fila = tblEntregas.getSelectedRow();

        /**
         * Se valida que exista una fila seleccionada
         */
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar una entrega.");
            return;
        }

        /**
         * Se obtiene el ID de la entrega
         */
        int id = (int) tblEntregas.getValueAt(fila, 0);

        /**
         * Se crea el DAO
         */
        EntregaDAO entregaDAO = new EntregaDAO();

        /**
         * Se elimina la entrega
         */
        entregaDAO.delete(id);

        JOptionPane.showMessageDialog(this, "Entrega eliminada correctamente.");
        /**
         * Se actualiza la tabla
         */
        cargarEntregas();
    }
}
