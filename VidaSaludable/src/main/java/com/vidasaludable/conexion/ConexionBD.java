
package com.vidasaludable.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    // Datos de conexión a MySQL
    private static final String URL =
            "jdbc:mysql://localhost:3306/vida_saludable";

    private static final String USUARIO = "root";

    // Método para establecer la conexión
    public static Connection conectar(String contrasena)
            throws SQLException {

        Connection conexion = DriverManager.getConnection(
                URL, USUARIO, contrasena
        );

        return conexion;
    }
}

