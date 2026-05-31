package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "cuentas_ahorro") // llamar a la tabla
public class CuentaAhorro extends Cuenta {

    public CuentaAhorro() {
    }

    public CuentaAhorro(Cliente cliente, String numeroCuenta, String tipoCuenta, Double saldoActual) {
        super(cliente, numeroCuenta, tipoCuenta, saldoActual);
    }

    @Override
    public void depositar(Double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser positivo");
        }

        this.setSaldoActual(this.getSaldoActual() + monto);
        System.out.println("Depósito exitoso. Nuevo saldo: S/ " + this.getSaldoActual());
    }

    @Override
    public void retirar(Double monto) {
        // Obtenemos el ID a través del objeto Cliente heredado
        System.out.println("Retirando " + monto + " de la cuenta de ahorro del cliente con ID " + this.getCliente().getId());

        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser positivo");
        }

        System.out.println("Intentando retirar S/ " + monto + " de la cuenta: " + this.getNumeroCuenta());

        if (this.getSaldoActual() >= monto) {
            this.setSaldoActual(this.getSaldoActual() - monto);
            System.out.println("Retiro exitoso. Saldo restante: S/ " + this.getSaldoActual());
        } else {
            // Es mejor un mensaje claro del error
            throw new IllegalArgumentException("Saldo insuficiente. Saldo disponible: S/ " + this.getSaldoActual());
        }
    }
}
