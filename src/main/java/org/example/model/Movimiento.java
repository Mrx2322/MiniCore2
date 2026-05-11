package org.example.model;

import java.time.LocalDateTime;

public class Movimiento {
    private int id;
    private String cuentaId;
    private Double monto;
    private String tipoMovimiento;
    private LocalDateTime fechaMovimiento;

    public Movimiento(int id, String cuentaId, Double monto, String tipoMovimiento, LocalDateTime fechaMovimiento) {
        this.id = id;
        this.cuentaId = cuentaId;
        this.monto = monto;
        this.tipoMovimiento = tipoMovimiento;
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

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public LocalDateTime getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(LocalDateTime fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }
}

