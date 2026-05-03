package org.example;

public abstract class Cuenta {
    private String id;
    private String titular;
    private Double saldo;

    // Constructor
    public Cuenta(String id, String titular, Double saldo) {
        this.id = id;
        this.titular = titular;
        this.saldo = saldo;
    }

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

    //Métodos
    public abstract void depositar( double monto);
    public void retirar(){
        if (saldo > 0) {
            Throw new IllegalAccessException("Saldo insuficiente");
        }
    }
}
