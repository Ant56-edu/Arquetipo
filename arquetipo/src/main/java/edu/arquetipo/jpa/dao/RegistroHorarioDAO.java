package edu.arquetipo.jpa.dao;

import java.time.LocalTime;
import edu.arquetipo.jpa.entidades.RegistroHorario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class RegistroHorarioDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("alumnosPU");

    /**
     * Busca un RegistroHorario por su clave primaria (ID).
     *
     * @param id La clave primaria del registro.
     * @return El objeto RegistroHorario encontrado, o null si no existe.
     */
    public RegistroHorario buscar(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(RegistroHorario.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Inserta un nuevo RegistroHorario en la base de datos.
     *
     * @param registro El objeto RegistroHorario a persistir.
     */
    public void insertar(RegistroHorario registro) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(registro);
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
     * Actualiza la hora de CheckIn de un registro.
     *
     * @param id           El ID del registro a actualizar.
     * @param nuevoCheckIn La nueva hora de CheckIn.
     */
    public void actualizarCheckIn(Long id, LocalTime nuevoCheckIn) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            RegistroHorario registroEncontrado = em.find(RegistroHorario.class, id);

            if (registroEncontrado != null) {
                registroEncontrado.setCheckIn(nuevoCheckIn);
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
     * Actualiza la hora de CheckOut de un registro.
     *
     * @param id            El ID del registro a actualizar.
     * @param nuevoCheckOut La nueva hora de CheckOut.
     */
    public void actualizarCheckOut(Long id, LocalTime nuevoCheckOut) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            RegistroHorario registroEncontrado = em.find(RegistroHorario.class, id);

            if (registroEncontrado != null) {
                registroEncontrado.setCheckOut(nuevoCheckOut);
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
     * Cierra el EntityManagerFactory. Debe llamarse al final de la aplicación.
     */
    public static void cerrarFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}