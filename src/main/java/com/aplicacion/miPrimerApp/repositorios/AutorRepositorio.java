package com.aplicacion.miPrimerApp.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.aplicacion.miPrimerApp.entidades.Autor;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String>{

}
