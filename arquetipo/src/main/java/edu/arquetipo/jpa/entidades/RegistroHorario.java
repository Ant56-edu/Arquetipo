package edu.arquetipo.jpa.entidades;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "RegistroHorario")
public class RegistroHorario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "Usuarios_has_Registros", joinColumns = @JoinColumn(name = "idRegistro"), inverseJoinColumns = @JoinColumn(name = "idUsuario"))
    Set<Usuario> usuarios;

    private LocalDate fechaRegistro;
    private LocalTime checkIn;
    private LocalTime checkOut;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalTime checkOut) {
        this.checkOut = checkOut;
    }

    public RegistroHorario() {
    }

    public RegistroHorario(LocalTime checkIn, LocalTime checkOut, LocalDate fechaRegistro) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RegistroHorario{");
        sb.append("id=").append(id);
        sb.append(", fechaRegistro=").append(fechaRegistro);
        sb.append(", checkIn=").append(checkIn);
        sb.append(", checkOut=").append(checkOut);
        sb.append('}');
        return sb.toString();
    }
}
