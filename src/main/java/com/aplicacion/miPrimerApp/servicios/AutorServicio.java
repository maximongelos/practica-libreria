package com.aplicacion.miPrimerApp.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aplicacion.miPrimerApp.entidades.Autor;
import com.aplicacion.miPrimerApp.repositorios.AutorRepositorio;

@Service
public class AutorServicio {

	@Autowired
	private AutorRepositorio autorRepositorio;

	public List<Autor> listarAutores() {
		return autorRepositorio.findAll();
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Autor guardarAutor(String nombre) throws Exception {
		Autor nuevoAutor = new Autor(nombre);
		return autorRepositorio.save(nuevoAutor);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Autor altaAutor(String id) {
		Autor autor = autorRepositorio.getById(id);
		autor.setAlta(true);
		return autorRepositorio.save(autor);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Autor bajaAutor(String id) {
		Autor autor = autorRepositorio.getById(id);
		autor.setAlta(false);
		return autorRepositorio.save(autor);
	}
	
	public void eliminarAutor(String id) {
		autorRepositorio.deleteById(id);
	}
}
