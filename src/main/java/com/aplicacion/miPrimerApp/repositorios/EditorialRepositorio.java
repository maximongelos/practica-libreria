package com.aplicacion.miPrimerApp.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.aplicacion.miPrimerApp.entidades.Editorial;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String>{
	@Query("SELECT e FROM Editorial e WHERE e.alta = true")
	public List<Editorial> buscarActivos();
	
	@Query("SELECT COUNT(e) > 0 FROM Editorial e WHERE e.nombre = :nombre")
	public boolean existePorNombre(@Param("nombre") String nombre);
}
