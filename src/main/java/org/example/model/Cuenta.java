package org.example.model;

public abstract class Cuenta {
    //ATRIBUTOS
   private int id;
   private int cliente_id;
   private String numero_cuenta;
   private String tipo_cuenta;
   private Double saldo_actual;

    //CONSTRUCTOR
    public Cuenta(int id, int cliente_id, String numero_cuenta, String tipo_cuenta, Double saldo_actual) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.numero_cuenta = numero_cuenta;
        this.tipo_cuenta = tipo_cuenta;
        this.saldo_actual = saldo_actual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getNumero_cuenta() {
        return numero_cuenta;
    }

    public void setNumero_cuenta(String numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }

    public String getTipo_cuenta() {
        return tipo_cuenta;
    }

    public void setTipo_cuenta(String tipo_cuenta) {
        this.tipo_cuenta = tipo_cuenta;
    }

    public Double getSaldo_actual() {
        return saldo_actual;
    }

    public void setSaldo_actual(Double saldo_actual) {
        this.saldo_actual = saldo_actual;
    }

    //Métodos abstractos donde se sobreEscribe en CuentaAhorro y CuentaCorriente
    public abstract void depositar( Double monto);
    public abstract void retirar(Double monto);

    public void transferencia(Double monto, Cuenta cuentaDestino){
        System.out.println("Realizando transferencia...");
        if (this.getSaldo_actual() < monto){
            throw new IllegalArgumentException("Saldo Insuficiente para realizar la transferencia de " + monto);
        }

        this.retirar(monto);
        cuentaDestino.depositar(monto);
        System.out.println("Transferencia realizada exitosamente");
    }
}
