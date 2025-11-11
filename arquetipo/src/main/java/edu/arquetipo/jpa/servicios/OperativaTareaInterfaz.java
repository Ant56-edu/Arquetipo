package edu.arquetipo.jpa.servicios;

import edu.arquetipo.jpa.entidades.Tarea;

public interface OperativaTareaInterfaz {

    Tarea buscarTarea(long id);

    Tarea crearTarea(Tarea tarea);

    Tarea editarDetalles(long id, String cosaACambiar);

    void borrarTarea(long id);

}
