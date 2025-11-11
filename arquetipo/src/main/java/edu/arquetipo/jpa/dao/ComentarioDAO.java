package edu.arquetipo.jpa.dao;

import edu.arquetipo.jpa.entidades.Comentario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ComentarioDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("alumnosPU");

    /**
     * Busca un Comentario por su clave primaria (ID).
     *
     * @param id La clave primaria del comentario.
     * @return El objeto Comentario encontrado, o null si no existe.
     */
    public Comentario buscar(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Comentario.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Inserta un nuevo Comentario en la base de datos.
     *
     * @param comentario El objeto Comentario a persistir.
     */
    public void insertar(Comentario comentario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(comentario);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.getMessage();
        } finally {
            em.close();
        }
    }

    /**
     * Actualiza el contenido de un Comentario existente.
     *
     * @param idComentario   El ID del comentario a actualizar.
     * @param nuevoContenido El nuevo texto para el comentario.
     */
    public void actualizarContenido(Long idComentario, String nuevoContenido) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Comentario comentarioEncontrado = em.find(Comentario.class, idComentario);

            if (comentarioEncontrado != null) {
                comentarioEncontrado.setContenido(nuevoContenido);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.getMessage();
        } finally {
            em.close();
        }
    }

    /**
     * Borra un Comentario de la base de datos usando su ID.
     *
     * @param id El ID del comentario a borrar.
     */
    public void borrarComentario(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Comentario comentarioEncontrado = em.find(Comentario.class, id);

            if (comentarioEncontrado != null) {
                em.remove(comentarioEncontrado);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.getMessage();
        } finally {
            em.close();
        }
    }

    public static void cerrarFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}