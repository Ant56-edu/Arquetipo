package edu.arquetipo.jpa.dao;

import edu.arquetipo.jpa.entidades.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ClienteDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("alumnosPU");

    /**
     * Busca un Cliente por su clave primaria (ID).
     *
     * @param id La clave primaria del cliente.
     * @return El objeto Cliente encontrado, o null si no existe.
     */
    public Cliente buscar(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Inserta un nuevo Cliente en la base de datos.
     *
     * @param cliente El objeto Cliente a persistir.
     */
    public void insertar(Cliente cliente) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(cliente);
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
     * Actualiza el nombre de un cliente.
     *
     * @param id          El ID del cliente a actualizar.
     * @param nuevoNombre El nuevo nombre para el cliente.
     */
    public void actualizarNombre(Long id, String nuevoNombre) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Cliente clienteEncontrado = em.find(Cliente.class, id);

            if (clienteEncontrado != null) {
                clienteEncontrado.setNombre(nuevoNombre);
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
     * Actualiza el dominio web de un cliente.
     *
     * @param id           El ID del cliente a actualizar.
     * @param nuevoDominio El nuevo dominio web.
     */
    public void actualizarDominio(Long id, String nuevoDominio) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Cliente clienteEncontrado = em.find(Cliente.class, id);
            if (clienteEncontrado != null) {
                clienteEncontrado.setDominioWeb(nuevoDominio);
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
     * Actualiza la dirección de un cliente.
     *
     * @param id             El ID del cliente a actualizar.
     * @param nuevaDireccion La nueva dirección.
     */
    public void actualizarDireccion(Long id, String nuevaDireccion) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Cliente clienteEncontrado = em.find(Cliente.class, id);
            if (clienteEncontrado != null) {
                clienteEncontrado.setDireccion(nuevaDireccion);
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
     * Actualiza el teléfono de un cliente.
     *
     * @param id            El ID del cliente a actualizar.
     * @param nuevoTelefono El nuevo número de teléfono.
     */
    public void actualizarTelefono(Long id, int nuevoTelefono) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Cliente clienteEncontrado = em.find(Cliente.class, id);
            if (clienteEncontrado != null) {
                clienteEncontrado.setTlf(nuevoTelefono);
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
     * Borra un Cliente de la base de datos usando su ID.
     *
     * @param id El ID del cliente a borrar.
     */
    public void borrarCliente(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Cliente clienteEncontrado = em.find(Cliente.class, id);

            if (clienteEncontrado != null) {
                em.remove(clienteEncontrado);
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
     * Cierra el EntityManagerFactory. Debe llamarse al final de la aplicación.
     */
    public static void cerrarFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}