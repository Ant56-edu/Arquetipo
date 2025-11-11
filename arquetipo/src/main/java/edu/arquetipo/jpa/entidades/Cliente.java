package edu.arquetipo.jpa.entidades;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "Clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "Usuarios")
    Set<Usuario> usuarios;

    private String nombre;
    private String dominioWeb;
    private String correoContacto;
    private String direccion;
    private int tlf;

    public Cliente() {
    }

    public Cliente(String correoContacto, String direccion, String dominioWeb, String nombre, int tlf) {
        this.correoContacto = correoContacto;
        this.direccion = direccion;
        this.dominioWeb = dominioWeb;
        this.nombre = nombre;
        this.tlf = tlf;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", dominioWeb=").append(dominioWeb);
        sb.append(", correoContacto=").append(correoContacto);
        sb.append(", direccion=").append(direccion);
        sb.append(", tlf=").append(tlf);
        sb.append('}');
        return sb.toString();
    }

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

    public String getDominioWeb() {
        return dominioWeb;
    }

    public void setDominioWeb(String dominioWeb) {
        this.dominioWeb = dominioWeb;
    }

    public String getCorreoContacto() {
        return correoContacto;
    }

    public void setCorreoContacto(String correoContacto) {
        this.correoContacto = correoContacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTlf() {
        return tlf;
    }

    public void setTlf(int tlf) {
        this.tlf = tlf;
    }
}
