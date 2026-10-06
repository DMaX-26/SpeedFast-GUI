package com.speedfast.gui;

import com.speedfast.dao.RepartidorDAO;
import com.speedfast.model.Repartidor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class VentanaListaRepartidores extends JFrame {
    private JPanel Listado;
    private JTable tblRepartidores;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JLabel lblLista;
    private JLabel lblTitulo;
    private JButton btnActualizar;

    /**
     * Constructor de la ventana
     */
    public VentanaListaRepartidores() {
        setTitle("Registrar Repartidor");
        setContentPane(Listado);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 250);
        setLocationRelativeTo(null);
        setResizable(false);

        btnEditar.addActionListener(e -> editarRepartidor());
        btnEliminar.addActionListener(e -> eliminarRepartidor());
        btnActualizar.addActionListener(e -> cargarRepartidores());

        cargarRepartidores();
    }

    /**
     * Metodo para insertar repartidores en el JTable
     */
    public void cargarRepartidores(){
        /**
         * Instancia del DAO
         */
        RepartidorDAO repartidorDAO = new RepartidorDAO();

        /**
         * El DAO consulta todos los repartidores registrados en la BD
         */
        List<Repartidor> repartidores = repartidorDAO.readAll();

        /**
         * Se crean las columnas
         */
        String[] columnas = {"ID", "Nombre"};

        /**
         * Se crea un model que recibe las columnas creadas
         */
        DefaultTableModel model = new DefaultTableModel(columnas, 0);

        for (Repartidor r : repartidores){
            /**
             * Se crea una fila con los datos del repartidor
             */
            Object[] fila = {
                    r.getIdRepartidor(),
                    r.getNombre(),
            };
            /**
             * Se agrega la fila con los datos al model
             */
            model.addRow(fila);
        }
        /**
         * Se agrega el model al "tblRepatidores" para que muestre los datos en la tabla
         */
        tblRepartidores.setModel(model);
    }

    public void editarRepartidor(){
        /**
         * Se obtiene la fila de la tabla
         */
        int fila = tblRepartidores.getSelectedRow();

        /**
         * Mensaje informativo si no se ha seleccionado una fila
         */
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un repartidor!");
            return;
        }

        /**
         * Se obtiene el id de la fila
         */
        int id = (int) tblRepartidores.getValueAt(fila, 0);

        /**
         * Instancia del DAO
         */
        RepartidorDAO repartidorDAO = new RepartidorDAO();

        /**
         * El DAO consulta todos los repartidores registrados en la BD
         */
        List<Repartidor> repartidores = repartidorDAO.readAll();

        /**
         * Se recorre la lista
         */
        for (Repartidor repartidor : repartidores) {
            /**
             * Si idRepartidor coincide con el "id "seleccionado de la tabla
             */
            if (repartidor.getIdRepartidor() == id) {
                /**
                 * Se abre la ventana de registro para editar el repartidor seleccionado
                 */
                VentanaRegistroRepartidor ventana = new VentanaRegistroRepartidor(repartidor);
                ventana.setVisible(true);
                break;
            }
        }
    }

    /**
     * Metodo para eliminar un repartidor
     * Se obtiene la fila, se valída, se obtiene el id de la fila, se elimina de la BD, se actualiza la tabla.
     */
    public void eliminarRepartidor(){
        int fila = tblRepartidores.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un repartidor!");
            return;
        }
        int id = (int) tblRepartidores.getValueAt(fila, 0);

        RepartidorDAO repartidorDAO = new RepartidorDAO();
        repartidorDAO.delete(id);

        JOptionPane.showMessageDialog(this, "Repartidor eliminado correctamente.");
        cargarRepartidores();
    }
}
