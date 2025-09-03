package com.seguros.app.polizas.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seguros.app.polizas.models.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
}
