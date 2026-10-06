package com.speedfast.dao;

import com.speedfast.model.Repartidor;
import com.speedfast.connection.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que encargada de crear, actualizar, eliminar y leer una lista de repartidores desde la base de datos.
 */
public class RepartidorDAO {
    /**
     * Metodo para crear un Repartidor en la base de datos
     */
    public void create(Repartidor repartidor){
        /**
         * Orden SQL para crear repartidores en la base de datos, con marcadores de posición para cada valor (?)
         */
        String sql = "INSERT INTO repartidores(nombre)VALUES(?)";

        /**
         * Se obtiene la conexión con la base de datos
         */
        try (Connection conexion = ConexionDB.getConnection();
             /**
              * La conexión llama a la orden "sql" (para crear Repartidores en la base de datos) y la guarda en la variable "stmt" para ser ejecutada
              */
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            /**
             * La variable "stmt" obtiene el nombre y lo posiciona en el "?"
             */
            stmt.setString(1, repartidor.getNombre());

            /**
             * Se ejecuta la orden
             */
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al crear repartidor.", e);
        }
    }

    /**
     * Metodo de tipo List<Repartidor> para leer todos los Repartidores registrados en la tabla de la BD
     */
    public List<Repartidor> readAll() {
        /**
         * Se crea una lista de tipo "Repartidor"
         */
        List<Repartidor> listaRepartidores = new ArrayList<>();

        /**
         * Orden sql que obtiene todos los repartidores
         */
        String sql = "SELECT * FROM repartidores";

        /**
         * Conexión con la base de datos
         */
        try (Connection conexion = ConexionDB.getConnection();
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
                 * Se obtienen id y nombre
                 */
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                /**
                 * Se crea una instancia de Repartidor, le pasamos el "id" y nombre
                 */
                Repartidor repartidor = new Repartidor(id, nombre);

                /**
                 * Se agrega la instancia a la lista
                 */
                listaRepartidores.add(repartidor);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar repartidores.", e);
        }
        /**
         * Devuelve la lista
         */
        return listaRepartidores;
    }

    /**
     * Metodo para modificar una Entrega
     */
    public void update(Repartidor repartidor) {
        /**
         * Instrucción SQL para actualizar repartidores (nombre) especificando su "id".
         * Los (?) son un marcador de posición para cada valor.
         */
        String sql = "UPDATE repartidores SET nombre = ? WHERE id = ?";

        /**
         * Se obtiene la conexión con la base de datos
         */
        try (Connection conexion = ConexionDB.getConnection();
             /**
              * La conexión llama a la orden "sql" (que actualiza un repartidor) y la guarda en la variable "stmt" para ser ejecutada
              */
            PreparedStatement stmt = conexion.prepareStatement(sql)) {

            /**
             * Se asigna una posición al nombre del repartidor y a su id asociado
             */
            stmt.setString(1, repartidor.getNombre());
            stmt.setInt(2, repartidor.getIdRepartidor());

            /**
             * Se ejecuta la actualización de un repartidor
             */
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar repartidor.", e);
        }
    }

    /**
     * Metodo para eliminar un Repartidor de la base de datos
     * @param id
     */
    public void delete(int id) {
        /**
         * Orden que elimina un repartidor según el "id" indicado
         */
        String sql = "Delete FROM repartidores WHERE id = ?";

        /**
         * Conexión con la base de datos
         */
        try (Connection conexion = ConexionDB.getConnection();
             /**
              * Se prepara la ejecución de la orden
              */
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            /**
             * Se le asigna la posición al id
             */
            stmt.setInt(1, id);
            /**
             * Se ejecuta la orden
             */
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el repartidor.", e);
        }
    }
}
