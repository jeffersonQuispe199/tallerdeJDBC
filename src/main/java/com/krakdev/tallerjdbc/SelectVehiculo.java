package com.krakdev.tallerjdbc;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelectVehiculo {

    private static final Logger LOGGER = LoggerFactory.getLogger(SelectVehiculo.class);

    public static void main(String[] args) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT placa, marca, modelo, anio, precio, color, disponible FROM vehiculos";

        try {
            conexion = Conexion.conectar();
            if (conexion != null) {
                ps = conexion.prepareStatement(sql);
                rs = ps.executeQuery();

                System.out.println("--- LISTADO DE VEHÍCULOS ---");
                while (rs.next()) {
                    String placa = rs.getString("placa");
                    String marca = rs.getString("marca");
                    String modelo = rs.getString("modelo");
                    int anio = rs.getInt("anio");
                    double precio = rs.getDouble("precio");
                    String color = rs.getString("color");
                    boolean disponible = rs.getBoolean("disponible");

                    System.out.println("Placa: " + placa + " | Marca: " + marca + " | Modelo: " + modelo + 
                                       " | Año: " + anio + " | Precio: " + precio + " | Color: " + color + 
                                       " | Disponible: " + disponible);
                }
                LOGGER.info("Vehículos listados correctamente desde la base de datos.");
            }
        } catch (SQLException e) {
            LOGGER.error("Error al listar los vehículos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar recursos adecuadamente
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conexion != null) conexion.close();
                LOGGER.info("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                LOGGER.error("Error al cerrar los recursos: " + e.getMessage());
            }
        }
    }
}
