package org.example.model;

import java.sql.Timestamp;

public class Cliente {
    private int id;
    private String nombre;
    private String dni;
    private String email;
    private Timestamp fechaRegistro;

    public Cliente(int id, String nombre, String dni, String email, Timestamp fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}



