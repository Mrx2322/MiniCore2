package org.example.model;

import java.time.LocalDateTime;

public class Movimiento {
    private int id;
    private String cuentaId;
    private Double monto;
    private String tipo_movimiento;
    private LocalDateTime fechaMovimiento;

    public Movimiento(int id, String cuentaId, Double monto, String tipo_movimiento, LocalDateTime fechaMovimiento) {
        this.id = id;
        this.cuentaId = cuentaId;
        this.monto = monto;
        this.tipo_movimiento = tipo_movimiento;
        this.fechaMovimiento = fechaMovimiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(String cuentaId) {
        this.cuentaId = cuentaId;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getTipo_movimiento() {
        return tipo_movimiento;
    }

    public void setTipo_movimiento(String tipo_movimiento) {
        this.tipo_movimiento = tipo_movimiento;
    }

    public LocalDateTime getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(LocalDateTime fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }
}

