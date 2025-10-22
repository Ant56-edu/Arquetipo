package edu.arquetipo.jpa.entidades;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "Tareas")
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private LocalDate fechaCreacion;
    private Usuario[] empleadosAsignados;
    private Usuario gestorEncargado;
    private String estadoTarea;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tarea{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", fechaCreacion=").append(fechaCreacion);
        sb.append(", empleadosAsignados=").append(empleadosAsignados);
        sb.append(", gestorEncargado=").append(gestorEncargado);
        sb.append(", estadoTarea").append(estadoTarea);
        sb.append('}');
        return sb.toString();
    }

    // Constructor sin argumentos
    public Tarea() {
    }

    // Constructor para testing y registro de usuarios
    public Tarea(String nombre, LocalDate fechaCreacion, Usuario[] empleadosAsignados, Usuario gestorEncargado,
            String estadoTarea) {
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.empleadosAsignados = empleadosAsignados;
        this.gestorEncargado = gestorEncargado;
        this.estadoTarea = estadoTarea;
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

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Usuario[] getEmpleadosAsignados() {
        return empleadosAsignados;
    }

    public void setEmpleadosAsignados(Usuario[] empleadosAsignados) {
        this.empleadosAsignados = empleadosAsignados;
    }

    public Usuario getGestorEncargado() {
        return gestorEncargado;
    }

    public void setGestorEncargado(Usuario gestorEncargado) {
        this.gestorEncargado = gestorEncargado;
    }

    public String getEstadoTarea() {
        return estadoTarea;
    }

    public void setEstadoTarea(String estadoTarea) {
        this.estadoTarea = estadoTarea;
    }

}
