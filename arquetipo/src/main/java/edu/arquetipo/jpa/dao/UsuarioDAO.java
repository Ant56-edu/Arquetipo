package edu.arquetipo.jpa.dao;

import edu.arquetipo.jpa.controladores.Inicio;
import edu.arquetipo.jpa.entidades.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UsuarioDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("alumnosPU");

    public void buscar(String nombre) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // Encuentra al usuario por su nombre
        Usuario usuarioEncontrado = em.find(Usuario.class, nombre);
        em.getTransaction().commit();
        em.close();
        System.out.println(usuarioEncontrado.toString());
    }

    public void insertar(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        em.close();
    }

    public void actualizar(String nombre, String cosaACambiar) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // Encuentra al usuario por su nombre
        Usuario usuarioEncontrado = em.find(Usuario.class, nombre);
        switch (cosaACambiar) {
            case "nombre" -> {
                // Limpiado de buffer
                Inicio.entrada.nextLine();
                // Proceso de cambio de nombre
                System.out.println("Escriba el nuevo nombre del usuario: ");
                String nuevoNombre = Inicio.entrada.next();
                usuarioEncontrado.setNombre(nuevoNombre);
            }
            case "telefono" -> {
                // Limpiado de buffer
                Inicio.entrada.nextLine();
                // Proceso de cambio de nombre
                System.out.println("Escriba el nuevo número de teléfono del usuario: ");
                int nuevoTlf = Inicio.entrada.nextInt();
                usuarioEncontrado.setTlf(nuevoTlf);
            }
            case "rol" -> {
                // Limpiado de buffer
                Inicio.entrada.nextLine();
                // Proceso de cambio de nombre
                System.out.println("Escriba el nuevo rol/puesto de trabajo del usuario: ");
                String nuevoRol = Inicio.entrada.next();
                usuarioEncontrado.setRol(nuevoRol);
            }
            case "contraseña" -> {
                // Limpiado de buffer
                Inicio.entrada.nextLine();
                // Proceso de cambio de nombre
                System.out.println("Escriba el nuevo nombre del usuario: ");
                String nuevaContrasena = Inicio.entrada.next();
                usuarioEncontrado.setContrasena(nuevaContrasena);
            }
            default -> {
                System.out.println(
                        "El campo que busca no puede cambiarse o ha ocurrido un error al seleccionar que desea cambiar.");
                break;
            }
        }
        // Se sobrescriben los detalles del usuario
        em.merge(usuarioEncontrado);
        em.getTransaction().commit();
        em.close();
    }

    public void borrar(String nombre) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // Encuentra al usuario por su nombre
        Usuario usuarioEncontrado = em.find(Usuario.class, nombre);
        // Borra al usuario
        em.remove(usuarioEncontrado);
        em.getTransaction().commit();
        em.close();
    }
}
