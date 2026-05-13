package org.example.model;

public class CuentaAhorro extends Cuenta {
    public CuentaAhorro(int id, int cliente_id, String numero_cuenta, String tipo_cuenta, Double saldo_actual) {
        super(id, cliente_id, numero_cuenta, tipo_cuenta, saldo_actual);
    }

    @Override
    public void depositar(Double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser positivo");
        }
        // Actualizamos el saldo usando el setter de la clase padre
        setSaldo_actual(getSaldo_actual() + monto);
        System.out.println("Depósito exitoso. Nuevo saldo: S/ " + getSaldo_actual());
    }

    @Override
    public void retirar(Double monto){
        System.out.println("Retirando " + monto + " de la cuenta de ahorro de " + getCliente_id());
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser positivo");
        }

        System.out.println("Intentando retirar S/ " + monto + " de la cuenta: " + getNumero_cuenta());

        if (getSaldo_actual() >= monto) {
            setSaldo_actual(getSaldo_actual() - monto);
            System.out.println("Retiro exitoso. Saldo restante: S/ " + getSaldo_actual());
        } else {
            // Es mejor un mensaje claro del error
            throw new IllegalArgumentException("Saldo insuficiente. Saldo disponible: S/ " + getSaldo_actual());
        }
    }
}
