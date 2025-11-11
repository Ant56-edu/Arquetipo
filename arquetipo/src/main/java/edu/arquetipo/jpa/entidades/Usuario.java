package edu.arquetipo.jpa.entidades;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Clientes")
    Cliente cliente;

    @ManyToMany
    @JoinTable(name = "Usuarios_has_Subtareas", joinColumns = @JoinColumn(name = "idSubtarea"), inverseJoinColumns = @JoinColumn(name = "idUsuario"))
    Set<Subtarea> subtareas;

    @ManyToMany
    @JoinTable(name = "Usuarios_has_Subtareas", joinColumns = @JoinColumn(name = "idSubtarea"), inverseJoinColumns = @JoinColumn(name = "idUsuario"))
    Set<RegistroHorario> registros;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
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

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}
