package org.example;

import org.example.model.CuentaAhorro;
import org.example.repository.ClienteRepository;
import org.example.repository.CuentaAhorroRepository;

public class Main {
    public static void main() {
        ClienteRepository repository = new ClienteRepository();

        // Ejecutando primero createTable
        repository.createTable();

        // Luego procedemos con la lógica
        repository.insertCliente();
        repository.selectCliente();
    }
    // Ejemplo de uso en el Main
    public static void main(String[] args) {
        CuentaAhorroRepository repo = new CuentaAhorroRepository();
        // Ejecutando primero createTable
        repo.createTable();
        // 1. Supongamos que traes la cuenta de la DB con ID 1 y saldo 1500.50
        CuentaAhorro miCuenta = new CuentaAhorro(1, 1, "191-74291919-0", "AHORRO", 1500.50);

        try {
            // 2. Realizas la operación en el objeto Java (Memoria)
            miCuenta.retirar(500.0);

            // 3. Guardas el cambio en PostgreSQL (Persistencia)
            repo.updateSaldo(miCuenta.getId(), miCuenta.getSaldo_actual());

        } catch (IllegalArgumentException e) {
            System.err.println("Operación fallida: " + e.getMessage());
        }
    }
}
