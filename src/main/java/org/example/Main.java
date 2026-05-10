package org.example;

import org.example.repository.ClienteRepository;

public class Main {
    public static void main() {
        ClienteRepository repository = new ClienteRepository();

        // Ejecutando primero createTable
        repository.createTable();

        // Luego procedemos con la lógica
        repository.insertCliente();
        repository.selectCliente();
    }
}
