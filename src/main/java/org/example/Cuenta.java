package org.example;

import java.math.BigDecimal;

public abstract class Cuenta {
    //ATRIBUTOS
    private String id;
    private String titular;
    private Double saldo;

    // Constructor
    public Cuenta(String id, String titular, Double saldo) {
        this.id = id;
        this.titular = titular;
        this.saldo = saldo;
    }
     // metodo getter and setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    //Métodos abstractos donde se sobreEscribe en CuentaAhorro y CuentaCorriente
    public abstract void depositar( Double monto);
    public abstract void retirar(Double monto);

    public void transferencia(Double monto, Cuenta cuentaDestino){
        System.out.println("Realizando transferencia...");
        if (this.getSaldo() < monto){
            throw new IllegalArgumentException("Saldo Insuficiente para realizar la transferencia de " + monto);
        }

        this.retirar(monto);
        cuentaDestino.depositar(monto);
        System.out.println("Transferencia realizada exitosamente");
    }
}
