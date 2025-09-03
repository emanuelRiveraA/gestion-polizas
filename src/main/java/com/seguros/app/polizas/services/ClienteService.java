package com.seguros.app.polizas.services;

import java.util.Optional;

import com.seguros.app.polizas.models.entity.Cliente;

public interface ClienteService {

	Cliente save(Cliente cliente);
	
	Iterable<Cliente> findAll();
	
	Iterable<Cliente> buscarPorFiltro(String filtro);
	
	Optional<Cliente> clienteById(Long id);
	
	void deleteById(Long id);
	
}
