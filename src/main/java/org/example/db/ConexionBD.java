package org.example.db;

import java.sql.*;

public class ConexionBD {
    // Connection credentials pointing to your Docker container
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/cuentasbd";
    private static final String USER = "postgres";
    private static final String PASS = "supersecret";

    public static void main(String[] args) {
    }
}
