package com.seguros.app.polizas.services.implement;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seguros.app.polizas.models.entity.Poliza;
import com.seguros.app.polizas.models.repository.PolizaRepository;
import com.seguros.app.polizas.services.PolizaService;

@Service
public class PolizaServiceImpl implements PolizaService {
	
	@Autowired
	private PolizaRepository polizaRepository;

	@Override
	public Poliza save(Poliza poliza) {
		return polizaRepository.save(poliza);
	}

	@Override
	public Iterable<Poliza> findAll() {
		return polizaRepository.findAll();
	}

	@Override
	public Iterable<Poliza> buscarPorFiltro(String filtro) {
		if (filtro == null || filtro.isEmpty()) { 
    		return polizaRepository.findAll(); 
    	} 
    	return polizaRepository.buscarPorFiltro(filtro); 
	}

	@Override
	public Optional<Poliza> polizaById(Long id) {
		return polizaRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		polizaRepository.deleteById(id);
	}



}
