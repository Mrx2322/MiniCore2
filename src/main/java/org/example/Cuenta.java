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

    //Métodos
    public void depositar(){
    }
    public void retirar(){
    }
}
