package com.seguros.app.polizas.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.seguros.app.polizas.models.entity.Poliza;
import com.seguros.app.polizas.models.repository.PolizaRepository;
import com.seguros.app.polizas.services.ClienteService;
import com.seguros.app.polizas.services.PolizaService;

@RestController
@RequestMapping("/api")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private PolizaService polizaService;
	
	@PostMapping("/clientes")
    public ResponseEntity<?> registrarCliente(@RequestBody Cliente cliente) {
        Cliente creado = clienteService.save(cliente);
        return ResponseEntity.ok().body(creado);
    }
	
	   // Editar cliente (solo ADMIN)
    @PutMapping("/clientes/{id}")
    public ResponseEntity<?> editarCliente(@PathVariable(name = "id") Long id, @RequestBody Cliente cliente) {
       //se busca en la BD por ID
    	Optional<Cliente> o = clienteService.clienteById(id);
    	//se valida que exista el registro
    	if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
    	//si existe lo obtenemos y lo guardamos en una nueva variable
    	Cliente clienteDB = o.get();
    	clienteDB.setNumeroIdentificacion(cliente.getNumeroIdentificacion());
    	clienteDB.setNombreCompleto(cliente.getNombreCompleto());
    	clienteDB.setCorreo(cliente.getCorreo());
    	clienteDB.setTelefono(cliente.getTelefono());
    	clienteDB.setDireccion(cliente.getDireccion());
    	//lo persistimos con el metodo save. Nos retonara el objeto actualizado
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(clienteDB));
    }

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
    
    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> clienteById(@PathVariable(name = "id") Long id) {
    	//llamar al servicio para buscar un cliente por su ID
    	Optional<Cliente> o = clienteService.clienteById(id);
    	//si el cliente se encuentra, devuelve una respuesta HTTP 404 (Not Found)
    	if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
    	//si el cliente existe, devuelve un HTTP 200 (ok) con el cliente en el cuerpo
        return ResponseEntity.ok().body(o.get());
    }
    
    @PutMapping("/clientes/{clientId}/asignar-poliza/{polizaId}")
    public ResponseEntity<?> asignarPoliza(@PathVariable(name = "clientId") Long clientId, @PathVariable(name = "polizaId") Long polizaId){
    	Optional<Cliente> cliente = clienteService.clienteById(clientId);
    	if (cliente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
    	Optional<Poliza> poliza = polizaService.polizaById(polizaId);
    	if (poliza.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
        // Asociar la póliza al cliente
        poliza.get().setCliente(cliente.get());        

        // Agregar la póliza a la lista de pólizas del cliente
        if (cliente.get().getPolizas() == null) {
            cliente.get().setPolizas(new ArrayList<>());
        }
        if (!cliente.get().getPolizas().contains(poliza.get())) {
            cliente.get().getPolizas().add(poliza.get());
        }
        
        // Guardar la póliza para actualizar la relación
        polizaService.save(poliza.get());
    
    	return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(cliente.get()));
    }


    // Eliminar cliente (solo ADMIN)
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
    	clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
