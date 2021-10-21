package com.aplicacion.miPrimerApp.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aplicacion.miPrimerApp.entidades.Autor;
import com.aplicacion.miPrimerApp.entidades.Editorial;
import com.aplicacion.miPrimerApp.entidades.Libro;
import com.aplicacion.miPrimerApp.repositorios.LibroRepositorio;

@Service
public class LibroServicio {

	@Autowired
	private LibroRepositorio libroRepositorio;
	@Autowired
	private AutorServicio autorServicio;
	@Autowired
	private EditorialServicio editorialServicio;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Libro guardar(String isbn, String titulo, String anio, String ejemplares, String autorID, String editorialID) throws Exception {
		
		this.validar(isbn, titulo, anio, ejemplares);
		
		Long isbnN = Long.parseLong(isbn);
		Integer anioN = Integer.parseInt(anio);
		Integer ejemplaresN = Integer.parseInt(ejemplares);
		
		Autor autor = autorServicio.obtenerPorId(autorID);
		Editorial editorial = editorialServicio.obtenerPorId(editorialID);
		Libro nuevoLibro = new Libro(isbnN, titulo, anioN, ejemplaresN, autor, editorial);
		return libroRepositorio.save(nuevoLibro);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Libro alta(String id) throws Exception{
		
			Libro l = this.obtenerPorId(id);
			l.setAlta(true);
			return libroRepositorio.save(l);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Libro baja(String id) throws Exception{
		
			Libro l = this.obtenerPorId(id);
			l.setAlta(false);
			return libroRepositorio.save(l);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Libro obtenerPorId(String id) throws Exception{
		
		Optional<Libro> result  = libroRepositorio.findById(id);
		
		if (result.isEmpty()) {
			throw new Exception("No se encontró el libro");
		} else {
			return result.get();
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void eliminar(String id) throws Exception {
		
		libroRepositorio.deleteById(id);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Libro editar(String id, String titulo, String isbn, String anio, String ejemplares, String autorID, String editorialID) throws Exception {
		
		Long isbnN = Long.parseLong(isbn);
		Integer anioN = Integer.parseInt(anio);
		Integer ejemplaresN = Integer.parseInt(ejemplares);
		
		Autor autor = autorServicio.obtenerPorId(autorID);
		Editorial editorial = editorialServicio.obtenerPorId(editorialID);
		
		Libro l = this.obtenerPorId(id);
		l.setIsbn(isbnN);
		l.setTitulo(titulo);
		l.setAnio(anioN);
		l.setEjemplares(ejemplaresN);
		l.setAutor(autor);
		l.setEditorial(editorial);
		
		return libroRepositorio.save(l);
	}
	
	@Transactional(readOnly = true)
	public List<Libro> listarTodos() {
		
		return libroRepositorio.findAll();
	
	}
	
	@Transactional(readOnly = true)
	public List<Libro> listarActivos() {
		
		return libroRepositorio.buscarActivos();
	}
	
public void validar(String isbn, String titulo, String anio, String ejemplares) throws Exception{
		
		if (titulo == null || titulo.isEmpty() || titulo.contains(" ")) {
			throw new Exception("Debe ingresar el nombre del libro");
		}
		
		if (libroRepositorio.existePorNombre(titulo)) {
			throw new Exception("El libro ya esta cargado");
		}
		
		if (isbn == null || isbn.isEmpty() || isbn.contains(" ")) {
			throw new Exception("Debe ingresar el isbn del libro");
		}
		
		if (anio == null || anio.isEmpty() || anio.contains(" ")) {
			throw new Exception("Debe ingresar el año del libro");
		}
		
		if (ejemplares == null || ejemplares.isEmpty() || ejemplares.contains(" ")) {
			throw new Exception("Debe ingresar la cantidad de ejemplares del libro");
		}
	}
	
}