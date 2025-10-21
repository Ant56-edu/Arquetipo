package edu.arquetipo.jpa.servicios;

import edu.arquetipo.jpa.dao.UsuarioDAO;
import edu.arquetipo.jpa.entidades.Usuario;

public class InsertarImplementacion implements InsertarInterfaz {
    @Override
    public void insertarUsuario(String nombre, int edad, String rol, String email, int tlf, String contrasena) {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario nuevo = new Usuario();
        nuevo.setNombre(nombre);
        nuevo.setEdad(edad);
        nuevo.setRol(rol);
        nuevo.setEmail(email);
        nuevo.setTlf(tlf);
        nuevo.setContraseña(contrasena);
        dao.insertar(nuevo);
    }

}
