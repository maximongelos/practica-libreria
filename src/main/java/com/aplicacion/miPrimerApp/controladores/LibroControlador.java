package com.aplicacion.miPrimerApp.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aplicacion.miPrimerApp.entidades.Libro;
import com.aplicacion.miPrimerApp.servicios.AutorServicio;
import com.aplicacion.miPrimerApp.servicios.EditorialServicio;
import com.aplicacion.miPrimerApp.servicios.LibroServicio;


@Controller
@RequestMapping("/libros")
public class LibroControlador {
	
	@Autowired
	private LibroServicio libroServicio;
	@Autowired 
	private AutorServicio autorServicio;
	@Autowired
	private EditorialServicio editorialServicio;
	
	@GetMapping()
	public String listarLibros(ModelMap modelo) {
		List<Libro> listaLibros = libroServicio.listarTodos();
		modelo.addAttribute("libros", listaLibros);
		return "Libro/lista-libro";
	}
	
	@GetMapping("/registro")
	public String crearLibro(ModelMap modelo) {
		modelo.addAttribute("autores", autorServicio.listarActivos());
		modelo.addAttribute("editoriales", editorialServicio.listarActivos());
		return "Libro/registro-libro";
	}
	
	@PostMapping("/registro")
	public String registrarLibro(ModelMap modelo, @RequestParam("isbn") String isbn, @RequestParam("titulo") String titulo, @RequestParam("anio") String anio,
			@RequestParam("ejemplares") String ejemplares, @RequestParam("autorID") String autorID, @RequestParam("editorialID") String editorialID) {
		try {
			libroServicio.guardar(isbn, titulo, anio, ejemplares, autorID, editorialID);
			modelo.put("exito", "Se registro con exito");
			return "redirect:/libros";
		} catch (Exception e) {
			modelo.put("error", e.getMessage());
			return "redirect:/libros/registro";
		}
		
	}
	
	@GetMapping("/alta/{id}")
	public String altaLibro(@PathVariable("id") String id) {
		try {
			libroServicio.alta(id);
			return "redirect:/libros";
		} catch (Exception e) {
			return "redirect:/libros";
		}
	}
	
	@GetMapping("/baja/{id}")
	public String bajaLibro(@PathVariable("id") String id) {
		try {
			libroServicio.baja(id);
			return "redirect:/libros";
		} catch (Exception e) {
			return "redirect:/libros";
		}
	}
	
	@GetMapping("/editar/{id}")
	public String editarLibro(ModelMap modelo, @PathVariable("id") String id) {
		
		try {
			modelo.addAttribute("libro", libroServicio.obtenerPorId(id));
			modelo.addAttribute("autores" , autorServicio.listarActivos());
			modelo.addAttribute("editoriales" , editorialServicio.listarActivos());
			return "Libro/editar-libro";
		} catch (Exception e) {
			return "redirect:/libros";
		}
		
	}
	
	@PostMapping("/editar/{id}")
	public String editarLibro (@PathVariable("id") String id, @RequestParam("isbn") String isbn,
			@RequestParam("titulo") String titulo, @RequestParam("anio") String anio,
			@RequestParam("ejemplares") String ejemplares, @RequestParam("autorID") String autorID,
			@RequestParam("editorialID") String editorialID) {
		try {
			libroServicio.editar(id, titulo, isbn, anio, ejemplares, autorID, editorialID);
			return "redirect:/libros";
		} catch (Exception e) {
			return "redirect:/libros";
		}
	}
	
	@GetMapping("eliminar/{id}")
	public String eliminarLibro(@PathVariable("id") String id) {
		try {
			libroServicio.eliminar(id);
			return "redirect:/libros";
		} catch (Exception e) {
			return "redirect:/libros";
		}
	}
	
}
