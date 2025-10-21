package edu.arquetipo.jpa.controladores;

import java.util.Scanner;

import edu.arquetipo.jpa.servicios.InsertarImplementacion;
import edu.arquetipo.jpa.servicios.InsertarInterfaz;

public class Inicio {

    public static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        InsertarInterfaz insertar = new InsertarImplementacion();
        System.out.println("Arquetipo: TaskManager");
        System.out.println("base de datos suada: PostgreSQL");
    }
}