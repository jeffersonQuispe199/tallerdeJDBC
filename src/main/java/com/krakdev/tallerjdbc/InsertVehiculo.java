package com.krakdev.tallerjdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsertVehiculo {

    private static final Logger LOGGER = LoggerFactory.getLogger(InsertVehiculo.class);

    public static void main(String[] args) {
        Connection conexion = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO vehiculos (placa, marca, modelo, anio, precio, color, disponible) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            conexion = Conexion.conectar();
            if (conexion != null) {
                ps = conexion.prepareStatement(sql);
                
                // Asignando valores a los parámetros ?
                ps.setString(1, "ABC-1234");
                ps.setString(2, "Toyota");
                ps.setString(3, "Corolla");
                ps.setInt(4, 2022);
                ps.setDouble(5, 18500.00);
                ps.setString(6, "Rojo");
                ps.setBoolean(7, true);

                // Ejecutar la inserción
                ps.executeUpdate();
                LOGGER.info("Vehículo insertado con éxito en la base de datos.");
                System.out.println("¡Vehículo insertado correctamente!");
            }
        } catch (SQLException e) {
            LOGGER.error("Error al insertar el vehículo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar recursos para evitar fugas de memoria (Parte 8)
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


