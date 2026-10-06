package com.speedfast.dao;

import com.speedfast.connection.ConexionDB;
import com.speedfast.model.Entrega;
import com.speedfast.model.Repartidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntregaDAO {
    /**
     * Metodo para crear una Entrega
     */
    public void create(Entrega entrega){
        /**
         * Orden sql para crear una entrega en la BD
         */
        String sql = "INSERT INTO entregas(id_pedido, id_repartidor, fecha, hora)VALUES(?, ?, ?, ?)";

        /**
         * Conexión con la BD
         * Try-catch para manejo de excepciones y cierre de la conexión de forma automática al final
         */
        try (Connection conexion = ConexionDB.getConnection();
             /**
              * Se prepara la orden para ser ejecutada
              */
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            /**
             * Se asigna una posición al id_pedido, id_repartidor, fecha y hora de la entrega
             */
            stmt.setInt(1, entrega.getIdPedido());
            stmt.setInt(2, entrega.getIdRepartidor());
            stmt.setString(3, entrega.getFecha());
            stmt.setString(4, entrega.getHora());

            /**
             * Se ejecuta la orden
             */
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al crear la entrega.", e);
        }
    }

    /**
     * Metodo pára leer todas las Entregas registradas
     */
    public List<Entrega> readAll(){
        /**
         * Se crea una lista
         */
        List<Entrega> entregas = new ArrayList<>();

        /**
         * Orden que obtiene todas las entregas
         */
        String sql = "SELECT * FROM entregas";

        /**
         * Conexión con la base de datos
         */
        try (Connection conexion = ConexionDB.getConnection();
             /**
              * Se prepara la orden para ser ejecutada
              */
             PreparedStatement stmt = conexion.prepareStatement(sql);
             /**
              * Se ejecuta la orden sql
              */
             ResultSet rs = stmt.executeQuery()) {

            /**
             * Mientras existan datos
             */
            while (rs.next()) {
                /**
                 * Se obtienen id, id_pedido, idRepartidor, fecha y hora de la BD
                 */
                int id = rs.getInt("id");
                int idPedido = rs.getInt("id_pedido");
                int idRepartidor = rs.getInt("id_repartidor");
                String fecha = rs.getString("fecha");
                String hora = rs.getString("hora");

                /**
                 * Se crea una instancia de Entrega, le pasamos el "id", idPedido, idRepartidor, fecha y hora
                 */
                Entrega entrega = new Entrega(id, idPedido, idRepartidor, fecha, hora);

                /**
                 * Se agrega la instancia a la lista
                 */
                entregas.add(entrega);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar entregas.", e);
        }
        /**
         * Devuelve la lista
         */
        return entregas;
    }

    /**
     * Metodo para modificar una Entrega
     */
    public void update(Entrega entrega){
        /**
         * Orden que modifica una entrega según el "id" indicado, con un marcador de posición (?) para cada valor
         */
        String sql = "UPDATE entregas SET id_pedido = ?, id_repartidor = ?, fecha = ?, hora = ? WHERE id = ?";

        /**
         * Se obtiene la conexión con la base de datos
         */
        try (Connection conexion = ConexionDB.getConnection();
             /**
              * Se prepara la orden para ser ejecutada
              */
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            /**
             * Se asigna una posición al id_pedido, id_repartidor, fecha, hora y id de la entrega
             */
            stmt.setInt(1, entrega.getIdPedido());
            stmt.setInt(2, entrega.getIdRepartidor());
            stmt.setString(3, entrega.getFecha());
            stmt.setString(4, entrega.getHora());
            stmt.setInt(5, entrega.getId());

            /**
             * Se ejecuta la orden
             */
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la entrega.", e);
        }
    }

    /**
     * Metodo para eliminar una Entrega
     * Recibe el "id" de la entrega
     */
    public void delete(int id){
        /**
         * Instrucción que elimina una entrega según el "id" indicado
         */
        String sql = "DELETE FROM entregas WHERE id = ?";

        /**
         * Conexión con la base de datos
         */
        try (Connection conexion = ConexionDB.getConnection();
             /**
              * Se prepara la ejecución de la orden
              */
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            /**
             * Se le asigna la posición al id de la entrega
             */
            stmt.setInt(1, id);
            /**
             * Se ejecuta la orden
             */
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la entrega.", e);
        }
    }
}
