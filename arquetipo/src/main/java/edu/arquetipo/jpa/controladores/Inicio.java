package edu.arquetipo.jpa.controladores;

import java.util.Scanner;

import edu.arquetipo.jpa.servicios.OperativaUsuarioImplementacion;
import edu.arquetipo.jpa.servicios.OperativaUsuarioInterfaz;

public class Inicio {

    public static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        OperativaUsuarioInterfaz operativaUsuario = new OperativaUsuarioImplementacion();
        System.out.println("Arquetipo: TaskManager");
        System.out.println("base de datos suada: PostgreSQL");
    }
}