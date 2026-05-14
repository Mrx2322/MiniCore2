package org.example.repository;

import org.example.db.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CuentaCorrienteDepository {
    // TODO Implementar métodos para manejar la persistencia de las cuentas corrientes en la base de datos
    private static final Logger logger = Logger.getLogger(CuentaCorrienteDepository.class.getName());
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
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
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

    public void insertCuenta() {
        try (Connection conn = ConexionBD.connect()) {
            if (conn != null) {
                String insertSql = "INSERT INTO Cuenta (cliente_id, numero_cuenta, tipo_cuenta, saldo_actual) VALUES (?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                    // Registro 1
                    pstmt.setInt(1, 101);                    // cliente_id (ID numérico)
                    pstmt.setString(2, "191-74291919-0");    // numero_cuenta
                    pstmt.setString(3, "AHORRO");            // tipo_cuenta
                    pstmt.setDouble(4, 1500.50);             // saldo_actual (Decimal/Double)
                    pstmt.addBatch();

                    // Registro 2
                    pstmt.setInt(1, 102);                    // cliente_id
                    pstmt.setString(2, "191-73128522-5");    // numero_cuenta
                    pstmt.setString(3, "AHORRO");         // tipo_cuenta
                    pstmt.setDouble(4, 50.00);               // saldo_actual
                    pstmt.addBatch();

                    pstmt.executeBatch();
                    logger.info("CuentaAhorroRepository insertado correctamente");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectCuenta() {
        try (Connection conn = ConexionBD.connect()) {
            if (conn != null) {
                // Usamos los nombres de columnas con guion bajo (_) como en tu INSERT
                String selectSql = "SELECT cliente_id, numero_cuenta, tipo_cuenta, saldo_actual FROM Cuenta";

                try (PreparedStatement pstmt = conn.prepareStatement(selectSql);
                     ResultSet rs = pstmt.executeQuery()) {

                    logger.info(" Fetching cuentas from database:");

                    while (rs.next()) {
                        // Extraemos los datos según su tipo en la base de datos
                        int clienteId = rs.getInt("cliente_id");
                        String numeroCuenta = rs.getString("numero_cuenta");
                        String tipoCuenta = rs.getString("tipo_cuenta");
                        double saldoActual = rs.getDouble("saldo_actual");

                        // Imprimimos usando el logger
                        logger.log(Level.INFO, "   -> Cliente ID: {0} | Nro Cuenta: {1} | Tipo: {2} | Saldo: S/ {3}",
                                new Object[]{clienteId, numeroCuenta, tipoCuenta, saldoActual});
                    }
                }
            } else {
                logger.info("Could not establish a connection to the database.");
            }
        } catch (SQLException e) {
            logger.severe("Error al consultar la cuentas: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
