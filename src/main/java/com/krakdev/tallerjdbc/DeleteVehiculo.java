package com.krakdev.tallerjdbc;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteVehiculo {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteVehiculo.class);

    public static void main(String[] args) {
        Connection conexion = null;
        PreparedStatement ps = null;

        String sql = "DELETE FROM vehiculos WHERE placa = ?";

        try {
            conexion = Conexion.conectar();
            if (conexion != null) {
                ps = conexion.prepareStatement(sql);
                
                // Placa del vehículo que deseas eliminar
                ps.setString(1, "ABC-1234");

                // Ejecutar la eliminación
                int filasAfectadas = ps.executeUpdate();
                if (filasAfectadas > 0) {
                    LOGGER.info("Vehículo eliminado con éxito de la base de datos.");
                    System.out.println("¡Vehículo eliminado correctamente!");
                } else {
                    System.out.println("No se encontró ningún vehículo con la placa especificada.");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error al eliminar el vehículo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (ps != null) ps.close();
                if (conexion != null) conexion.close();
                LOGGER.info("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                LOGGER.error("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}