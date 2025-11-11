package edu.arquetipo.jpa.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.arquetipo.jpa.entidades.Usuario;

public class OperativaAuthImplementacion implements OperativaAuthInterfaz {

    @Override
    public String login(String email, String contrasena) {
        List<Usuario> usuarios = new ArrayList<>();
        String token = "";
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equals(email) && usuario.getContrasena().equals(contrasena)) {
                token = UUID.randomUUID().toString();
            }
        }
        return token;
    }

}
