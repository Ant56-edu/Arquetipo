package edu.arquetipo.jpa.dao;

import java.util.List;

import edu.arquetipo.jpa.entidades.Subtarea;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.RollbackException;
import jakarta.persistence.TypedQuery;

/**
 * Data Access Object (DAO) para la entidad Subtarea, utilizando JPA/Hibernate.
 * Se encarga de las operaciones CRUD y de búsqueda.
 * * NOTA: Esta implementación asume que la clave primaria de Subtarea es un
 * Long.
 */
public class SubtareaDAO {

    // EntityManagerFactory es estática y final para asegurar que se crea una única
    // vez.
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("alumnosPU");

    /**
     * Busca una Subtarea por su clave primaria (ID).
     *
     * @param id La clave primaria de la subtarea.
     * @return El objeto Subtarea encontrado, o null si no existe.
     */
    public Subtarea buscar(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Subtarea.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Busca una Subtarea por su nombre (utilizando JPQL).
     *
     * @param nombre El nombre exacto de la subtarea.
     * @return El objeto Subtarea encontrado, o null si no existe.
     */
    public Subtarea buscarPorNombre(String nombre) {
        EntityManager em = emf.createEntityManager();
        try {
            // Se utiliza una consulta JPQL parametrizada para buscar por un campo que no es
            // PK.
            String jpql = "SELECT s FROM Subtarea s WHERE s.nombre = :nombreSubtarea";
            TypedQuery<Subtarea> query = em.createQuery(jpql, Subtarea.class);
            query.setParameter("nombreSubtarea", nombre);

            // Retorna el resultado único. Si hay más de uno o ninguno, Hibernate maneja el
            // error.
            List<Subtarea> results = query.getResultList();
            return results.isEmpty() ? null : results.get(0);

        } finally {
            em.close();
        }
    }

    /**
     * Inserta una nueva Subtarea en la base de datos.
     *
     * @param subtarea El objeto Subtarea a persistir.
     */
    public void insertar(Subtarea subtarea) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(subtarea);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            // Se imprime un error útil para depuración.
            System.err.println("Error al insertar la subtarea: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Actualiza el nombre de una subtarea existente.
     *
     * @param id          El ID de la subtarea a actualizar.
     * @param nuevoNombre El nuevo nombre.
     */
    public void actualizarNombre(Long id, String nuevoNombre) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Subtarea subtareaEncontrada = em.find(Subtarea.class, id);

            if (subtareaEncontrada != null) {
                subtareaEncontrada.setNombre(nuevoNombre);
                // em.merge(subtareaEncontrada) no es estrictamente necesario aquí si el objeto
                // está en el contexto de persistencia, pero es una buena práctica para
                // asegurar.
                em.merge(subtareaEncontrada);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al actualizar el nombre de la subtarea: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Borra una Subtarea de la base de datos usando su ID.
     *
     * @param id El ID de la subtarea a borrar.
     */
    public void borrar(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            // Se busca la entidad antes de borrarla
            Subtarea subtareaEncontrada = em.find(Subtarea.class, id);

            if (subtareaEncontrada != null) {
                em.remove(subtareaEncontrada);
            }
            em.getTransaction().commit();
        } catch (RollbackException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al borrar la subtarea (posiblemente tiene dependencias): " + e.getMessage());
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error general al borrar la subtarea: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Cierra el EntityManagerFactory. Debe llamarse una vez al final de la
     * aplicación (por ejemplo, al apagar el contexto de Spring).
     */
    public static void cerrarFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}