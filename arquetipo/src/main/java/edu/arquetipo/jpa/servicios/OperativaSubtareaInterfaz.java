package edu.arquetipo.jpa.servicios;

import java.util.List;

import edu.arquetipo.jpa.entidades.Subtarea;

public interface OperativaSubtareaInterfaz {

    Subtarea crearSubtarea(Subtarea subtarea);

    Subtarea buscarSubtarea(String nombre);

    List<Subtarea> listarTodasSubtareas();

    Subtarea editarSubtarea(String nombre, Subtarea subtareaActualizada);

    boolean borrarSubtarea(String nombre);

}
