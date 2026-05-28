package org.example;

import org.example.model.CuentaAhorro;
import org.example.repository.ClienteRepository;
import org.example.repository.CuentaAhorroRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
