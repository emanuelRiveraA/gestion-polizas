package com.seguros.app.polizas.services;

import java.util.Optional;

import com.seguros.app.polizas.models.entity.Poliza;

public interface PolizaService {

	Poliza save(Poliza poliza);
	
	Iterable<Poliza> findAll();
	
	Iterable<Poliza> buscarPorFiltro(String filtro);
	
	Optional<Poliza> polizaById(Long id);
	
	void deleteById(Long id);
	
}
