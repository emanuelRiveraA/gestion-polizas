package com.seguros.app.polizas.services.implement;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seguros.app.polizas.models.entity.Cliente;
import com.seguros.app.polizas.models.repository.ClienteRepository;
import com.seguros.app.polizas.services.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public Iterable<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	@Override
	public Iterable<Cliente> buscarPorFiltro(String filtro) {
		if (filtro == null || filtro.isEmpty()) { 
    		return clienteRepository.findAll(); 
    	} 
    	return clienteRepository.buscarPorFiltro(filtro); 
	}


	@Override
	public Optional<Cliente> clienteById(Long id) {
		return clienteRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	

}
