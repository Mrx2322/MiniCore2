package org.example.repository;

import org.example.db.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CuentaAhorroRepository {

    private static final Logger logger = Logger.getLogger(CuentaAhorroRepository.class.getName());
    private void executePreparedStatement(Connection conn, String sql) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        }
    }

     public void createTable() {
         try (Connection conn = ConexionBD.connect()) {
             if (conn != null) {
                 // Se actualizan los nombres y tipos según la imagen (SQLite/Estilo estándar)
                 String createTableSql = "CREATE TABLE IF NOT EXISTS Cuenta (" +
                         "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                         "cliente_id INTEGER NOT NULL, " +
                         "numero_cuenta TEXT NOT NULL UNIQUE, " +
                         "tipo_cuenta TEXT, " +
                         "saldo_actual DECIMAL(15,2) DEFAULT 0.00,"  +
                         "CONSTRAINT fk_cliente FOREIGN KEY(cliente_id) REFERENCES Cliente(id) ON DELETE CASCADE)";

                 executePreparedStatement(conn, createTableSql);
                 logger.info("Tabla 'Cuenta' verificada/creada con éxito.");
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
                    pstmt.setInt(1, 1);                    // cliente_id (ID numérico)
                    pstmt.setString(2, "191-74291919-0");    // numero_cuenta
                    pstmt.setString(3, "AHORRO");            // tipo_cuenta
                    pstmt.setDouble(4, 1500.50);             // saldo_actual (Decimal/Double)
                    pstmt.addBatch();

                    // Registro 2
                    pstmt.setInt(1, 2);                    // cliente_id
                    pstmt.setString(2, "191-73128522-5");    // numero_cuenta
                    pstmt.setString(3, "AHORRO");         // tipo_cuenta
                    pstmt.setDouble(4, 50.00);               // saldo_actual
                    pstmt.addBatch();

                    pstmt.executeBatch();
                    logger.info("Datos de cuenta insertados correctamente en batch.");
                }
            }
        } catch (SQLException e) {
            logger.severe("Error al insertar cuentas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void selectCuenta() {
        try (Connection conn = ConexionBD.connect()) {
            if (conn != null) {
                // Usamos los nombres de columnas con guion bajo (_) como en tu INSERT
                String selectSql = "SELECT id, cliente_id, numero_cuenta, tipo_cuenta, saldo_actual FROM Cuenta";

                try (PreparedStatement pstmt = conn.prepareStatement(selectSql);
                     ResultSet rs = pstmt.executeQuery()) {

                    logger.info(" Fetching cuentas from database:");

                    while (rs.next()) {
                        // Extraemos los datos según su tipo en la base de datos
                        int id = rs.getInt("id");
                        int clienteId = rs.getInt("cliente_id");
                        String numeroCuenta = rs.getString("numero_cuenta");
                        String tipoCuenta = rs.getString("tipo_cuenta");
                        double saldoActual = rs.getDouble("saldo_actual");

                        // Imprimimos usando el logger
                        logger.log(Level.INFO, "   -> ID: {0} | Cliente ID: {1} | Nro: {2} | Tipo: {3} | Saldo: S/ {4}",
                                new Object[]{id, clienteId, numeroCuenta, tipoCuenta, saldoActual});
                    }
                }
            } else {
                logger.warning("No se pudo establecer conexión para la consulta de cuentas.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al consultar la tabla Cuenta: {0}", e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateSaldo(int idCuenta, Double nuevoSaldo) {
        String sql = "UPDATE Cuenta SET saldo_actual = ? WHERE id = ?";

        try (Connection conn = ConexionBD.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, nuevoSaldo);
            pstmt.setInt(2, idCuenta);

            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                logger.info("Saldo actualizado en la base de datos para la cuenta ID: " + idCuenta);
            } else {
                logger.warning("No se encontró la cuenta con ID: " + idCuenta);
            }

        } catch (SQLException e) {
            logger.severe("Error al actualizar el saldo en Postgres: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
