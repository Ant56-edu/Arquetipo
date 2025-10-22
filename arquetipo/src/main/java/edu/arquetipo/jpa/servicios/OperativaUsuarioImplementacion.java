package edu.arquetipo.jpa.servicios;

import edu.arquetipo.jpa.dao.UsuarioDAO;
import edu.arquetipo.jpa.entidades.Usuario;

public class OperativaUsuarioImplementacion implements OperativaUsuarioInterfaz {

    UsuarioDAO dao = new UsuarioDAO();
    Usuario usuario = new Usuario();

    @Override
    public void buscarUsuario(String nombre) {
        dao.buscar(nombre);
    }

    @Override
    public void crearUsuario(Usuario usuario) {
        dao.insertar(usuario);
    }

    @Override
    public void editarDetallesUsuario(String nombre, String cosaACambiar) {
        dao.actualizar(nombre, cosaACambiar);
    }

    @Override
    public void borrarUsuario(String nombre) {
        dao.borrar(nombre);
    }

}
