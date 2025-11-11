package edu.arquetipo.jpa.servicios;

import java.util.List;

import edu.arquetipo.jpa.entidades.Comentario;

public interface OperativaComentarioInterfaz {

    Comentario crearComentario(Comentario comentario);

    Comentario buscarComentario(Long id);

    List<Comentario> listarTodosComentarios();

    Comentario editarComentario(Long id, Comentario comentarioActualizado);

    boolean borrarComentario(Long id);

}
