package org.example.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Cuenta {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Relacion con el cliente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "numero_cuenta")
    private String numeroCuenta;

    @Column(name = "tipo_cuenta")
    private String tipoCuenta;

    @Column(name = "saldo_actual")
    private Double saldoActual;

    public Cuenta() {
    }

    public Cuenta(Cliente cliente, String numeroCuenta, String tipoCuenta, Double saldoActual) {
        this.cliente = cliente;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldoActual = saldoActual;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public Double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(Double saldoActual) {
        this.saldoActual = saldoActual;
    }

    //Metodo de negocio

    public abstract void depositar(Double monto);
    public abstract void retirar(Double monto);

    public void transferir(Cuenta cuentaDestino, Double monto) {
        System.out.println("Realizando transferencia...");
        if (this.getSaldoActual() < monto){
            throw new IllegalArgumentException("Saldo Insuficiente para realizar la transferencia de " + monto);
        }
        this.retirar(monto);
        cuentaDestino.depositar(monto);
        System.out.println("Transferencia realizada con exito");
    }
}
