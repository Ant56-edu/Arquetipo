package edu.arquetipo.jpa.servicios;

import java.util.List;

import edu.arquetipo.jpa.entidades.Usuario;

public interface OperativaUsuarioInterfaz {

    // Se cambia el tipo de retorno a Usuario, ya que un método de búsqueda debe
    // devolver el objeto.
    Usuario buscarUsuario(long id);

    // Se cambia el tipo de retorno a Usuario, ya que la operación debe confirmar la
    // creación del objeto.
    Usuario crearUsuario(Usuario usuario);

    // Se cambia el tipo de retorno a Usuario, ya que la operación debería devolver
    // el objeto actualizado.
    Usuario editarUsuario(long id, Usuario usuarioActualizado);

    boolean borrarUsuario(long id);

    List<Usuario> listarUsuarios();

}