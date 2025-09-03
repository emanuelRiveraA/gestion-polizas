package com.seguros.app.polizas.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.seguros.app.polizas.models.entity.Cliente;
import com.seguros.app.polizas.services.ClienteService;

@RestController
@RequestMapping("/api")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping("/clientes")
    public ResponseEntity<?> registrarCliente(@RequestBody Cliente cliente) {
        Cliente creado = clienteService.save(cliente);
        return ResponseEntity.ok().body(creado);
    }
	
	   // Editar cliente (solo ADMIN)
//    @PutMapping("/clientes/{id}")
//    public ResponseEntity<Cliente> editarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
//        Cliente actualizado = clienteService.(id, cliente);
//        return ResponseEntity.ok(actualizado);
//    }

    // Listar clientes (solo ADMIN)
//    @GetMapping("/clientes")
//    public ResponseEntity<Iterable<?>> listarClientes(){
//        return ResponseEntity.ok(clienteService.findAll());
//    }
    

    // Obtener todos los clientes o filtrar por nombre, correo o número de identificación 
    @GetMapping("/clientes")
    public ResponseEntity<?> listarClientes(@RequestParam(name = "filtro", required = false) String filtro) {
    	return ResponseEntity.ok(clienteService.buscarPorFiltro(filtro));
    }

    // Eliminar cliente (solo ADMIN)
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
    	clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
