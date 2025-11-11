package edu.arquetipo.jpa.controladores;

import java.util.List;

import edu.arquetipo.jpa.entidades.RegistroHorario; // Asumido
import edu.arquetipo.jpa.servicios.OperativaRegistroInterfaz; // Asumido

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para la gestión de Registros Horarios (Check-In/Check-Out).
 * Mapeado a /api/registros-horarios
 */
@RestController
@RequestMapping("/api/registros-horarios")
public class RegistroHorarioControlador {

    private final OperativaRegistroInterfaz registroHorarioInterfaz;

    @Autowired
    public RegistroHorarioControlador(OperativaRegistroInterfaz registroHorarioInterfaz) {
        this.registroHorarioInterfaz = registroHorarioInterfaz;
    }

    /**
     * POST: Crea un nuevo registro horario (típicamente solo CheckIn).
     * Ruta: /api/registros-horarios/check-in
     * 
     * @param registro El objeto RegistroHorario a persistir.
     * @return 201 Created y el registro guardado.
     */
    @PostMapping("/check-in")
    public ResponseEntity<RegistroHorario> crearRegistro(@RequestBody RegistroHorario registro) {
        // Asume un método crearRegistro en OperativaRegistroHorarioInterfaz
        RegistroHorario nuevoRegistro = registroHorarioInterfaz.crearRegistro(registro);
        return new ResponseEntity<>(nuevoRegistro, HttpStatus.CREATED);
    }

    /**
     * GET: Busca un registro por ID.
     * Ruta: /api/registros-horarios/{id}
     * 
     * @param id El ID del registro a buscar.
     * @return 200 OK y el registro, o 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RegistroHorario> obtenerRegistroPorId(@PathVariable Long id) {
        // Asume un método buscarRegistro en OperativaRegistroHorarioInterfaz
        RegistroHorario registro = registroHorarioInterfaz.buscarRegistro(id);

        if (registro != null) {
            return new ResponseEntity<>(registro, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * GET: Lista todos los registros horarios.
     * Ruta: /api/registros-horarios/listado
     * 
     * @return 200 OK y la lista de registros.
     */
    @GetMapping("/listado")
    public List<RegistroHorario> listarTodosRegistros() {
        // Asume un método listarTodosRegistros en OperativaRegistroHorarioInterfaz
        return registroHorarioInterfaz.listarTodosRegistros();
    }

    /**
     * PUT: Actualiza el CheckOut (marca la salida) de un registro existente.
     * Ruta: /api/registros-horarios/check-out/{id}
     * Se asume que el cuerpo de la petición contiene el objeto RegistroHorario
     * actualizado.
     * 
     * @param id                  El ID del registro a actualizar.
     * @param registroActualizado El objeto RegistroHorario con la hora de CheckOut.
     * @return 200 OK y el registro actualizado, o 404 Not Found.
     */
    @PutMapping("/check-out/{id}")
    public ResponseEntity<RegistroHorario> actualizarCheckOut(@PathVariable Long id,
            @RequestBody RegistroHorario registroActualizado) {

        // Asume un método actualizarCheckOut que toma el ID y el objeto
        RegistroHorario registroEditado = registroHorarioInterfaz.actualizarCheckOut(id, registroActualizado);

        if (registroEditado != null) {
            return ResponseEntity.ok(registroEditado);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * DELETE: Elimina un registro horario por ID.
     * Ruta: /api/registros-horarios/borrar/{id}
     * 
     * @param id El ID del registro a eliminar.
     * @return 204 No Content si se elimina, o 404 Not Found.
     */
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> eliminarRegistro(@PathVariable Long id) {
        // Asume un método borrarRegistro en OperativaRegistroHorarioInterfaz
        boolean eliminado = registroHorarioInterfaz.borrarRegistro(id);

        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Registro Horario con ID " + id + " no encontrado", HttpStatus.NOT_FOUND);
        }
    }
}