package edu.arquetipo.jpa.servicios;

import edu.arquetipo.jpa.dao.UsuarioDAO;
import edu.arquetipo.jpa.entidades.Usuario;

public class OperativaUsuarioImplementacion implements OperativaUsuarioInterfaz {

    UsuarioDAO dao = new UsuarioDAO();
    Usuario usuario = new Usuario();

    @Override
    public void buscarUsuario(String nombre) {
        // TODO: Lógica de la opción
        dao.buscar(nombre);
    }

    @Override
    public void crearUsuario(Usuario usuario) {
        // TODO: Lógica de la opción
        dao.insertar(usuario);
    }

    @Override
    public void editarDetalles(String nombre, String cosaACambiar) {
        // TODO: Lógica de la opción
        dao.actualizar(nombre, cosaACambiar);
    }

    @Override
    public void borrarUsuario(String nombre) {
        // TODO: Lógica de la opción
        dao.borrar(nombre);
    }

}
