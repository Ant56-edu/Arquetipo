package edu.arquetipo.jpa.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String fechaNacimiento;
    private String rol;
    private String email;

    @Override
    public String toString() {
        return "Usuario [Id=" + id + "\n Nombre=" + nombre + "\n Fecha de nacimiento=" + fechaNacimiento + "\n Rol="
                + rol
                + "\n Email=" + email + "\n Teléfono=" + tlf + "]";
    }

    private int tlf;
    private String contrasena;

    // Constructor sin argumentos
    public Usuario() {
    }

    // Constructor para testing y registro de usuarios
    public Usuario(String nombre, String fechaNacimiento, String rol, String email, int tlf, String contrasena) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.rol = rol;
        this.email = email;
        this.tlf = tlf;
        this.contrasena = contrasena;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTlf() {
        return tlf;
    }

    public void setTlf(int tlf) {
        this.tlf = tlf;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contraseña) {
        this.contrasena = contraseña;
    }

}
