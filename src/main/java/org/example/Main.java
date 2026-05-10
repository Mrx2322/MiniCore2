package org.example;

import org.example.model.CuentaAhorro;
import org.example.model.CuentaCorriente;
import org.example.repository.ClienteRepository;

public class Main {
    public static void main(String[] args) {
        ClienteRepository repository = new ClienteRepository();

        // Ejecutando primero createTable
        repository.createTable();

        // Luego procedemos con la lógica
        repository.insertCliente();
        repository.selectCliente();
    }
}
