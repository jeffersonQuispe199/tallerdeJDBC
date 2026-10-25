package com.krakdev.tallerjdbc;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Conexion {
    private static final String URL = "jdbc:postgresql://localhost:5432/tallerjdbc";
    private static final String USER = "postgres";
    // Reemplaza "tu_contraseña" con la contraseña de tu servidor de PostgreSQL
    private static final String PASSWORD = "199210"; 

    private static final Logger LOGGER = LoggerFactory.getLogger(Conexion.class);

    public static Connection conectar() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            LOGGER.info("Conexión realizada con éxito a la base de datos.");
        } catch (ClassNotFoundException e) {
            LOGGER.error("Error al cargar el driver de PostgreSQL: " + e.getMessage());
        } catch (SQLException e) {
            LOGGER.error("Error de conexión a la base de datos: " + e.getMessage());
        }
        return connection;
    }
}