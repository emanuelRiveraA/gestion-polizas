package com.seguros.app.polizas.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seguros.app.polizas.models.entity.Cliente;
import com.seguros.app.polizas.models.entity.Poliza;
import com.seguros.app.polizas.models.repository.PolizaRepository;
import com.seguros.app.polizas.services.PolizaService;

@RestController
@RequestMapping("/api")
public class PolizaController {
	
	@Autowired
	private PolizaService polizaService;
	
	@PostMapping("/polizas")
    public ResponseEntity<?> registrarPoliza(@RequestBody Poliza poliza) {
        Poliza creado = polizaService.save(poliza);
        return ResponseEntity.ok().body(creado);
    }
	
	 @GetMapping("/polizas")
	 public ResponseEntity<Iterable<?>> listarPolizas(){
	      return ResponseEntity.ok(polizaService.findAll());
	 }
	 
	 @GetMapping("/polizas/{id}")
	 public ResponseEntity<?> polizaById(@PathVariable(name = "id") Long id) {
		 // llamar al servicio para buscar una poliza por su ID
		 Optional<Poliza> o = polizaService.polizaById(id);
		 // si la poliza se encuentra, devuelve una respuesta HTTP 404 (Not Found)
		 if (o.isEmpty()) {
			 return ResponseEntity.notFound().build();
		 }
		 // si la poliza existe, devuelve un HTTP 200 (ok) con la poliza en el cuerpo
		 return ResponseEntity.ok().body(o.get());
	 }
	
	 // Obtener todos los ...
//	 @GetMapping("/polizas")
//	 public ResponseEntity<?> listarPolizas(@RequestParam(name = "filtro", required = false) String filtro) {
//	   	return ResponseEntity.ok(polizaRepository.buscarPorFiltro(filtro));
//	 }

}
