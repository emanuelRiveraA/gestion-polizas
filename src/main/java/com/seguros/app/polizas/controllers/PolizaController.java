package com.seguros.app.polizas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seguros.app.polizas.models.entity.Poliza;
import com.seguros.app.polizas.models.repository.PolizaRepository;

@RestController
@RequestMapping("/api")
public class PolizaController {
	
	@Autowired
	private PolizaRepository polizaRepository;
	
	@PostMapping("/polizas")
    public ResponseEntity<?> registrarPoliza(@RequestBody Poliza poliza) {
        Poliza creado = polizaRepository.save(poliza);
        return ResponseEntity.ok().body(creado);
    }
	
	 @GetMapping("/polizas")
	 public ResponseEntity<Iterable<?>> listarClientes(){
	      return ResponseEntity.ok(polizaRepository.findAll());
	 }
	

}
