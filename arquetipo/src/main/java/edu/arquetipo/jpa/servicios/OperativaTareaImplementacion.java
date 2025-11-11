package edu.arquetipo.jpa.servicios;

import edu.arquetipo.jpa.dao.TareaDAO;
import edu.arquetipo.jpa.entidades.Tarea;
import edu.arquetipo.jpa.entidades.Usuario;

public class OperativaTareaImplementacion implements OperativaTareaInterfaz {

    TareaDAO dao = new TareaDAO();

    @Override
    public Tarea buscarTarea(long id) {
        return dao.buscar(id);
    }

    @Override
    public Tarea crearTarea(Tarea tarea) {
        dao.insertar(tarea);
        return dao.buscar(tarea.getId());
    }

    /**
     * NOTA IMPORTANTE: Este método de actualización es defectuoso por diseño
     * (no permite pasar el nuevo valor como parámetro). Se han ajustado los valores
     * para usar marcadores o mantener 'null' si la intención es desasignar.
     */
    @Override
    public Tarea editarDetalles(long id, String cosaACambiar) {
        switch (cosaACambiar) {
            case "gestor" -> {
                // Se mantiene 'null' si la intención es desasignar o es un marcador de valor
                // faltante.
                Usuario nuevoGestor = null;
                dao.actualizarGestor(id, nuevoGestor);
                return dao.buscar(id);
            }
            case "empleados" -> {
                // Se mantiene 'null' si la intención es desasignar o es un marcador de valor
                // faltante.
                Usuario[] nuevosEmpleados = null;
                dao.actualizarEmpleadosAsignados(id, nuevosEmpleados);
                return dao.buscar(id);
            }
            case "estado" -> {
                // El nuevo estado (String) debería venir como parámetro. Se usa un marcador.
                String estado = "ESTADO_PENDIENTE_ACTUALIZACION";
                dao.actualizarEstado(id, estado);
                return dao.buscar(id);
            }
            case "nombre" -> {
                // El nuevo nombre (String) debería venir como parámetro. Se usa un marcador.
                String nuevoNombre = "NOMBRE_PENDIENTE_ACTUALIZACION";
                dao.actualizarNombre(id, nuevoNombre);
                return dao.buscar(id);
            }
            default -> {
                System.err.println("Campo de actualización no reconocido: " + cosaACambiar);
                return dao.buscar(id);
            }
        }
    }

    @Override
    public void borrarTarea(long id) {
        dao.borrar(id);
    }

}