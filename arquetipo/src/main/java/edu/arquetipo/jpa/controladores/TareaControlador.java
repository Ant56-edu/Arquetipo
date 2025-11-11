package edu.arquetipo.jpa.controladores;

import java.util.ArrayList;

import edu.arquetipo.jpa.entidades.Tarea;
import edu.arquetipo.jpa.servicios.OperativaTareaInterfaz;
import edu.arquetipo.jpa.servicios.OperativaTareaImplementacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarea")
public class TareaControlador {
    OperativaTareaInterfaz tareaInterfaz = new OperativaTareaImplementacion();

    @Autowired
    public TareaControlador(OperativaTareaInterfaz tareaInterfaz) {
        this.tareaInterfaz = tareaInterfaz;
    }

    // Create a new product.
    @PostMapping("/nueva")
    public ResponseEntity<Tarea> saveProduct(@RequestBody Tarea tarea) {
        Tarea nuevaTarea = tareaInterfaz.crearTarea(tarea);
        return ResponseEntity.ok(nuevaTarea);
    }

    // Get all products.
    @GetMapping("/listado")
    public List<Tarea> getAllProducts() {
        List<Tarea> listaTareas = new ArrayList<>();
        for (Tarea tarea : listaTareas) {
            listaTareas.add(tareaInterfaz.buscarTarea(tarea.getId()));
        }
        return listaTareas;
    }

    // Get a product by ID.
    @GetMapping("/{id}")
    public ResponseEntity<Tarea> getTareaById(@PathVariable Long id) {
        Tarea tarea = tareaInterfaz.buscarTarea(id);
        if (tarea != null) {
            return new ResponseEntity<>(tarea, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    // Update a product by ID.
    @PutMapping("/{id}/{cosaACambiar}")
    public ResponseEntity<Tarea> updateTarea(@PathVariable Long id, @RequestBody Tarea tarea,
            String cosaACambiar) {
        Tarea tareaActualizada = tareaInterfaz.editarDetalles(id, cosaACambiar);
        return ResponseEntity.ok(tareaActualizada);
    }

    // Delete a product by ID.
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> deleteTarea(@PathVariable Long id) {
        tareaInterfaz.borrarTarea(id);
        return ResponseEntity.ok("Product deleted successfully");
    }
}
