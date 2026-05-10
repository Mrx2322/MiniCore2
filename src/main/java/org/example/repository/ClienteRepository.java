package org.example.repository;

import org.example.db.ConexionBD;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.*;


public class ClienteRepository {

    private static final Logger logger = Logger.getLogger(ClienteRepository.class.getName());
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
                        "dni VARCHAR(255))" +
                        "email VARCHAR(255)" +
                        "fechaRegistro DATETIME DEFAULT CURRENT_TIMESTAMP";
                executePreparedStatement(conn, createTableSql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertCliente() {
        try (Connection conn = ConexionBD.connect()) {
            if (conn != null) {
                String insertSql = "INSERT INTO Cliente (name, dni, email, fechaRegistro) VALUES (?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                    // Primer registro
                    pstmt.setString(1, "Deivis Roberto");
                    pstmt.setString(2, "74291919");
                    pstmt.setString(3, "djemdliam@gmail.com");
                    pstmt.setString(4, "2024-06-01");
                    pstmt.addBatch(); // En lugar de executeUpdate, lo agregamos a la "caja" (lote)

                    // Segundo registro
                    pstmt.setString(1, "Antonio Seguro");
                    pstmt.setString(2, "73128522");
                    pstmt.setString(3, "acsegurog@gmail.com");
                    pstmt.setString(4, "2025-05-01");
                    pstmt.addBatch();

                    // Ejecutamos toda la caja de un solo viaje a la base de datos
                    pstmt.executeBatch();

                    logger.info("Mock data inserted in batch successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectCliente() {
        try (Connection conn = ConexionBD.connect()) {
            if (conn != null) {
                String selectSql = "SELECT id, name, dni, email, fechaRegistro FROM Cliente";

                try (PreparedStatement pstmt = conn.prepareStatement(selectSql);
                     ResultSet rs = pstmt.executeQuery()) {

                    logger.info(" Fetching clientes from database:");

                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String dni = rs.getString("dni");
                        String email = rs.getString("email");
                        String fechaRegistro = rs.getString("fechaRegistro");
                        logger.log(Level.INFO, "   -> ID: {0} | Name: {1} | Dni: {2} | Email: {3} | Fecha: {4}",
                                new Object[]{id, name, dni, email, fechaRegistro});
                    }
                }
            } else {
                logger.info("Could not establish a connection to the database.");
            }

        } catch (Exception e) {
            logger.info("Database error occurred!");
            e.printStackTrace();
        }
    }
}
