package org.example.model;

public class Cliente {
    private int id;
    private String nombre;
    private String dni;
    private String email;
    private String fechaRegistro;

    public Cliente(int id, String nombre, String dni, String email, String fechaRegistro) {
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

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}



