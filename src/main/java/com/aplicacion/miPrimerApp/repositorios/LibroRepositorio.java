package com.aplicacion.miPrimerApp.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aplicacion.miPrimerApp.entidades.Libro;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String>{
	@Query("SELECT l FROM Libro l WHERE l.alta = true")
	public List<Libro> buscarActivos();
	
	@Query("SELECT COUNT(l) > 0 FROM Libro l WHERE l.titulo = :titulo")
	public boolean existePorNombre(@Param("titulo") String titulo);
}
