package edu.arquetipo.jpa.servicios;

import java.util.List;

// Se elimina la importación de Inicio, ya que la capa de servicio no debe manejar I/O.
import edu.arquetipo.jpa.dao.UsuarioDAO;
import edu.arquetipo.jpa.entidades.Usuario;

public class OperativaUsuarioImplementacion implements OperativaUsuarioInterfaz {

    UsuarioDAO dao = new UsuarioDAO();

    @Override
    public Usuario buscarUsuario(long id) {
        return dao.buscar(id);
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        dao.insertar(usuario);
        return dao.buscar(usuario.getId());
    }

    @Override
    public Usuario editarUsuario(long id, Usuario usuarioActualizado) {
        
        return dao.actualizar(usuarioActualizado);
    }

    @Override
    public boolean borrarUsuario(long id) {
        return dao.borrar(id);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return dao.listarTodos();
    }

}