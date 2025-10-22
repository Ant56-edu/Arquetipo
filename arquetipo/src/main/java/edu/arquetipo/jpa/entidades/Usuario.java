package edu.arquetipo.jpa.entidades;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String rol;
    private String correo;
    private int tlf;
    private String contrasena;

    @Override
    public String toString() {
        return "Usuario [Id=" + id + "\n Nombre=" + nombre + "\n Fecha de nacimiento=" + fechaNacimiento + "\n Rol="
                + rol
                + "\n Email=" + correo + "\n Teléfono=" + tlf + "]";
    }

    // Constructor sin argumentos
    public Usuario() {
    }

    // Constructor para testing y registro de usuarios
    public Usuario(String nombre, LocalDate fechaNacimiento, String rol, String correo, int tlf, String contrasena) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.rol = rol;
        this.correo = correo;
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
        return fechaNacimiento.toString();
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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
