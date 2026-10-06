package com.speedfast.dao;

import com.speedfast.connection.ConexionDB;
import com.speedfast.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    /**
     * Metodo para crear un Pedido
     */
    public void create(Pedido pedido){
        /**
         * Orden para crear un pedido en la base de datos
         */
        String sql = "INSERT INTO pedidos(direccion, tipo, estado)VALUES(?, ?, ?)";

        try (Connection conexion = ConexionDB.getConnection();
             /**
              * La conexión llama a la orden "sql" (para crear pedidos en la base de datos) y la guarda en la variable "stmt" para ser ejecutada
              */
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            /**
             * Se asigna una posición al nombre, tipoPedido y estado del pedido obtenido
             */
            stmt.setString(1, pedido.getDireccionEntrega());
            stmt.setString(2, pedido.getTipoPedido());
            stmt.setString(3, pedido.getEstado().name()); // Se obtiene el nombre del estado (PENDIENTE, EN_REPARTO, ENTREGADO)

            /**
             * Se ejecuta la orden
             */
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al crear el pedido.", e);
        }
    }

    /**
     * Metodo para leer todos los Pedidos registrados en la BD
     */
    public List<Pedido> readAll(){
        /**
         * Se crea una lista de tipo "Pedido"
         */
        List<Pedido> pedidos = new ArrayList<>();

        /**
         * Orden que obtiene todos los pedidos
         */
        String sql = "SELECT * FROM pedidos";

        /**
         * Conexión con la base de datos
         */
        try (Connection conexion = ConexionDB.getConnection();
             /**
              * Se prepara la ejecución de la orden
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
                 * Se obtienen id, direccion, tipo y estado del pedido
                 */
                int id = rs.getInt("id");
                String direccion = rs.getString("direccion");
                String tipo = rs.getString("tipo");
                String estado = rs.getString("estado");

                /**
                 * Se valída el tipo de pedido antes de agregarlo a la lista
                 */
                if (tipo.equals("COMIDA")){
                    /**
                     * Se crea una instancia de Repartidor, le pasamos el "id" y nombre
                     */
                    Pedido pedidoComida = new PedidoComida(id, tipo, direccion, 0);
                    /**
                     * Se agrega el pedidoComida a la lista
                     */
                    pedidos.add(pedidoComida);
                    /**
                     * Cambia el estado del pedido
                     */
                    pedidoComida.setNuevoEstado(estado);
                }
                if (tipo.equals("ENCOMIENDA")){
                    Pedido pedidoEncomienda = new PedidoEncomienda(id, tipo, direccion, 0);
                    /**
                     * Se agrega el pedidoEncomienda a la lista
                     */
                    pedidos.add(pedidoEncomienda);
                    /**
                     * Cambia el estado del pedido
                     */
                    pedidoEncomienda.setNuevoEstado(estado);
                }
                if (tipo.equals("EXPRESS")){
                    Pedido pedidoExpress = new PedidoExpress(id, tipo, direccion, 0);
                    /**
                     * Se agrega pedidoExpress a la lista
                     */
                    pedidos.add(pedidoExpress);
                    /**
                     * Cambia el estado del pedido
                     */
                    pedidoExpress.setNuevoEstado(estado);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al leer los pedidos.", e);
        }
        return pedidos;
    }

    /**
     * Metodo para modificar un Pedido
     */
    public void update(Pedido pedido){
        /**
         * Orden que actualiza los pedidos especificando su "id"
         */
        String sql = "UPDATE pedidos SET direccion = ?, tipo = ?, estado = ? WHERE id = ?";

        /**
         * Se obtiene la conexión con la base de datos
         */
        try (Connection conexion = ConexionDB.getConnection();
             /**
              * La conexión llama a la orden "sql" (que actualiza un repartidor) y la guarda en la variable "stmt" para ser ejecutada
              */
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            /**
             * Se asigna una posición a la direccion, tipo, estado y id del pedido obtenido
             */
            stmt.setString(1, pedido.getDireccionEntrega());
            stmt.setString(2, pedido.getTipoPedido());
            stmt.setString(3, pedido.getEstado().name());
            stmt.setInt(4, pedido.getIdPedido());

            /**
             * Se ejecuta la orden
             */
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el pedido.", e);
        }
    }

    /**
     * Metodo para eliminar un Pedido
     */
    public void delete(int idPedido){
        /**
         * Orden que elimina un pedido según el "id" indicado
         */
        String sql = "Delete FROM pedidos WHERE id = ?";

        /**
         * Conexión con la base de datos
         */
        try (Connection conexion = ConexionDB.getConnection();
             /**
              * Se prepara la ejecución de la orden
              */
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            /**
             * Se le asigna la posición al idPedido
             */
            stmt.setInt(1, idPedido);
            /**
             * Se ejecuta la orden
             */
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el pedido.", e);
        }
    }
}
