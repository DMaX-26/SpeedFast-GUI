package com.speedfast.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    // dirección completa al servidor MySQL y la base de datos "speedfast_db"
    private static final String URL = "jdbc:mysql://localhost:3306/speedfast_db";
    private static final String USUARIO = "root"; // nombre de usuario para acceder (por defecto: "root")
    private static final String CONSTRASENA = "12345"; // contraseña asociada

    /**
     * Conexión con la base de datos
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONSTRASENA);
    }
}
