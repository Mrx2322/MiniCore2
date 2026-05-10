package org.example.model;

import java.time.LocalDateTime;

public class Movimiento {
    private int id;
    private String cuentaId;
    private Double monto;
    private String tipo_movimiento;
    private LocalDateTime fechaMovimiento;

    public Movimiento(int id, Double monto, String tipo) {
        this.id = id;
        this.monto = monto;
        this.Cuenta = tipo;
    }

    public String getTipo() {
        return Cuenta;
    }

    public void setTipo(String tipo) {
        this.Cuenta = tipo;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
