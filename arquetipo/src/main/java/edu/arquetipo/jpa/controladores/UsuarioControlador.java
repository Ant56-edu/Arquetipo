package edu.arquetipo.jpa.controladores;

import java.util.List;

import edu.arquetipo.jpa.entidades.Usuario;
import edu.arquetipo.jpa.servicios.OperativaUsuarioInterfaz; // Asumido

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlador {

    // Inicialización del servicio de usuarios
    private final OperativaUsuarioInterfaz usuarioInterfaz;

    // Inyección de dependencia a través del constructor
    @Autowired
    public UsuarioControlador(OperativaUsuarioInterfaz usuarioInterfaz) {
        this.usuarioInterfaz = usuarioInterfaz;
    }

    /**
     * POST: Crea un nuevo usuario.
     * Ruta: /api/usuarios/nueva
     * 
     * @param usuario El objeto Usuario a guardar.
     * @return 201 Created y el usuario guardado.
     */
    @PostMapping("/nueva")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        // Se asume la existencia de un método crearUsuario en el servicio
        Usuario nuevoUsuario = usuarioInterfaz.crearUsuario(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    /**
     * GET: Lista todos los usuarios.
     * Ruta: /api/usuarios/listado
     * 
     * @return Lista de todos los usuarios.
     */
    @GetMapping("/listado")
    public List<Usuario> listarTodosUsuarios() {
        // Se asume la existencia de un método listarTodosUsuarios en el servicio
        return usuarioInterfaz.listarUsuarios();
    }

    /**
     * GET: Busca un usuario por ID.
     * Ruta: /api/usuarios/{id}
     * 
     * @param id El ID del usuario a buscar.
     * @return 200 OK y el usuario, o 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        // Se asume la existencia de un método buscarUsuario en el servicio
        Usuario usuario = usuarioInterfaz.buscarUsuario(id);

        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * PUT: Actualiza un usuario existente.
     * Ruta: /api/usuarios/{id}
     * 
     * @param id                 El ID del usuario a actualizar.
     * @param usuarioActualizado El objeto Usuario con los datos nuevos.
     * @return 200 OK y el usuario actualizado, o 404 Not Found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioActualizado) {

        // Se asume la existencia de un método editarUsuario que toma el ID y el objeto
        Usuario usuarioEditado = usuarioInterfaz.editarUsuario(id, usuarioActualizado);

        if (usuarioEditado != null) {
            return ResponseEntity.ok(usuarioEditado);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * DELETE: Elimina un usuario por ID.
     * Ruta: /api/usuarios/borrar/{id}
     * 
     * @param id El ID del usuario a eliminar.
     * @return 204 No Content si se elimina, o 404 Not Found.
     */
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        // Se asume la existencia de un método borrarUsuario en el servicio
        boolean eliminado = usuarioInterfaz.borrarUsuario(id);

        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Usuario con ID " + id + " no encontrado", HttpStatus.NOT_FOUND);
        }
    }
}