package org.example.db;

import java.sql.*;

public class ConexionBD {
    // Connection credentials pointing to the Docker container
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/cuentasbd";
    private static final String USER = "postgres";
    private static final String PASS = "supersecret";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.err.println("Database connection failed!");
            e.printStackTrace();

            return null;
        }
    }


}
