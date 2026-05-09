package org.example.repository;

import java.sql.*;

public class ClienteRepository {
    static void main(String[] args) {
        // try-with-resources ensures the connection is automatically closed
        // --- 1. CREATE TABLE ---
        // Notice we use SERIAL in Postgres instead of AUTO_INCREMENT
        String createTableSql = "CREATE TABLE IF NOT EXISTS candidates (" +
                "id SERIAL PRIMARY KEY, " +
                "name VARCHAR(255), " +
                "role VARCHAR(255))";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSql);
            System.out.println("2. Table 'candidates' created.");
        }

        // --- 2. INSERT DATA ---
        // Using PreparedStatement is mandatory in interviews to prevent SQL Injection
        String insertSql = "INSERT INTO candidates (name, role) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            // Insert first record
            pstmt.setString(1, "Junior Developer");
            pstmt.setString(2, "Backend Java");
            pstmt.executeUpdate();

            // Insert second record
            pstmt.setString(1, "Senior Mentor");
            pstmt.setString(2, "Architecture");
            pstmt.executeUpdate();
            System.out.println("3. Mock data inserted successfully.");
        }

        // --- 3. SELECT DATA ---
        String selectSql = "SELECT id, name, role FROM candidates";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSql)) {

            System.out.println("4. Fetching candidates from database:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String role = rs.getString("role");
                System.out.println("   -> ID: " + id + " | Name: " + name + " | Role: " + role);
            }
        }

    } catch (Exception e) {
        System.err.println("Database error occurred!");
        e.printStackTrace();
    }
    }
}
