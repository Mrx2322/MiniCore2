package org.example.repository;

import org.example.db.ConexionBD;

import java.sql.*;

public class ClienteRepository {

    // TODO adaptar a cliente, candidate no es una clase de nuestro programa

    private void executePreparedStatement(Connection conn, String sql) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        }
    }

    public void createTable() {
        try (Connection conn = ConexionBD.connect()) {
            if (conn != null) {
                String createTableSql = "CREATE TABLE IF NOT EXISTS Cliente (" +
                        "id SERIAL PRIMARY KEY, " +
                        "name VARCHAR(255), " +
                        "dni VARCHAR(255))";
                executePreparedStatement(conn, createTableSql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertCliente() {
        try (Connection conn = ConexionBD.connect()) {
            if (conn != null) {
                String insertSql = "INSERT INTO Cliente (name, dni) VALUES (?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                    // Primer registro
                    pstmt.setString(1, "Deivis Roberto");
                    pstmt.setString(2, "74291919");
                    pstmt.addBatch(); // En lugar de executeUpdate, lo agregamos a la "caja" (lote)

                    // Segundo registro
                    pstmt.setString(1, "Antonio Seguro");
                    pstmt.setString(2, "1278567");
                    pstmt.addBatch();

                    // Ejecutamos toda la caja de un solo viaje a la base de datos
                    pstmt.executeBatch();

                    System.out.println("Mock data inserted in batch successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectCliente() {
        try (Connection conn = ConexionBD.connect()) {
            if (conn != null) {
                String selectSql = "SELECT id, name, dni FROM Cliente";

                try (PreparedStatement pstmt = conn.prepareStatement(selectSql);
                     ResultSet rs = pstmt.executeQuery()) {

                    System.out.println("Fetching clientes from database:");

                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String dni = rs.getString("dni");
                        System.out.println("   -> ID: " + id + " | Name: " + name + " | Dni: " + dni);
                    }
                }
            } else {
                System.err.println("Could not establish a connection to the database.");
            }

        } catch (Exception e) {
            System.err.println("Database error occurred!");
            e.printStackTrace();
        }
    }
}
