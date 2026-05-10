package org.example.model;

public class Movimiento {
    private int id;
    private String tipo;
    private Double monto;

    public Movimiento(int id, Double monto, String tipo) {
        this.id = id;
        this.monto = monto;
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
