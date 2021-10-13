package com.aplicacion.miPrimerApp.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.aplicacion.miPrimerApp.entidades.Autor;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String>{
	@Query("SELECT a FROM Autor a WHERE a.alta = true")
	public List<Autor> buscarActivos();
	
	@Query("SELECT COUNT(a) > 0 FROM Autor a WHERE a.nombre = :nombre")
	public boolean existePorNombre(@Param("nombre") String nombre);
}
