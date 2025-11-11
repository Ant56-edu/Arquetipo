package edu.arquetipo.jpa.servicios;

import java.util.List;

import edu.arquetipo.jpa.entidades.RegistroHorario;

public interface OperativaRegistroInterfaz {

    RegistroHorario crearRegistro(RegistroHorario registro);

    RegistroHorario buscarRegistro(Long id);

    List<RegistroHorario> listarTodosRegistros();

    RegistroHorario actualizarCheckOut(Long id, RegistroHorario registroActualizado);

    boolean borrarRegistro(Long id);

}
