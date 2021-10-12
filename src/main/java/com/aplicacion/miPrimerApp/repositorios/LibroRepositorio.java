package com.aplicacion.miPrimerApp.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.aplicacion.miPrimerApp.entidades.Libro;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String>{
	
}
