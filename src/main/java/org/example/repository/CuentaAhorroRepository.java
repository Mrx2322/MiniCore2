package org.example.repository;

import org.example.db.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CuentaAhorroRepository {
    // TODO: Implementar métodos para manejar la persistencia de las cuentas de ahorro en la base de datos

    private void executePreparedStatement(Connection conn, String sql) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        }
    }

     public void createTable() {
         try (Connection conn = ConexionBD.connect()) {
             if (conn != null) {
                 // Se actualizan los nombres y tipos según la imagen (SQLite/Estilo estándar)
                 String createTableSql = "CREATE TABLE IF NOT EXISTS Cuentas (" +
                         "cuenta_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                         "cliente_id INTEGER NOT NULL, " +
                         "numero_cuenta TEXT NOT NULL UNIQUE, " +
                         "tipo_cuenta TEXT, " +
                         "saldo_actual NUMERIC DEFAULT 0.00)";

                 executePreparedStatement(conn, createTableSql);
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
}
