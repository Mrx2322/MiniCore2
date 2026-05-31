package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "cuenta_corriente")
public class CuentaCorriente extends Cuenta {

    // Atributo propio de una cuenta corriente (ejemplo: línea de sobregiro autorizado)
    private Double limiteSobregiro;

    // 1. CONSTRUCTOR VACÍO (Obligatorio para JPA)
    public CuentaCorriente() {
    }

    // 2. CONSTRUCTOR ACTUALIZADO (Recibe el objeto Cliente y omite el id manual)
    public CuentaCorriente(Cliente cliente, String numeroCuenta, String tipoCuenta, Double saldoActual, Double limiteSobregiro) {
        super(cliente, numeroCuenta, tipoCuenta, saldoActual); // Llama al constructor de la clase padre
        this.limiteSobregiro = limiteSobregiro; // Inicializa el atributo específico de CuentaRepository Corriente
    }

    // Getter y Setter exclusivo

    public Double getLimiteSobregiro() {
        return limiteSobregiro;
    }

    public void setLimiteSobregiro(Double limiteSobregiro) {
        this.limiteSobregiro = limiteSobregiro;
    }


    // IMPLEMENTACIÓN DE MÉTODOS ABSTRACTOS

    @Override
    public void depositar(Double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser positivo");
        }

        this.setSaldoActual(this.getSaldoActual() + monto);
        System.out.println("Depósito exitoso en CuentaRepository Corriente. Nuevo saldo: S/ " + this.getSaldoActual());
    }

    @Override
    public void retirar(Double monto) {
        System.out.println("Retirando " + monto + " de la cuenta corriente del cliente con ID " + this.getCliente().getId());

        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser positivo");
        }

        System.out.println("Intentando retirar S/ " + monto + " de la cuenta: " + this.getNumeroCuenta());

        // Lógica típica de CuentaRepository Corriente: el saldo disponible incluye el sobregiro
        Double saldoDisponible = this.getSaldoActual() + this.limiteSobregiro;

        if (saldoDisponible >= monto) {
            this.setSaldoActual(this.getSaldoActual() - monto);
            System.out.println("Retiro exitoso de CuentaRepository Corriente. Saldo restante: S/ " + this.getSaldoActual());
        } else {
            throw new IllegalArgumentException("Saldo y línea de sobregiro insuficientes. Fondos totales disponibles: S/ " + saldoDisponible);
        }
    }
}
