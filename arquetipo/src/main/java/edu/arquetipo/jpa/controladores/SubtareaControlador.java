package edu.arquetipo.jpa.controladores;

import java.util.List;

import edu.arquetipo.jpa.entidades.Subtarea; // Asumido
import edu.arquetipo.jpa.servicios.OperativaSubtareaInterfaz; // Asumido

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para la gestión de Subtareas.
 * Mapeado a /api/subtareas
 */
@RestController
@RequestMapping("/api/subtareas")
public class SubtareaControlador {

    private final OperativaSubtareaInterfaz subtareaInterfaz;

    @Autowired
    public SubtareaControlador(OperativaSubtareaInterfaz subtareaInterfaz) {
        this.subtareaInterfaz = subtareaInterfaz;
    }

    /**
     * POST: Crea una nueva subtarea.
     * Ruta: /api/subtareas/nueva
     * 
     * @param subtarea El objeto Subtarea a guardar.
     * @return 201 Created y la subtarea guardada.
     */
    @PostMapping("/nueva")
    public ResponseEntity<Subtarea> crearSubtarea(@RequestBody Subtarea subtarea) {
        // Asume un método crearSubtarea en OperativaSubtareaInterfaz
        Subtarea nuevaSubtarea = subtareaInterfaz.crearSubtarea(subtarea);
        return new ResponseEntity<>(nuevaSubtarea, HttpStatus.CREATED);
    }

    /**
     * GET: Busca una subtarea por ID (o por el campo usado como clave primaria).
     * El DAO usa 'nombre' como clave, por lo que usaremos String en el path.
     * Ruta: /api/subtareas/{nombre}
     * 
     * @param nombre El nombre de la subtarea a buscar.
     * @return 200 OK y la subtarea, o 404 Not Found.
     */
    @GetMapping("/{nombre}")
    public ResponseEntity<Subtarea> obtenerSubtareaPorNombre(@PathVariable String nombre) {
        // Asume un método buscarSubtarea que usa el nombre como clave
        Subtarea subtarea = subtareaInterfaz.buscarSubtarea(nombre);

        if (subtarea != null) {
            return new ResponseEntity<>(subtarea, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * GET: Lista todas las subtareas.
     * Ruta: /api/subtareas/listado
     * 
     * @return 200 OK y la lista de subtareas.
     */
    @GetMapping("/listado")
    public List<Subtarea> listarTodasSubtareas() {
        // Asume un método listarTodasSubtareas en OperativaSubtareaInterfaz
        return subtareaInterfaz.listarTodasSubtareas();
    }

    /**
     * PUT: Actualiza los detalles de una subtarea.
     * Ruta: /api/subtareas/{nombre}
     * 
     * @param nombre              El nombre de la subtarea a actualizar.
     * @param subtareaActualizada El objeto Subtarea con los datos nuevos.
     * @return 200 OK y la subtarea actualizada, o 404 Not Found.
     */
    @PutMapping("/{nombre}")
    public ResponseEntity<Subtarea> actualizarSubtarea(@PathVariable String nombre,
            @RequestBody Subtarea subtareaActualizada) {

        // Asume un método editarSubtarea que toma el nombre y el objeto actualizado
        Subtarea subtareaEditada = subtareaInterfaz.editarSubtarea(nombre, subtareaActualizada);

        if (subtareaEditada != null) {
            return ResponseEntity.ok(subtareaEditada);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * DELETE: Elimina una subtarea por nombre.
     * Ruta: /api/subtareas/borrar/{nombre}
     * 
     * @param nombre El nombre de la subtarea a eliminar.
     * @return 204 No Content si se elimina, o 404 Not Found.
     */
    @DeleteMapping("/borrar/{nombre}")
    public ResponseEntity<String> eliminarSubtarea(@PathVariable String nombre) {
        // Asume un método borrarSubtarea en OperativaSubtareaInterfaz
        boolean eliminado = subtareaInterfaz.borrarSubtarea(nombre);

        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Subtarea con nombre " + nombre + " no encontrada", HttpStatus.NOT_FOUND);
        }
    }
}