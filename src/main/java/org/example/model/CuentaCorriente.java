package org.example.model;

public class CuentaCorriente extends Cuenta {
    public CuentaCorriente(String id, String titular, Double saldo) {
        super(id, titular, saldo);
    }

    @Override
    public void depositar(Double monto) {
        System.out.println("Depositando " + monto + " en la cuenta corriente de " + getTitular());
    }

    @Override
    public void retirar(Double monto){
        System.out.println("Retirando " + monto + " de la cuenta corriente de " + getTitular());
        if (getSaldo() + 500 >= monto) {
            System.out.println("El sobregiro permitido");
        } else {
            throw new IllegalArgumentException("No se puede retirar más de lo que hay en la cuenta" + monto);
        }
    }
}
