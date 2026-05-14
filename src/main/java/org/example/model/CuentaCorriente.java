package org.example.model;

public class CuentaCorriente extends Cuenta {
    public CuentaCorriente(int id, int Cliente_id,String numero_cuenta, String tipo_cuenta, Double saldo_actual) {
        super(id, Cliente_id, numero_cuenta, tipo_cuenta, saldo_actual);
    }

    @Override
    public void depositar(Double monto) {
        System.out.println("Depositando " + monto + " en la cuenta corriente de " + getCliente_id());
    }

    @Override
    public void retirar(Double monto){
        System.out.println("Retirando " + monto + " de la cuenta corriente de " + getCliente_id());
        if (getSaldo_actual() + 500 >= monto) {
            System.out.println("El sobregiro permitido");
        } else {
            throw new IllegalArgumentException("No se puede retirar más de lo que hay en la cuenta" + monto);
        }
    }
}
