package edu.arquetipo.jpa.dao;

import edu.arquetipo.jpa.controladores.Inicio;
import edu.arquetipo.jpa.entidades.Subtarea;
import edu.arquetipo.jpa.entidades.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class SubtareaDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("alumnosPU");

    public void buscar(String nombre) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // Encuentra al usuario por su nombre
        Subtarea subtareaEncontrada = em.find(Subtarea.class, nombre);
        em.getTransaction().commit();
        em.close();
        System.out.println(subtareaEncontrada.toString());
    }

    public void insertar(Subtarea subtarea) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // Guarda la subtarea
        em.persist(subtarea);
        em.getTransaction().commit();
        em.close();
    }

    public void actualizar(String nombre, String cosaACambiar) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // Encuentra a la subtarea por su nombre
        Subtarea subtareaEncontrada = em.find(Subtarea.class, nombre);
        switch (cosaACambiar) {
            case "nombre" -> {
                // Limpiado de buffer
                Inicio.entrada.nextLine();
                // Proceso de cambio de nombre
                System.out.println("Escriba el nuevo nombre de la subtarea: ");
                String nuevoNombre = Inicio.entrada.next();
                subtareaEncontrada.setNombre(nuevoNombre);
            }
            case "empleados asignados" -> {
                // Limpiado de buffer
                Inicio.entrada.nextLine();
                // Proceso de cambio de nombre
                System.out.println("Actualizando empleados asignados a la subtarea");
                Usuario[] empleadosAsignados = null;
                subtareaEncontrada.setEmpleadosAsignados(empleadosAsignados);
            }
            default -> {
                System.out.println(
                        "El campo que busca no puede cambiarse o ha ocurrido un error al seleccionar que desea cambiar.");
                break;
            }
        }
        // Se sobrescriben los detalles del usuario y se suben los cambios
        em.merge(subtareaEncontrada);
        em.getTransaction().commit();
        em.close();
    }

    public void borrar(String nombre) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // Encuentra a la subtarea por su nombre
        Subtarea subtareaEncontrada = em.find(Subtarea.class, nombre);
        // Borra la subtarea
        em.remove(subtareaEncontrada);
        em.getTransaction().commit();
        em.close();
    }
}
