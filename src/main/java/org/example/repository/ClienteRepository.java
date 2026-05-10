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

    public void insertCandidate() {
        try (Connection conn = ConexionBD.connect()) {
            if (conn != null) {
                String insertSql = "INSERT INTO Cliente (name, dni) VALUES (?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                    // Insert first record
                    pstmt.setString(1, "Deivis Roberto");
                    pstmt.setString(2, "74291919");
                    pstmt.executeUpdate();

                    // Insert second record
                    pstmt.setString(1, "Senior Mentor");
                    pstmt.setString(2, "Architecture");
                    pstmt.executeUpdate();
                    System.out.println("3. Mock data inserted successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectCandidate() {
        try (Connection conn = ConexionBD.connect()) {
            if (conn != null) {
                String selectSql = "SELECT id, name, role FROM Cliente";

                try (PreparedStatement pstmt = conn.prepareStatement(selectSql);
                     ResultSet rs = pstmt.executeQuery()) {

                    System.out.println("4. Fetching candidates from database:");

                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String role = rs.getString("role");
                        System.out.println("   -> ID: " + id + " | Name: " + name + " | Role: " + role);
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

    public static void main(String[] args) {
        ClienteRepository repository = new ClienteRepository();
        repository.createTable();
        repository.insertCandidate();
        repository.selectCandidate();
    }
}
