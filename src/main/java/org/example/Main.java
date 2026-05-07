package org.example;

public class Main {
    public static void main(String[] args) {
        CuentaAhorro cuentaAhorro = new CuentaAhorro("001", "Juan Perez", 1000.0);
        CuentaCorriente cuentaCorriente = new CuentaCorriente("002", "Maria Lopez", 500.0);

        System.out.println("Saldo de la cuenta de Juan Perez: " + cuentaAhorro.getSaldo());
        System.out.println("Saldo de la cuenta de Maria Lopez: " + cuentaCorriente.getSaldo());

        // Realizar operaciones en la cuenta de ahorro
//        cuentaAhorro.depositar(200.0);
        cuentaAhorro.retirar(500.0);
        cuentaAhorro.retirar(600.00);

//        // Realizar operaciones en la cuenta corriente
//        cuentaCorriente.depositar(300.0);
//        cuentaCorriente.retirar(600.0); // Esto permitirá un sobregiro de hasta 500
//
//        // Realizar una transferencia desde la cuenta de ahorro a la cuenta corriente
//        cuentaAhorro.transferencia(100.0, cuentaCorriente);
    }
}
