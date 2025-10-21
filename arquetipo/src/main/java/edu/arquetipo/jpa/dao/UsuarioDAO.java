package edu.arquetipo.jpa.dao;

import edu.arquetipo.jpa.controladores.Inicio;
import edu.arquetipo.jpa.entidades.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UsuarioDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("alumnosPU");

    public void insertar(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        em.close();
    }

    public void actualizar(Usuario usuario, String cosaACambiar) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // Encuentra al usuario por su nombre
        Usuario usuarioEncontrado = em.find(Usuario.class, usuario.getNombre());
        switch (cosaACambiar) {
            case "nombre" -> {
                // Limpiado de buffer
                Inicio.entrada.nextLine();
                // Proceso de cambio de nombre
                System.out.println("Escriba el nuevo nombre del usuario: ");
                String nuevoNombre = Inicio.entrada.next();
                usuario.setNombre(nuevoNombre);
            }
            /*
             * case "fechaDeNacimiento" -> {
             * // Limpiado de buffer
             * Inicio.entrada.nextLine();
             * // Proceso de cambio de nombre
             * System.out.println("Escriba el nuevo nombre del usuario: ");
             * String nuevoNombre = Inicio.entrada.next();
             * usuario.setNombre(nuevoNombre);
             * }
             */
            case "telefono" -> {
                // Limpiado de buffer
                Inicio.entrada.nextLine();
                // Proceso de cambio de nombre
                System.out.println("Escriba el nuevo nombre del usuario: ");
                int nuevoTlf = Inicio.entrada.nextInt();
                usuario.setTlf(nuevoTlf);
            }
            case "rol" -> {
            }
            case "contraseña" -> {
            }
            default -> {
                System.out.println(
                        "El campo que busca no puede cambiarse o ha ocurrido un error al seleccionar que desea cambiar.");
            }
        }
    }
}
