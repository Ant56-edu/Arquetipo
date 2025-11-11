package edu.arquetipo.jpa.dao;

import edu.arquetipo.jpa.entidades.Tarea;
import edu.arquetipo.jpa.entidades.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TareaDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("alumnosPU");

    /**
     * Busca una Tarea por su clave primaria (ID).
     *
     * @param id La clave primaria de la tarea.
     * @return El objeto Tarea encontrado, o null si no existe.
     */
    public Tarea buscar(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Tarea.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Inserta una nueva Tarea en la base de datos.
     *
     * @param tarea El objeto Tarea a persistir.
     */
    public void insertar(Tarea tarea) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(tarea);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Actualiza el nombre de una tarea.
     *
     * @param id          El ID de la tarea a actualizar.
     * @param nuevoNombre El nuevo nombre para la tarea.
     */
    public void actualizarNombre(Long id, String nuevoNombre) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Tarea tareaEncontrada = em.find(Tarea.class, id);

            if (tareaEncontrada != null) {
                tareaEncontrada.setNombre(nuevoNombre);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Reemplaza la lista de empleados asignados a una tarea.
     *
     * @param id              El ID de la tarea.
     * @param nuevosEmpleados La nueva lista de empleados (o null/vacía).
     */
    public void actualizarEmpleadosAsignados(Long id, Usuario[] nuevosEmpleados) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Tarea tareaEncontrada = em.find(Tarea.class, id);

            if (tareaEncontrada != null) {
                tareaEncontrada.setEmpleadosAsignados(nuevosEmpleados);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Actualiza el gestor encargado de una tarea.
     *
     * @param id          El ID de la tarea.
     * @param nuevoGestor El objeto Usuario que será el nuevo gestor.
     */
    public void actualizarGestor(Long id, Usuario nuevoGestor) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Tarea tareaEncontrada = em.find(Tarea.class, id);

            if (tareaEncontrada != null) {
                tareaEncontrada.setGestorEncargado(nuevoGestor);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Actualiza el estado de una tarea (e.g., a "Completada", "Bloqueada", etc.).
     *
     * @param id          El ID de la tarea.
     * @param nuevoEstado El nuevo valor del estado (e.g., un String o Enum).
     */
    public void actualizarEstado(Long id, String nuevoEstado) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Tarea tareaEncontrada = em.find(Tarea.class, id);

            if (tareaEncontrada != null) {
                tareaEncontrada.setEstadoTarea(nuevoEstado);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Borra una Tarea de la base de datos usando su ID.
     *
     * @param id El ID de la tarea a borrar.
     */
    public void borrar(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Tarea tareaEncontrada = em.find(Tarea.class, id);

            if (tareaEncontrada != null) {
                em.remove(tareaEncontrada);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Cierra el EntityManagerFactory. Debe llamarse una vez al final de la
     * aplicación.
     */
    public static void cerrarFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}