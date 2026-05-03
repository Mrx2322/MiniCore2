package org.example;

public class CuentaAhorro extends Cuenta {
    public CuentaAhorro(String id, String titular, Double saldo) {
        super(id, titular, saldo);
    }

    @Override
    public void depositar(double monto) {
    }
}
