package edu.arquetipo.jpa.controladores;

import java.util.List;

import edu.arquetipo.jpa.entidades.Comentario; // Asumido
import edu.arquetipo.jpa.servicios.OperativaComentarioInterfaz; // Asumido

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para la gestión de Comentarios.
 * Mapeado a /api/comentarios
 */
@RestController
@RequestMapping("/api/comentarios")
public class ComentarioControlador {

    private final OperativaComentarioInterfaz comentarioInterfaz;

    @Autowired
    public ComentarioControlador(OperativaComentarioInterfaz comentarioInterfaz) {
        this.comentarioInterfaz = comentarioInterfaz;
    }

    /**
     * POST: Crea un nuevo comentario.
     * Ruta: /api/comentarios/nueva
     * 
     * @param comentario El objeto Comentario a guardar.
     * @return 201 Created y el comentario guardado.
     */
    @PostMapping("/nueva")
    public ResponseEntity<Comentario> crearComentario(@RequestBody Comentario comentario) {
        // Asume un método crearComentario en OperativaComentarioInterfaz
        Comentario nuevoComentario = comentarioInterfaz.crearComentario(comentario);
        return new ResponseEntity<>(nuevoComentario, HttpStatus.CREATED);
    }

    /**
     * GET: Busca un comentario por ID.
     * Ruta: /api/comentarios/{id}
     * 
     * @param id El ID del comentario a buscar.
     * @return 200 OK y el comentario, o 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Comentario> obtenerComentarioPorId(@PathVariable Long id) {
        // Asume un método buscarComentario en OperativaComentarioInterfaz
        Comentario comentario = comentarioInterfaz.buscarComentario(id);

        if (comentario != null) {
            return new ResponseEntity<>(comentario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * GET: Lista todos los comentarios.
     * Ruta: /api/comentarios/listado
     * 
     * @return 200 OK y la lista de comentarios.
     */
    @GetMapping("/listado")
    public List<Comentario> listarTodosComentarios() {
        // Asume un método listarTodosComentarios en OperativaComentarioInterfaz
        return comentarioInterfaz.listarTodosComentarios();
    }

    /**
     * PUT: Actualiza el contenido de un comentario.
     * Ruta: /api/comentarios/{id}
     * 
     * @param id                    El ID del comentario a actualizar.
     * @param comentarioActualizado El objeto Comentario con el nuevo contenido.
     * @return 200 OK y el comentario actualizado, o 404 Not Found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Comentario> actualizarComentario(@PathVariable Long id,
            @RequestBody Comentario comentarioActualizado) {

        // Asume un método editarComentario que toma el ID y el objeto actualizado
        Comentario comentarioEditado = comentarioInterfaz.editarComentario(id, comentarioActualizado);

        if (comentarioEditado != null) {
            return ResponseEntity.ok(comentarioEditado);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * DELETE: Elimina un comentario por ID.
     * Ruta: /api/comentarios/borrar/{id}
     * 
     * @param id El ID del comentario a eliminar.
     * @return 204 No Content si se elimina, o 404 Not Found.
     */
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> eliminarComentario(@PathVariable Long id) {
        // Asume un método borrarComentario en OperativaComentarioInterfaz
        boolean eliminado = comentarioInterfaz.borrarComentario(id);

        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Comentario con ID " + id + " no encontrado", HttpStatus.NOT_FOUND);
        }
    }
}