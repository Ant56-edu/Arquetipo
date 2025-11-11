package edu.arquetipo.jpa.controladores;

import java.util.List;

import edu.arquetipo.jpa.entidades.Cliente;
import edu.arquetipo.jpa.servicios.OperativaClienteInterfaz; // Asumido

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteControlador {

    // Inicialización del servicio de clientes
    private final OperativaClienteInterfaz clienteInterfaz;

    // Inyección de dependencia a través del constructor
    @Autowired
    public ClienteControlador(OperativaClienteInterfaz clienteInterfaz) {
        this.clienteInterfaz = clienteInterfaz;
    }

    /**
     * POST: Crea un nuevo cliente.
     * Ruta: /api/clientes/nueva
     * 
     * @param cliente El objeto Cliente a guardar.
     * @return 201 Created y el cliente guardado.
     */
    @PostMapping("/nueva")
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        // Se asume la existencia de un método crearCliente en el servicio
        Cliente nuevoCliente = clienteInterfaz.crearCliente(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    /**
     * GET: Lista todos los clientes.
     * Ruta: /api/clientes/listado
     * 
     * @return Lista de todos los clientes.
     */
    @GetMapping("/listado")
    public List<Cliente> listarTodosClientes() {
        // Se asume la existencia de un método listarTodosClientes en el servicio
        return clienteInterfaz.listarTodosClientes();
    }

    /**
     * GET: Busca un cliente por ID.
     * Ruta: /api/clientes/{id}
     * 
     * @param id El ID del cliente a buscar.
     * @return 200 OK y el cliente, o 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
        // Se asume la existencia de un método buscarCliente en el servicio
        Cliente cliente = clienteInterfaz.buscarCliente(id);

        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * PUT: Actualiza un cliente existente.
     * Ruta: /api/clientes/{id}
     * 
     * @param id                 El ID del cliente a actualizar.
     * @param clienteActualizado El objeto Cliente con los datos nuevos.
     * @return 200 OK y el cliente actualizado, o 404 Not Found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteActualizado) {

        // Se asume la existencia de un método editarCliente que toma el ID y el objeto
        Cliente clienteEditado = clienteInterfaz.editarCliente(id, clienteActualizado);

        if (clienteEditado != null) {
            return ResponseEntity.ok(clienteEditado);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * DELETE: Elimina un cliente por ID.
     * Ruta: /api/clientes/borrar/{id}
     * 
     * @param id El ID del cliente a eliminar.
     * @return 204 No Content si se elimina, o 404 Not Found.
     */
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable Long id) {
        // Se asume la existencia de un método borrarCliente en el servicio
        boolean eliminado = clienteInterfaz.borrarCliente(id);

        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Cliente con ID " + id + " no encontrado", HttpStatus.NOT_FOUND);
        }
    }
}