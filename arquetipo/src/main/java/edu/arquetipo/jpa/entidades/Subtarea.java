package edu.arquetipo.jpa.entidades;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "Subtareas")
public class Subtarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    Set<Usuario> Usuarios;

    @ManyToOne
    @JoinColumn(name = "Tarea")
    Set<Tarea> tareas;

    private String nombre;
    private LocalDate fechaCreacion;
    private Usuario[] empleadosAsignados;
    private Tarea[] tareaAsociada;

    // Constructor sin argumentos
    public Subtarea() {
    }

    // Constructor para testing y registro de usuarios
    public Subtarea(Usuario[] empleadosAsignados, LocalDate fechaCreacion, String nombre,
            Tarea[] tareaAsociada) {
        this.empleadosAsignados = empleadosAsignados;
        this.fechaCreacion = fechaCreacion;
        this.nombre = nombre;
        this.tareaAsociada = tareaAsociada;
    }

    @Override
    public String toString() {
        return "Subtarea [id=" + id + ", nombre=" + nombre + ", fechaCreacion=" + fechaCreacion
                + ", empleadosAsignados=" + Arrays.toString(empleadosAsignados) + ", tareaAsociada="
                + Arrays.toString(tareaAsociada) + "]";
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

    public Tarea[] getTareaAsociada() {
        return tareaAsociada;
    }

    public void setTareaAsociada(Tarea[] tareaAsociada) {
        this.tareaAsociada = tareaAsociada;
    }

}
