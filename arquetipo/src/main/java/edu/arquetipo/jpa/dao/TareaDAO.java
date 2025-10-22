package edu.arquetipo.jpa.dao;

import edu.arquetipo.jpa.controladores.Inicio;
import edu.arquetipo.jpa.entidades.Tarea;
import edu.arquetipo.jpa.entidades.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TareaDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("alumnosPU");

    public void buscar(String nombre) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // Encuentra a la tarea por su nombre
        Tarea tareaEncontrada = em.find(Tarea.class, nombre);
        em.getTransaction().commit();
        em.close();
        System.out.println(tareaEncontrada.toString());
    }

    public void insertar(Tarea tarea) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // Guarda al usuario
        em.persist(tarea);
        em.getTransaction().commit();
        em.close();
    }

    public void actualizar(String nombre, String cosaACambiar) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // Encuentra al usuario por su nombre
        Tarea tareaEncontrada = em.find(Tarea.class, nombre);
        switch (cosaACambiar) {
            case "nombre" -> {
                // Limpiado de buffer
                Inicio.entrada.nextLine();
                // Proceso de cambio de nombre
                System.out.println("Escriba el nuevo nombre de la tarea: ");
                String nuevoNombre = Inicio.entrada.next();
                tareaEncontrada.setNombre(nuevoNombre);
            }
            case "empleados" -> {
                // Limpiado de buffer
                Inicio.entrada.nextLine();
                // Proceso de cambio de nombre
                Usuario[] empleadosAsignados = null;
                tareaEncontrada.setEmpleadosAsignados(empleadosAsignados);
            }
            case "gestor" -> {
                // Limpiado de buffer
                Inicio.entrada.nextLine();
                // Proceso de cambio de nombre
                System.out.println("Cambiando al gestor encargado de la tarea.");
                Usuario gestorEncargado = null;
                tareaEncontrada.setGestorEncargado(gestorEncargado);
            }
            default -> {
                System.out.println(
                        "El campo que busca no puede cambiarse o ha ocurrido un error al seleccionar que desea cambiar.");
                break;
            }
        }
        // Se sobrescriben los detalles de la tarea y se suben los cambios
        em.merge(tareaEncontrada);
        em.getTransaction().commit();
        em.close();
    }

    public void cambiarEstado(String nombre) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        // Encuentra a la tarea
        Tarea tareaEncontrada = em.find(Tarea.class, nombre);
        // Cambia su estado a bloqueada, pospuesta, en progreso o completada
        em.merge(tareaEncontrada);
        em.getTransaction().commit();
        em.close();
    }
}
