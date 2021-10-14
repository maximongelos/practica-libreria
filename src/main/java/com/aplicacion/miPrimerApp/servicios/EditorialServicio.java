package com.aplicacion.miPrimerApp.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aplicacion.miPrimerApp.entidades.Editorial;
import com.aplicacion.miPrimerApp.repositorios.EditorialRepositorio;

@Service
public class EditorialServicio {

	@Autowired
	private EditorialRepositorio editorialRepositorio;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Editorial guardar(String nombre) throws Exception  {
		
		validar(nombre);
		
		Editorial nuevoAutor = new Editorial(nombre);
		
		return editorialRepositorio.save(nuevoAutor);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Editorial alta(String id) throws Exception{
		
		Optional<Editorial> result  = editorialRepositorio.findById(id);
		
		if (result.isEmpty()) {
			throw new Exception("No se encontró el autor");
		} else {
			Editorial e = result.get();
			e.setAlta(true);
			return editorialRepositorio.save(e);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Editorial baja(String id) throws Exception {
		
		Optional<Editorial> result  = editorialRepositorio.findById(id);
		
		if (result.isEmpty()) {
			throw new Exception("No se encontró el autor");
		} else {
			Editorial e = result.get();
			e.setAlta(false);
			return editorialRepositorio.save(e);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Editorial editar(String id, String nombre) throws Exception{
		
		Optional<Editorial> result  = editorialRepositorio.findById(id);
		
		if (result.isEmpty()) {
			throw new Exception("No se encontró el autor");
		} else {
			Editorial e = result.get();
			e.setNombre(nombre);
			return editorialRepositorio.save(e);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Editorial obtenerPorId(String id) throws Exception{
		
		Optional<Editorial> result  = editorialRepositorio.findById(id);
		
		if (result.isEmpty()) {
			throw new Exception("No se encontró el autor");
		} else {
			return result.get();
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void eliminar(String id) throws Exception {
		
		editorialRepositorio.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public List<Editorial> listarTodos() {
		
		return editorialRepositorio.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Editorial> listarActivos() {
		
		return editorialRepositorio.buscarActivos();
	}
	
	public void validar(String nombre) throws Exception{
		
		if (nombre == null || nombre.isEmpty() || nombre.contains("  ")) {
			throw new Exception("Debe ingresar el nombre de la editorial");
		}
		
		if (editorialRepositorio.existePorNombre(nombre)) {
			throw new Exception("La editorial ya esta cargado");
		}
	}
}

