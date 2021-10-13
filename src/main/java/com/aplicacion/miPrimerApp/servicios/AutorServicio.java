package com.aplicacion.miPrimerApp.servicios;

import java.util.List;
import java.util.Optional;

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

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Autor guardar(String nombre) throws Exception  {
		
		validar(nombre);
		
		Autor nuevoAutor = new Autor(nombre);
		
		return autorRepositorio.save(nuevoAutor);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Autor alta(String id) throws Exception{
		
		Optional<Autor> result  = autorRepositorio.findById(id);
		
		if (result.isEmpty()) {
			throw new Exception("No se encontró el autor");
		} else {
			Autor a = result.get();
			a.setAlta(true);
			return autorRepositorio.save(a);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Autor baja(String id) throws Exception {
		
		Optional<Autor> result  = autorRepositorio.findById(id);
		
		if (result.isEmpty()) {
			throw new Exception("No se encontró el autor");
		} else {
			Autor a = result.get();
			a.setAlta(false);
			return autorRepositorio.save(a);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Autor editar(String id, String nombre) throws Exception{
		
		Optional<Autor> result  = autorRepositorio.findById(id);
		
		if (result.isEmpty()) {
			throw new Exception("No se encontró el autor");
		} else {
			Autor a = result.get();
			a.setNombre(nombre);
			return autorRepositorio.save(a);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void eliminar(String id) throws Exception {
		
		autorRepositorio.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public List<Autor> listarTodos() {
		
		return autorRepositorio.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Autor> listarActivos() {
		
		return autorRepositorio.buscarActivos();
	}
	
	public void validar(String nombre) throws Exception{
		
		if (nombre == null || nombre.isEmpty() || nombre.contains("  ")) {
			throw new Exception("Debe ingresar el nombre del autor");
		}
		
		if (autorRepositorio.existePorNombre(nombre)) {
			throw new Exception("El autor ya esta cargado");
		}
	}
}
