package edu.arquetipo.jpa.servicios;

import edu.arquetipo.jpa.entidades.Usuario;

public interface OperativaUsuarioInterfaz {

    void buscarUsuario(String nombre);

    void crearUsuario(Usuario usuario);

    void editarDetalles(String nombre, String cosaACambiar);

    void borrarUsuario(String nombre);

}
