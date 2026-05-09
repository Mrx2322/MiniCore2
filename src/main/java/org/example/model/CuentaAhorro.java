package org.example.model;

public class CuentaAhorro extends Cuenta {
    public CuentaAhorro(String id, String titular, Double saldo) {
        super(id, titular, saldo);
    }

    @Override
    public void depositar(Double monto) {
        System.out.println("Depositando " + monto + " en la cuenta de ahorro de " + getTitular());
    }

    @Override
    public void retirar(Double monto){
        System.out.println("Retirando " + monto + " de la cuenta de ahorro de " + getTitular());
        if (getSaldo() >= monto) {
            setSaldo(getSaldo() - monto);
            System.out.println("Retiro exitoso de " + monto);
        } else {
            throw new IllegalArgumentException("No se puede retirar más de lo que hay en la cuenta" + monto);
        }
    }
}
