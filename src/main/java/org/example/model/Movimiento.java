package org.example.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimiento")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // EL CAMBIO PRINCIPAL: Guardamos el objeto CuentaRepository, no un String
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;

    private Double monto;

    private String tipoMovimiento;

    private LocalDateTime fechaMovimiento;

    // 1. CONSTRUCTOR VACÍO
    public Movimiento() {
    }

    // 2. CONSTRUCTOR ACTUALIZADO (Recibe CuentaRepository, y omitimos el ID porque es autogenerado)
    public Movimiento(Cuenta cuenta, Double monto, String tipoMovimiento, LocalDateTime fechaMovimiento) {
        this.cuenta = cuenta;
        this.monto = monto;
        this.tipoMovimiento = tipoMovimiento;
        this.fechaMovimiento = fechaMovimiento;
    }

    // GETTERS Y SETTERS
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Cuenta getCuenta() { return cuenta; }
    public void setCuenta(Cuenta cuenta) { this.cuenta = cuenta; }

    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }

    public String getTipoMovimiento() { return tipoMovimiento; }
    public void setTipoMovimiento(String tipoMovimiento) { this.tipoMovimiento = tipoMovimiento; }

    public LocalDateTime getFechaMovimiento() { return fechaMovimiento; }
    public void setFechaMovimiento(LocalDateTime fechaMovimiento) { this.fechaMovimiento = fechaMovimiento; }
}
