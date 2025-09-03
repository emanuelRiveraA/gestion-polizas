package com.seguros.app.polizas.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.seguros.app.polizas.models.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	// Consulta personalizada para filtrar por nombre, correo o número de identificación (case insensitive) 
	@Query("SELECT c FROM Cliente c WHERE " + 
			"LOWER(c.nombreCompleto) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " + 
			"LOWER(c.correo) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " + 
			"LOWER(c.numeroIdentificacion) LIKE LOWER(CONCAT('%', :filtro, '%'))") 
	Iterable<Cliente> buscarPorFiltro(@Param("filtro") String filtro);
}
