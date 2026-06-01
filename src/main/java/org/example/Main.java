package org.example;

import org.example.model.Cliente;
import org.example.model.CuentaAhorro;
import org.example.model.CuentaCorriente;
import org.example.repository.CuentaRepository;
import org.example.repository.ClienteRepository; // Importamos el repo de cliente
import org.example.service.CuentaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner probarLogica(CuentaRepository cuentaRepo, ClienteRepository clienteRepo, CuentaService cuentaService) {
        return args -> {
            System.out.println("\n--- INICIANDO PRUEBA CORREGIDA ---");

            // 1. Creamos y guardamos los Clientes primero
            Cliente cliente1 = new Cliente();
            cliente1.setNombre("Juan Perez");
            cliente1 = clienteRepo.save(cliente1); // Guardamos para que tenga ID

            Cliente cliente2 = new Cliente();
            cliente2.setNombre("Maria Lopez");
            cliente2 = clienteRepo.save(cliente2);

            // 2. Creamos las cuentas y ASIGNAMOS el cliente (Esto soluciona el null)
            CuentaAhorro cuentaOrigen = new CuentaAhorro();
            cuentaOrigen.setCliente(cliente1); // <-- Aquí asociamos el cliente
            cuentaOrigen.setNumeroCuenta("AHO-001");
            cuentaOrigen.setSaldoActual(1000.0);

            CuentaCorriente cuentaDestino = new CuentaCorriente();
            cuentaDestino.setCliente(cliente2); // <-- Aquí asociamos el cliente
            cuentaDestino.setNumeroCuenta("COR-001");
            cuentaDestino.setSaldoActual(500.0);

            // 3. Guardamos las cuentas
            cuentaOrigen = (CuentaAhorro) cuentaRepo.save(cuentaOrigen);
            cuentaDestino = (CuentaCorriente) cuentaRepo.save(cuentaDestino);

            // 4. Ejecutamos la transferencia
            System.out.println("Ejecutando transferencia de S/ 200...");
            cuentaService.realizarTransferencia(cuentaOrigen.getId(), cuentaDestino.getId(), 200.0);

            System.out.println("\n¡Prueba terminada con éxito!");
        };
    }
}
