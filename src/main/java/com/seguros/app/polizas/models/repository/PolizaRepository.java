package com.seguros.app.polizas.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.seguros.app.polizas.models.entity.Poliza;

@Repository
public interface PolizaRepository extends JpaRepository<Poliza, Long> {
	
		// Consulta personalizada para filtrar por nombre, correo o número de identificación (case insensitive) 
		@Query("SELECT p FROM Poliza p WHERE " + 
				"LOWER(p.tipoPoliza) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " + 
				"LOWER(p.estado) LIKE LOWER(CONCAT('%', :filtro, '%'))") 
		Iterable<Poliza> buscarPorFiltro(@Param("filtro") String filtro);
	
}
