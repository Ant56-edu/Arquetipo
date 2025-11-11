package edu.arquetipo.jpa.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import edu.arquetipo.jpa.entidades.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.RollbackException;
import jakarta.persistence.TypedQuery;

public class UsuarioDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("alumnosPU");
    private final SessionFactory sessionFactory = null;

    /**
     * Busca un Usuario por su clave primaria (ID).
     *
     * @param id La clave primaria del usuario.
     * @return El objeto Usuario encontrado, o null si no existe.
     */
    public Usuario buscar(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Usuario.class, id);
        }
    }

    /**
     * Busca un Usuario por su correo electrónico corporativo.
     *
     * @param email El correo electrónico corporativo del usuario.
     * @return El objeto Usuario encontrado, o null si no existe.
     */
    public Usuario buscar(String email) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM User WHERE email = :email";
            Query<Usuario> query = session.createQuery(hql, Usuario.class);
            query.setParameter("email", email);
            return query.uniqueResult();
        }
    }

    public List<Usuario> listarTodos() {
        EntityManager em = emf.createEntityManager();
        List<Usuario> usuarios = null;
        try {
            // JPQL: "SELECT u FROM Usuario u" selecciona todos los objetos Usuario.
            // El nombre 'Usuario' debe coincidir con el nombre de tu clase de entidad.
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
            
            // Ejecuta la consulta y obtiene la lista de resultados
            usuarios = query.getResultList();
            
        } catch (Exception e) {
            System.err.println("Error al listar todos los usuarios: " + e.getMessage());
            e.getMessage();
            // Retornar null o una lista vacía es mejor que lanzar la excepción al exterior
            usuarios = java.util.Collections.emptyList(); 
        } finally {
            em.close();
        }
        return usuarios;
    }

    /**
     * Inserta un nuevo Usuario en la base de datos.
     *
     * @param usuario El objeto Usuario a persistir.
     */
    public void insertar(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(usuario);
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
     * Actualiza el usuario completo con los nuevos datos.
     *
     * @param usuario El objeto Usuario (con el ID y los campos actualizados)
     * que se va a persistir.
     */
    public Usuario actualizar(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Usuario usuarioEncontrado = em.find(Usuario.class, usuario.getId());
        try {
            // 1. Encontrar la entidad gestionada (managed) por su ID

            if (usuarioEncontrado != null) {
                // 2. Aplicar solo los cambios deseados o todos los campos del objeto de entrada
                // Copia de propiedades: La mejor práctica es usar em.merge() si el objeto
                // 'usuario' ya trae todos los datos correctos (actualizados y sin actualizar).
                // Pero si el objeto 'usuario' solo tiene los campos a cambiar,
                // debemos copiar campo por campo para evitar poner 'null' donde no se debe.

                if (usuario.getNombre() != null) {
                    usuarioEncontrado.setNombre(usuario.getNombre());
                }
                // Asumo que si el teléfono es 0 o un valor por defecto, no se actualiza.
                // Si el teléfono es un tipo primitivo (int), se debe tener cuidado con el valor por defecto (0).
                // Si la entidad usa Integer (clase) y no int (primitivo), podemos verificar si es null.
                // Aquí, asumo que setTlf acepta int y si no quieres cambiarlo, el valor de entrada es el original
                // o, idealmente, se usa un DTO más específico. Para simplificar, actualizaremos si es distinto del valor actual.
                if (usuario.getTlf() != usuarioEncontrado.getTlf() && usuario.getTlf() != 0) {
                    usuarioEncontrado.setTlf(usuario.getTlf());
                }

                if (usuario.getRol() != null) {
                    usuarioEncontrado.setRol(usuario.getRol());
                }

                if (usuario.getContrasena() != null) {
                    usuarioEncontrado.setContrasena(usuario.getContrasena());
                }
                
                // 3. El Entity Manager detecta el cambio automáticamente, solo se hace commit.
            }
            em.getTransaction().commit();
            return usuarioEncontrado;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.getMessage();
        } finally {
            em.close();
        }
        return usuarioEncontrado;
    }

    /**
     * Borra un Usuario de la base de datos usando su ID.
     *
     * @param id El ID del usuario a borrar.
     */
    public boolean  borrar(Long id) {
        boolean conclusion = false;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Usuario usuarioEncontrado = em.find(Usuario.class, id);

            if (usuarioEncontrado != null) {
                em.remove(usuarioEncontrado);
                conclusion = true;
            }
            em.getTransaction().commit();
        } catch (RollbackException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al borrar el usuario (posiblemente tiene tareas asociadas): " + e.getMessage());
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.getMessage();
        } finally {
            em.close();
        }
        return conclusion;
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